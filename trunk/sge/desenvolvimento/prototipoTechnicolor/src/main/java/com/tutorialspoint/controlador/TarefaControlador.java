package com.tutorialspoint.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tutorialspoint.modelo.Tarefa;

@ManagedBean
@SessionScoped
public class TarefaControlador {

	private List<Tarefa> lista;

	private Tarefa entidade;

	@PostConstruct
	public void init() {

		this.lista = new ArrayList<Tarefa>();

		this.entidade = new Tarefa();
		this.entidade.setData(new Date());
		this.entidade.setDescricao("Revisão");
		this.entidade.setUsuario("Marcos Oliveira");
		this.entidade.setProtocolo("1/2014");
		this.lista.add(entidade);

		this.entidade = new Tarefa();
		this.entidade.setData(new Date());
		this.entidade.setDescricao("Aprovação");
		this.entidade.setUsuario("Marcos Oliveira");
		this.entidade.setProtocolo("2/2014");
		this.lista.add(entidade);

		this.entidade = new Tarefa();
		this.entidade.setData(new Date());
		this.entidade.setDescricao("Homologação");
		this.entidade.setUsuario("Marcos Oliveira");
		this.entidade.setProtocolo("3/2014");
		this.lista.add(entidade);

	}

	public String detalhe(Tarefa entidade) {
		this.entidade = entidade;
		return "/paginas/tarefa/aprovacao.xhtml?faces-redirect=true";
	}

	public List<Tarefa> getLista() {
		return lista;
	}

	public void setLista(List<Tarefa> lista) {
		this.lista = lista;
	}

	public Tarefa getEntidade() {
		return entidade;
	}

	public void setEntidade(Tarefa entidade) {
		this.entidade = entidade;
	}
	
	public String telaPesquisa(){
		return "/paginas/tarefa/pesquisa.xhtml?faces-redirect=true";
	}
	
	public String telaAprovacao(){
		return "/paginas/tarefa/aprovacao.xhtml?faces-redirect=true";
	}
	
	public String telaHomologacao(){
		return "/paginas/tarefa/homologacao.xhtml?faces-redirect=true";
	}

}