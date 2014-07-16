package br.com.ss.core.web.controlador.seguranca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.ss.core.seguranca.dominio.Perfil;
import br.com.ss.core.seguranca.dominio.PerfilRotina;
import br.com.ss.core.seguranca.dominio.Rotina;
import br.com.ss.core.seguranca.servico.PerfilRotinaServico;
import br.com.ss.core.web.utils.FacesUtils;

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

	
	@SuppressWarnings("unchecked")
	public void onTransfer(TransferEvent event) {  

		boolean add = event.isAdd();
		
		List<PerfilRotina> perRots = (List<PerfilRotina>) event.getItems();
		for (PerfilRotina usuarioRotina : perRots ) {
			salvarUsuario(usuarioRotina, add);
		}
		
		String msg = MSG_ADICIONAR;
		if (!add) {
			msg = MSG_REMOVER;
		}

		FacesUtils.addMessage(msg, null, FacesMessage.SEVERITY_INFO);
	}
	
	
	private void salvarUsuario(PerfilRotina perfilRotina, boolean add) {
		try {
			
			Perfil perfil = perfilRotina.getPerfil();

			if (add) {
				perfil.getPerfilRotina().add(perfilRotina);
				perfilRotinaServico.salvar(perfilRotina);
				
			} else {
				perfil.getPerfilRotina().remove(perfilRotina);
				perfilRotinaServico.remover(perfilRotina);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtils.addMessage(MSG_ERRO, null, FacesMessage.SEVERITY_ERROR);
		}

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
