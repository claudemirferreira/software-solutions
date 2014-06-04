package br.com.ss.core.web.controlador.seguranca;

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

import br.com.ss.core.seguranca.dominio.Perfil;
import br.com.ss.core.seguranca.dominio.PerfilRotina;
import br.com.ss.core.seguranca.dominio.Rotina;
import br.com.ss.core.seguranca.servico.PerfilRotinaServico;

@ManagedBean
@SessionScoped
public class PerfilRotinaControlador {

	private static final String MSG_ADICIONAR = "Rotina adicionada com sucesso.";
	private static final String MSG_REMOVER = "Rotina removida com sucesso.";

	private static final String MSG_ERRO = "Ocorreu um erro ao executar a opera��o!";

	@ManagedProperty(value = "#{perfilRotinaServicoImpl}")
	private PerfilRotinaServico perfilRotinaServico;
	
    private DualListModel<PerfilRotina> dualListRotina;  
    
	private List<PerfilRotina> listaPerfilRotinaNotInPerfil;
	
	@PostConstruct
	public void init() {
		dualListRotina = new DualListModel<PerfilRotina>();
	}

	public void showModalRotina( Perfil perfil ) {
		listaPerfilRotinaNotInPerfil = new ArrayList<PerfilRotina>();
		List<Rotina> listaRotinaNotInPerfil = perfilRotinaServico.listaRotinaNotInPerfil(perfil.getId());
		
		for ( Rotina rot : listaRotinaNotInPerfil ) {
			PerfilRotina perfilRotina = createPerfilRotina(rot, perfil);
			listaPerfilRotinaNotInPerfil.add(perfilRotina);
		}
		
		dualListRotina = new DualListModel<PerfilRotina>(listaPerfilRotinaNotInPerfil, perfil.getPerfilRotina());
	}

	
	private PerfilRotina createPerfilRotina(Rotina rotina, Perfil perfil) {
		PerfilRotina usuarioRotina = new PerfilRotina();
		usuarioRotina.setData(new Date());
		usuarioRotina.setRotina(rotina);
		usuarioRotina.setPerfil(perfil);
		return usuarioRotina;
	}

	
	public void onTransfer(TransferEvent event) {  
		PerfilRotina usuarioRotina = (PerfilRotina)  event.getItems().get(0);
		salvarUsuario(usuarioRotina, event.isAdd());
	}
	
	
	private void salvarUsuario(PerfilRotina perfilRotina, boolean add) {
		try {
			String msg;
			Perfil perfil = perfilRotina.getPerfil();

			if (add) {
				perfil.getPerfilRotina().add(perfilRotina);
				perfilRotinaServico.salvar(perfilRotina);
				msg = MSG_ADICIONAR;
			} else {
				perfil.getPerfilRotina().remove(perfilRotina);
				perfilRotinaServico.remover(perfilRotina);
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
	
	public PerfilRotinaServico getPerfilRotinaServico() {
		return perfilRotinaServico;
	}

	public void setPerfilRotinaServico(PerfilRotinaServico perfilRotinaServico) {
		this.perfilRotinaServico = perfilRotinaServico;
	}

	public DualListModel<PerfilRotina> getDualListRotina() {
		return dualListRotina;
	}

	public void setDualListRotina(DualListModel<PerfilRotina> dualListRotina) {
		this.dualListRotina = dualListRotina;
	}
}
