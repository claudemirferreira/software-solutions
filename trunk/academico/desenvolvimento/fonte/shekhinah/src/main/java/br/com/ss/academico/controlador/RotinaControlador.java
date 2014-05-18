package br.com.ss.academico.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.ss.academico.dominio.Perfil;
import br.com.ss.academico.dominio.Rotina;
import br.com.ss.academico.servico.IService;
import br.com.ss.academico.servico.RotinaServico;

@ManagedBean
@SessionScoped
public class RotinaControlador extends ControladorGenerico<Rotina> {

	private static final long serialVersionUID = -6832271293709421841L;

	@ManagedProperty(value = "#{rotinaServicoImpl}")
	private RotinaServico servico;

	private Perfil perfil;

	private int colunas;

	private final String LISTAR_ROTINAS = "listar_rotinas";

	@Override
	public void init() {
		
	}

	@Override
	protected void initEntity() {
		this.entidade = new Rotina();
		this.pesquisa = new Rotina();
		this.perfil = new Perfil();
	}

	@Override
	protected String getNomeRelatorio() {
		// FIXME #Peninha: verificar relatorio
		return null;
	}

	@Override
	protected IService<Rotina, Long> getService() {
		return servico;
	}


	public String listarRotinas(Perfil perfil) {
		this.perfil = perfil;
		return listarRotinas();
	}
	
	public String listarRotinas() {
		this.listaPesquisa = servico.listaRotinasPorPerfil(this.perfil.getId());
		this.colunas = 4; // Util.definirTamanhoColuna(rotinas.size());
		return LISTAR_ROTINAS;
	}

	public String telaRelatorio(Rotina rotina) {
		return rotina.getAcao() ;
	}


	/* --------------- Gets/Sets ----------------------*/
	
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public RotinaServico getServico() {
		return servico;
	}

	public void setServico(RotinaServico servico) {
		this.servico = servico;
	}

}