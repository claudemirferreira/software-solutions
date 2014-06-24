package br.com.ss.core.web.controlador;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.core.seguranca.dominio.AbstractEntity;
import br.com.ss.core.seguranca.dominio.Usuario;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.web.enumerated.Constants;
import br.com.ss.core.web.enumerated.Sexo;
import br.com.ss.core.web.ireport.RelatorioUtil;
import br.com.ss.core.web.utils.DateUtil;
import br.com.ss.core.web.utils.FacesUtils;
import br.com.ss.core.web.utils.ReflectionsUtil;

import com.lowagie.text.DocumentException;

@Named
public abstract class ControladorGenerico<T extends AbstractEntity> implements
		Serializable {

	private static final long serialVersionUID = -1229239475130268144L;

	/* ---------- Atributos ----------------------- */

	/** Entity usado no cadastro. */
	protected T entidade;

	/** Entity usado na pesquisa. */
	protected T pesquisa;

	/** Entity selecionada para exclusão. */
	protected T itemToRemove;

	/** Lista com o resultado da pesquisa. */
	protected List<T> listaPesquisa;

	@ManagedProperty(value = "#{relatorioUtil}")
	protected RelatorioUtil relatorioUtil;

	// FIXME deve ficar no contexto de app - criar classe
	protected List<SelectItem> sexoList;

	/**
	 * Alias para redirecionar para a tela de cadastro.
	 */
	public static final String CADASTRO = "cadastro";

	/**
	 * Alias para redirecionar para a tela de pesquisa.
	 */
	public static final String PESQUISA = "pesquisa";

	/**
	 * Alias para redirecionar para a tela de relatorio.
	 */
	public static final String RELATORIO = "relatorio";

	/**
	 * armazena os bytes do relatorio
	 */
	private StreamedContent inputStream;

	/* ---------- Metodos ----------------------- */

	@PostConstruct
	protected void setup() {
		initEntity();
		init();
		initCommons();
		pesquisar();
	}

	protected void initCommons() {

		sexoList = new ArrayList<SelectItem>();
		for (Sexo c : Sexo.values()) {
			sexoList.add(new SelectItem(c, c.getDescricao()));
		}

	}

	protected void init() {
		// Sobrescrever caso necessario
	}

	/**
	 * Força o recarregamento da pagina de pesquisa.
	 */
	public void reload() {
		init();
	}

	@SuppressWarnings("unchecked")
	protected void initEntity() {
		try {
			Class<T> clazz = resolverClass();
			pesquisa = (T) ReflectionsUtil.callConstructor(clazz);
			entidade = (T) ReflectionsUtil.callConstructor(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> resolverClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/** Nome do relatorio utilizado na impressao. */
	protected abstract String getNomeRelatorio();

	/** Retornar o serviço da entidade. */
	protected abstract IService<T, Long> getService();

	public void pesquisar() {
		this.listaPesquisa = getService().pesquisar(pesquisa);
	}

	public String salvar() {
		try {
			getService().salvar(entidade);
			setup();
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
			return PESQUISA;
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
			return null;
		}
	}

	public void excluir(T itemRemove) {
		itemToRemove = itemRemove;
		excluir();
	}

	public void excluir() {
		try {
			executarExcluir(itemToRemove);
			pesquisar();
			setItemToRemove(null);
			showMessage(Constants.MSG_SUCESSO, FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(Constants.MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		}
	}

	public void executarExcluir(T itemRemove) {
		T removeEntity = getService().findByPrimaryKey(itemRemove.getId());
		getService().remover(removeEntity);
	}

	/**
	 * Metodo utilizado para ir para a tela de cadastra da entidade.
	 * 
	 * @return string.
	 */
	public String novo() {
		this.initEntity();
		return CADASTRO;
	}

	public String detalhe() {
		return detalhe(entidade);
	}

	/**
	 * Metodo utilizado para editar uma entidade. Sobrescrever este metodo caso
	 * necessário realizar outras operaçoes.
	 * 
	 * @return string.
	 */
	public String detalhe(T entidade) {
		this.entidade = entidade;
		return CADASTRO;
	}

	/**
	 * Metodo utilizado para cancelar uma edicao e retornar para a pg de
	 * inicial.
	 * 
	 * @return string.
	 */
	public String cancelar() {
		init();
		return PESQUISA;
	}

	/**
	 * Impressao de um item selecionado no grid de pesquisa.
	 * 
	 * @param entity
	 */
	public void imprimir(T entity) {
		// FIXME #Peninha: implementar: metodo para imprimir do grid de pesquisa
		// usar em telas como: matricula, aluno..
	}

	/**
	 * Usado para imprimir o grid da tela de pesqusia.
	 * 
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void imprimir() throws FileNotFoundException, IOException,
			DocumentException {
		relatorioUtil.gerarRelatorioWeb(this.listaPesquisa, null,
				getNomeRelatorio());
		// return RELATORIO;
	}

	public void imprimir(List<?> lista, Map<String, Object> params,
			String nomeRelatorio) {

		try {
			byte[] dadosPdf = relatorioUtil.gerarRelatorioWebBytes(lista,
					params, nomeRelatorio);
			InputStream is = new ByteArrayInputStream(dadosPdf);

			// InputStream inputStream = new FileInputStream(arquivo);

			//
			// File f = new File ("teste.pdf");
			// FileOutputStream fos = null;
			// try {
			// fos = new FileOutputStream (f);
			// fos.write (dadosPdf);
			// } finally {
			// if (fos != null) try { fos.close(); } catch (IOException ex) {}
			// }

			// inputStream = new DefaultStreamedContent(fos, "application/pdf");
			inputStream = new DefaultStreamedContent(is, "application/pdf");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* -------- Metodos utilitarios -------------- */

	protected void showMessage(String msg, Severity severityInfo) {
		showMessage(msg, null, severityInfo);
	}

	protected void showMessage(String msg, String detail, Severity severityInfo) {
		FacesUtils.addMessage(msg, detail, severityInfo);
	}

	/**
	 * Retorna o usuário logado.
	 * 
	 * @return Usuario
	 */
	protected Usuario getUsuarioLogado() {
		return ((Usuario) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal());
	}

	/**
	 * Retorna a instancia de HttpServletRequest.
	 */
	protected HttpServletRequest getRequest() {
		return FacesUtils.getRequest();
	}

	protected boolean isDataFuturo(Date date) {
		return DateUtil.isDataFuturo(date);
	}

	/* ---------- Others ------------- */

	/* ---------- Gets/Sets ------------- */

	public T getItemToRemove() {
		return itemToRemove;
	}

	public void setItemToRemove(T itemToRemove) {
		this.itemToRemove = itemToRemove;
	}

	public List<T> getResultList() {
		return listaPesquisa;
	}

	public T getEntidade() {
		return entidade;
	}

	public T getPesquisa() {
		return pesquisa;
	}

	public List<T> getListaPesquisa() {
		return listaPesquisa;
	}

	public List<SelectItem> getSexoList() {
		return sexoList;
	}

	public RelatorioUtil getRelatorioUtil() {
		return relatorioUtil;
	}

	public void setRelatorioUtil(RelatorioUtil relatorioUtil) {
		this.relatorioUtil = relatorioUtil;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

	public StreamedContent getInputStream() {
		return inputStream;
	}

	public void setInputStream(StreamedContent inputStream) {
		this.inputStream = inputStream;
	}

}