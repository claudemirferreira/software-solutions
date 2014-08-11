package com.tutorialspoint.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DualListModel;

import com.tutorialspoint.modelo.Documento;

@ManagedBean
@SessionScoped
public class PickListView {

	private DualListModel<String> cities;

	private List<Documento> documentos;

	private Documento documento;

	@PostConstruct
	public void init() {
		// Cities
		List<String> citiesSource = new ArrayList<String>();
		List<String> citiesTarget = new ArrayList<String>();

		citiesSource.add("GRUPO QUALIDADE");
		citiesSource.add("GRUPO ENGENHARIA");
		citiesSource.add("GRUPO PRODUCAO");
		citiesSource.add("GRUPO GERENCIA");
		
		cities = new DualListModel<String>(citiesSource, citiesTarget);

		listarDocumentos();
	}

	public void listarDocumentos() {

		documentos = new ArrayList<Documento>();

		documento = new Documento();
		documento.setNome("doc1.xls");
		documentos.add(documento);

		documento = new Documento();
		documento.setNome("doc2.xls");
		documentos.add(documento);

	}

	public DualListModel<String> getCities() {
		return cities;
	}

	public void setCities(DualListModel<String> cities) {
		this.cities = cities;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
}