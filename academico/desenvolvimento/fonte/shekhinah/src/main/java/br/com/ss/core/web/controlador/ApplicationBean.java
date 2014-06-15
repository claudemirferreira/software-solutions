package br.com.ss.core.web.controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.ss.academico.dominio.Empresa;
import br.com.ss.academico.servico.EmpresaServico;
import br.com.ss.core.seguranca.dominio.Sistema;
import br.com.ss.core.seguranca.servico.SistemaServico;
import br.com.ss.core.web.utils.MessageUtils;

@ManagedBean
@ApplicationScoped
public class ApplicationBean {

	@ManagedProperty(value = "#{sistemaServicoImpl}")
	private SistemaServico sistemaServico;

	@ManagedProperty(value = "#{empresaServicoImpl}")
	private EmpresaServico empresaServico;

	private Sistema sistema;

	private Empresa empresa;
	
	@PostConstruct
	public void init() {
		
		// lê a configuracao da empresa e sistema do messages_pt.properties.
		String idEmpresa = MessageUtils.getMessageResourceString( MessageUtils.ID_EMPRESA, null );
		String idSistema = MessageUtils.getMessageResourceString( MessageUtils.ID_SISTEMA, null );
		
		sistema = sistemaServico.findByPrimaryKey(new Long(idSistema));
		empresa = empresaServico.findByPrimaryKey(new Long(idEmpresa));
		
	}
	
	public void reload() {
		// add regra de reload caso necessario.
	}

	
	/* --------- Gets/Sets --------------- */

	public SistemaServico getSistemaServico() {
		return sistemaServico;
	}

	public void setSistemaServico(SistemaServico sistemaServico) {
		this.sistemaServico = sistemaServico;
	}

	public EmpresaServico getEmpresaServico() {
		return empresaServico;
	}

	public void setEmpresaServico(EmpresaServico empresaServico) {
		this.empresaServico = empresaServico;
	}

	
	public Sistema getSistema() {
		return sistema;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}
	
}
