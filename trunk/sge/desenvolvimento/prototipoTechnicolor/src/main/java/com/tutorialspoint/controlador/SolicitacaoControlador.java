package com.tutorialspoint.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tutorialspoint.modelo.Solicitacao;

@ManagedBean
@SessionScoped
public class SolicitacaoControlador {

	private List<Solicitacao> lista;

	private Solicitacao entidade;

	@PostConstruct
	public void init() {

		this.lista = new ArrayList<Solicitacao>();

		this.entidade = new Solicitacao();
		this.entidade.setDataInicio(new Date());
		this.entidade.setDescricao("Revisão");
		this.entidade.setUsuario("Carlos Eduardo Fernades");
		this.entidade.setStatus("PENDENTE");
		this.entidade.setProtocolo("1/2014");
		this.lista.add(entidade);

		this.entidade = new Solicitacao();
		this.entidade.setDataInicio(new Date());
		this.entidade.setDescricao("Aprovação");
		this.entidade.setUsuario("Elimarcos Arouca");
		this.entidade.setProtocolo("2/2014");
		this.entidade.setStatus("PENDENTE");
		this.lista.add(entidade);

		this.entidade = new Solicitacao();
		this.entidade.setDataInicio(new Date());
		this.entidade.setDescricao("Homologação");
		this.entidade.setUsuario("Danny Lopes");
		this.entidade.setProtocolo("3/2014");
		this.entidade.setStatus("PENDENTE");
		this.lista.add(entidade);

	}

	
	public String detalhe(Solicitacao entidade) {
		this.entidade = entidade;
		return "/paginas/solicitacao/cadastro.xhtml?faces-redirect=true";
	}

	public List<Solicitacao> getLista() {
		return lista;
	}

	public void setLista(List<Solicitacao> lista) {
		this.lista = lista;
	}

	public Solicitacao getEntidade() {
		return entidade;
	}

	public void setEntidade(Solicitacao entidade) {
		this.entidade = entidade;
	}
	
	public String telaPesquisa(){
		return "/paginas/solicitacao/pesquisa.xhtml?faces-redirect=true";
	}

	public String telaCadastro(){
		return "/paginas/solicitacao/cadastro.xhtml?faces-redirect=true";
	}
	
	public String avancar() {
		return "/paginas/solicitacao/documentos.xhtml?faces-redirect=true";
	}
	
}