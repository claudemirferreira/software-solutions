package br.ss.authenticator.managedbean;

import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import br.ss.authenticator.business.service.SistemaService;
import br.ss.authenticator.model.dao.SistemaDAO;
import br.ss.authenticator.model.entity.Sistema;


@ConversationScoped
@Named
public class SistemaBean {
	
	@Inject
	private SistemaService dsl;
	
	@Inject
	private SistemaDAO dao;
	
	@Getter
	private List<Sistema> resultList;
	
	@Getter @Setter
	private Sistema entity;
	
	public SistemaBean() {
		
		resultList = dao.obterAllSistemas();
		
	}
	
	
	public String persit() {
		
		dsl.salvar();
		
		return null;
	}
	
	

}
