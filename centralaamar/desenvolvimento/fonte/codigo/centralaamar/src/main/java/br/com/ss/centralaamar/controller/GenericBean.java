package br.com.ss.centralaamar.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;

import br.com.ss.centralaamar.component.Relatorio;
import br.com.ss.centralaamar.exception.ValidationException;
import br.com.ss.centralaamar.model.entity.AbstractEntity;
import br.com.ss.centralaamar.service.IService;

@Component
public abstract class GenericBean<T extends AbstractEntity> implements
		Serializable {

	/* ---------- Atributos ----------------------- */

	private static final long serialVersionUID = -1229239475130268144L;

	/** Entity usado no cadastro. */
	@Getter
	protected T entity;

	/** Entity usado na pesquisa. */
	@Getter
	protected T search;

	private Class<T> entityClass;

	@Getter
	@Setter
	protected T itemToRemove;

	@Getter
	@Setter
	protected List<T> resultList;

	@Getter
	@Setter
	protected Relatorio relatorio = new Relatorio();

	// @Inject
	// @Autowired
	// protected Conversation conversation; // TODO conversation

	/* ---------- Metodos ----------------------- */

	@PostConstruct
	protected void setup() throws InstantiationException,
			IllegalAccessException {
		// initConversation();
		instanciateEntityClass();
		initEntity();
		init();
		getPathRelatorio();

	}

	@PreDestroy
	public void cleanUp() throws Exception {
		// this.resultList = null;
	}

	public void init() {
		this.search();
	}

	protected void initEntity() throws InstantiationException,
			IllegalAccessException {
		this.entity = entityClass.newInstance();
		this.search = entityClass.newInstance();

	}

	protected abstract IService<T> getService();

	private void instanciateEntityClass() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) getClass()
					.getGenericSuperclass();
			entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		}
	}

	public void search() {
		this.resultList = getService().search(search);
	}

	public String save() throws SQLException {
		try {
			getService().save(entity);
			init();
			return resolveNavigation(false);
		} catch (UnexpectedRollbackException ure) {
			System.out.println(ure.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"JA EXISTE UM REGISTRO CADASTRADO COM O NOME "));
			ure.printStackTrace();

		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnning", e
							.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e
							.getMessage()));

		}

		return null;
	}

	public void remove(ActionEvent actionEvent) {

	}

	public void remove() {
		remove(itemToRemove);
		search();
		setItemToRemove(null);
	}

	public void remove(T itemRemove) {
		getService().remove(itemRemove);
	}

	/**
	 * Metodo utilizado para ir para a tela de cadastra da entidade.
	 * 
	 * @return string.
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public String cadastrar() throws InstantiationException,
			IllegalAccessException {
		this.initEntity();
		return resolveNavigation(true);
	}

	/**
	 * Metodo utilizado para editar uma entidade. Sobrescrever este metodo caso
	 * necessário realizar outras operaçoes.
	 * 
	 * @return string.
	 */
	public String editar(T entity) {
		this.entity = entity;
		return resolveNavigation(true);
	}

	/**
	 * Metodo utilizado para editar uma entidade.
	 * 
	 * @return string.
	 */
	public String cancel() {
		// endConversation();
		init();
		return resolveNavigation(false);
	}

	/* ---------- Others ------------- */
	protected String resolveNavigation(boolean crud) {
		String url = "/pages/"
				+ entity.getClass().getSimpleName().toLowerCase() + "/";
		url += crud ? "create.jsf" : "search.jsf";
		return url;
	}

	protected void getPathRelatorio() {
		this.relatorio.setPath("C:\\jasper\\"
				+ entity.getClass().getSimpleName().toLowerCase() + ".jasper");
		System.out.println("path relatorio == " + this.relatorio.getPath());
	}

	public void print() {
		ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();

		try {

			FileInputStream fileJasper = new FileInputStream(relatorio.getPath());

			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();

			JasperPrint preencher = JasperFillManager.fillReport(fileJasper, relatorio
					.getParametros(),
					new JRBeanCollectionDataSource(relatorio.getResultList()));

			JasperExportManager.exportReportToPdfStream(preencher,
					byteOutPutStream);

			response.setContentLength(byteOutPutStream.size());
			response.setContentType("application/pdf");

			ServletOutputStream servletOutPutStream = response
					.getOutputStream();
			servletOutPutStream.write(byteOutPutStream.toByteArray(), 0,
					byteOutPutStream.size());

			servletOutPutStream.flush();
			servletOutPutStream.close();

			FacesContext.getCurrentInstance().responseComplete();

		} catch (JRException jrex) {
			jrex.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("NAO FOI ENCONTRADO O RELATORIO " + relatorio.getPath());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
