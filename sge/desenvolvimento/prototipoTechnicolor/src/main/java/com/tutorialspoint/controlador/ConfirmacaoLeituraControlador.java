package com.tutorialspoint.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tutorialspoint.modelo.ConfirmacaoLeitura;

@ManagedBean
@SessionScoped
public class ConfirmacaoLeituraControlador {

	private List<ConfirmacaoLeitura> confirmacaoLeituras;

	private ConfirmacaoLeitura confirmacaoLeitura;

	@PostConstruct
	public void init() {

		listarConfirmacaoLeitura();

	}

	public void listarConfirmacaoLeitura() {

		this.confirmacaoLeituras = new ArrayList<ConfirmacaoLeitura>();
		this.confirmacaoLeitura = new ConfirmacaoLeitura();
		this.confirmacaoLeitura.setBusinessKey("1/2014");
		this.confirmacaoLeitura.setData(new Date());
		this.confirmacaoLeitura.setLogin("Marcos Oliveira");
		this.confirmacaoLeituras.add(confirmacaoLeitura);

		this.confirmacaoLeitura = new ConfirmacaoLeitura();
		this.confirmacaoLeitura.setBusinessKey("2/2014");
		this.confirmacaoLeitura.setData(new Date());
		this.confirmacaoLeitura.setLogin("Marcos Oliveira");
		this.confirmacaoLeituras.add(confirmacaoLeitura);

		this.confirmacaoLeitura = new ConfirmacaoLeitura();
		this.confirmacaoLeitura.setBusinessKey("3/2014");
		this.confirmacaoLeitura.setData(new Date());
		this.confirmacaoLeitura.setLogin("Marcos Oliveira");
		this.confirmacaoLeituras.add(confirmacaoLeitura);

	}

	public String avancar() {
		return "/paginas/solicitacao/documentos.xhtml?faces-redirect=true";
	}

	public String voltar() {
		return "/paginas/solicitacao/pesquisa.xhtml?faces-redirect=true";
	}

	public List<ConfirmacaoLeitura> getConfirmacaoLeituras() {
		return confirmacaoLeituras;
	}

	public void setConfirmacaoLeituras(
			List<ConfirmacaoLeitura> confirmacaoLeituras) {
		this.confirmacaoLeituras = confirmacaoLeituras;
	}

	public ConfirmacaoLeitura getConfirmacaoLeitura() {
		return confirmacaoLeitura;
	}

	public void setConfirmacaoLeitura(ConfirmacaoLeitura confirmacaoLeitura) {
		this.confirmacaoLeitura = confirmacaoLeitura;
	}

}