package br.com.ss.centralaamar.controller;

import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ss.centralaamar.model.entity.Profissao;
import br.com.ss.centralaamar.service.IProfissaoService;
import br.com.ss.centralaamar.service.IService;

@Component("profissaoController")
@Named
@Scope("session")
public class ProfissaoController extends GenericBean<Profissao> {

	@Autowired
	private IProfissaoService service;

	@Override
	protected IService<Profissao> getService() {
		return service;
	}

	@Override
	public String save() {
		this.entity.setNome(this.entity.getNome().toUpperCase());
		try {
			return super.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void print() {
		this.relatorio.setPath("D:\\jasper\\profissao.jasper");
		this.relatorio.setResultList(this.resultList);
		super.print();
	}

}