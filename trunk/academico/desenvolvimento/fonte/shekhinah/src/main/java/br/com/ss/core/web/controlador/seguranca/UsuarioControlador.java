package br.com.ss.core.web.controlador.seguranca;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ss.core.seguranca.dominio.Perfil;
import br.com.ss.core.seguranca.dominio.Usuario;
import br.com.ss.core.seguranca.servico.IService;
import br.com.ss.core.seguranca.servico.UsuarioServico;
import br.com.ss.core.web.controlador.ControladorGenerico;
import br.com.ss.core.web.enumerated.StatusUsuario;
import br.com.ss.core.web.utils.CriptografiaUtil;
import br.com.ss.core.web.utils.StringUtil;

@ManagedBean
@SessionScoped
public class UsuarioControlador extends ControladorGenerico<Usuario> {

	private static final long serialVersionUID = -929165489387258837L;

	private List<Perfil> perfis = new ArrayList<Perfil>();

	@ManagedProperty(value = "#{usuarioServicoImpl}")
	private UsuarioServico servico;

	private int colunas;

	protected List<SelectItem> statusUsuarioList;

	public void init() {
		this.statusUsuarioList = new ArrayList<SelectItem>();
		for (StatusUsuario c : StatusUsuario.values()) {
			statusUsuarioList.add(new SelectItem(c, c.getDescricao()));
		}
	}

	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha: relatorio
		return null;
	}
	
	
	@Override
	public String salvar() {
		// criptografa a senha do usuario
		if (StringUtil.notEmpty(entidade.getSenha())) {
			entidade.setSenha( CriptografiaUtil.criptografar(entidade.getSenha()) );
		}
		
		return super.salvar();
	}

	@Override
	protected IService<Usuario, Long> getService() {
		return servico;
	}


	public String editarSenha() {
		this.entidade = (Usuario) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		return "paginas/usuario/alterarSenha.xhtml?faces-redirect=true";
	}

	/* --------------- Gets/Sets ---------------------- */

	public UsuarioServico getServico() {
		return servico;
	}

	public void setServico(UsuarioServico servico) {
		this.servico = servico;
	}


	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public List<SelectItem> getStatusUsuarioList() {
		return statusUsuarioList;
	}

}