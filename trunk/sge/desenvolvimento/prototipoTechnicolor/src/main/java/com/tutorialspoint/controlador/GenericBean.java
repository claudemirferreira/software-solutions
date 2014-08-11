package com.tutorialspoint.controlador;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.tutorialspoint.modelo.HistoricoDocumento;


@ManagedBean(name = "genericBean")
@SessionScoped
public class GenericBean {

	private Collection<HistoricoDocumento> historicoDocumentoList;
	private Collection<HistoricoDocumento> historicoDocumentoPesquisaList;
	
	@PostConstruct
	public void init() {
		this.historicoDocumentoList = 
				new ArrayList<HistoricoDocumento>();
		
		this.historicoDocumentoPesquisaList = 
				new ArrayList<HistoricoDocumento>();
		
		HistoricoDocumento h1 = new HistoricoDocumento();
		h1.setVersao(1.0);
		h1.setAutorAlteracao("Marcos Oliveira");
		h1.setDataAlteracao("05/05/2014");
		
		HistoricoDocumento h2 = new HistoricoDocumento();
		h2.setVersao(2.0);
		h2.setAutorAlteracao("Tony Ricardo");
		h2.setDataAlteracao("10/06/2014");
		
		HistoricoDocumento h3 = new HistoricoDocumento();
		h3.setVersao(3.0);
		h3.setAutorAlteracao("Luciana Goncalves");
		h3.setDataAlteracao("15/06/2014");
		
		this.historicoDocumentoList.add(h1);
		this.historicoDocumentoList.add(h2);
		this.historicoDocumentoList.add(h3);
	}
	
	public void viewDialog() {
		RequestContext.getCurrentInstance().execute("dlgInfo.show()");
	}
	
	public Collection<HistoricoDocumento> getHistoricoDocumentoList() {
		return historicoDocumentoList;
	}

	public void setHistoricoDocumentoList(
			Collection<HistoricoDocumento> historicoDocumentoPesquisaList) {
		this.historicoDocumentoPesquisaList = historicoDocumentoPesquisaList;
	}
	public Collection<HistoricoDocumento> getHistoricoDocumentoPesquisaList() {
		return historicoDocumentoPesquisaList;
	}

	public void setHistoricoDocumentoPesquisaList(
			Collection<HistoricoDocumento> historicoDocumentoPesquisaList) {
		this.historicoDocumentoPesquisaList = historicoDocumentoPesquisaList;
	}
}
