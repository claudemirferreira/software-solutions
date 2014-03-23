package br.com.ss.centralaamar.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.ss.centralaamar.model.dominio.Perfil;
import br.com.ss.centralaamar.model.dominio.Usuario;
import br.com.ss.centralaamar.model.dominio.UsuarioPerfil;
import br.com.ss.centralaamar.model.servico.UsuarioPerfilServico;
import br.com.ss.centralaamar.model.servico.UsuarioServico;

@ManagedBean
@SessionScoped
public class UsuarioPerfilControlador {

	private static final String MSG_ADICIONAR = "Perfil adicionado com sucesso.";
	private static final String MSG_REMOVER = "Perfil removido com sucesso.";

	private static final String MSG_ERRO = "Ocorreu um erro ao executar a operação!";

	@ManagedProperty(value = "#{usuarioServicoImpl}")
	private UsuarioServico usuarioServico;

	@ManagedProperty(value = "#{usuarioPerfilServicoImpl}")
	private UsuarioPerfilServico usuarioPerfilServico;

	private DualListModel<UsuarioPerfil> dualListPerfil;

	private List<UsuarioPerfil> listaUsuPerfilNotInUsuario;

	@PostConstruct
	public void init() {
		dualListPerfil = new DualListModel<UsuarioPerfil>();
	}

	public void showModalPerfil(Usuario usuario) {
		listaUsuPerfilNotInUsuario = new ArrayList<UsuarioPerfil>();
		List<Perfil> listaPerfilNotInUsuario = usuarioPerfilServico
				.listaPerfilNotInUsuario(usuario.getId());

		for (Perfil perfil : listaPerfilNotInUsuario) {
			UsuarioPerfil usuarioPerfil = createUsuarioPerfil(perfil, usuario);
			listaUsuPerfilNotInUsuario.add(usuarioPerfil);
		}
		// faz o fetch de UsuarioPerfil
		List<UsuarioPerfil> usuPerfis = usuarioPerfilServico
				.findByUsuario(usuario);
		usuario.setUsuarioPerfil(usuPerfis);
		dualListPerfil = new DualListModel<UsuarioPerfil>(
				listaUsuPerfilNotInUsuario, usuario.getUsuarioPerfil());
	}

	private UsuarioPerfil createUsuarioPerfil(Perfil perfil, Usuario usuario) {
		UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
		usuarioPerfil.setData(new Date());
		usuarioPerfil.setPerfil(perfil);
		usuarioPerfil.setUsuario(usuario); // TODO validar relacionamento qdo o
											// item for excluido/adicionado..
		return usuarioPerfil;
	}

	public void onTransfer(TransferEvent event) {

		UsuarioPerfil usuarioPerfil = (UsuarioPerfil) event.getItems().get(0);

		salvarUsuario(usuarioPerfil, event.isAdd());

	}

	private void salvarUsuario(UsuarioPerfil usuarioPerfil, boolean add) {

		try {
			String msg;

			if (add) {
				usuarioPerfilServico.salvar(usuarioPerfil);
				msg = MSG_ADICIONAR;
			} else {
				usuarioPerfilServico.remover(usuarioPerfil);
				msg = MSG_REMOVER;
			}

			showMessage(msg, FacesMessage.SEVERITY_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			showMessage(MSG_ERRO, FacesMessage.SEVERITY_ERROR);
		}

	}

	private void showMessage(String msg, Severity severityInfo) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(severityInfo);
		facesMessage.setSummary(msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	/* ---------- Gets/Sets --------------- */

	public UsuarioServico getUsuarioServico() {
		return usuarioServico;
	}

	public void setUsuarioServico(UsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}

	public UsuarioPerfilServico getUsuarioPerfilServico() {
		return usuarioPerfilServico;
	}

	public void setUsuarioPerfilServico(
			UsuarioPerfilServico usuarioPerfilServico) {
		this.usuarioPerfilServico = usuarioPerfilServico;
	}

	public DualListModel<UsuarioPerfil> getDualListPerfil() {
		return dualListPerfil;
	}

	public void setDualListPerfil(DualListModel<UsuarioPerfil> dualListPerfil) {
		this.dualListPerfil = dualListPerfil;
	}
}
