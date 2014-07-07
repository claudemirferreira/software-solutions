package br.com.ss.academico.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.servico.MensalidadeServico;

@ManagedBean
@SessionScoped
public class HomeControlador {

	@ManagedProperty(value = "#{mensalidadeServicoImpl}")
	private MensalidadeServico mensalidadeServico;
	
	private List<Mensalidade> mensalidadesAtraso;

	@PostConstruct
	public void init() {
		reload();
	}


	public void reload() {
		mensalidadesAtraso = mensalidadeServico.listarMensalidadesEmAtraso();
	}
	

	public MensalidadeServico getMensalidadeServico() {
		return mensalidadeServico;
	}

	public void setMensalidadeServico(MensalidadeServico mensalidadeServico) {
		this.mensalidadeServico = mensalidadeServico;
	}

	public List<Mensalidade> getMensalidadesAtraso() {
		return mensalidadesAtraso;
	}

}