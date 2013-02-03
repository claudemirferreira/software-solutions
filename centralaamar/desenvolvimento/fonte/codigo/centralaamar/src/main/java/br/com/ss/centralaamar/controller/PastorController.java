package br.com.ss.centralaamar.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ss.centralaamar.componente.report.IReport;
import br.com.ss.centralaamar.model.entity.AbstractEntity;
import br.com.ss.centralaamar.model.entity.Pastor;
import br.com.ss.centralaamar.service.IPastorService;
import br.com.ss.centralaamar.service.IService;

@Controller
@Named
@Scope("session")
public class PastorController extends GenericBean<Pastor> {

	@Autowired
	private IPastorService service;

	@Override
	protected IService<Pastor> getService() {
		return service;
	}

	public String save() throws SQLException {
		this.entity.setNome(this.entity.getNome().toUpperCase());
		return super.save();

	}

	public void print() {
		this.relatorio.setPath("D:\\jasper\\pequenoGrup.jasper");
		// TODO INCLUIR A CONSULTA AOS DADOS DO RELATORIOS
		this.relatorio.setResultList(new ArrayList<AbstractEntity>());
		super.print();
	}

}