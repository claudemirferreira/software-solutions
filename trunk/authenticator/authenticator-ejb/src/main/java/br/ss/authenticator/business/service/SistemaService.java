package br.ss.authenticator.business.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.ss.authenticator.builder.SistemaBuilder;
import br.ss.authenticator.business.rn.SistemaRN;
import br.ss.authenticator.model.dao.SistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.validator.BeanValidator;

@RequestScoped
public class SistemaService {
	
	@Inject
	private SistemaRN regra;

	@Inject
	private SistemaDAO dao;
	
	private SistemaBuilder builder;

	@Inject
	private BeanValidator<Sistema> sistemaValidator;
	
	public SistemaService() {
		builder = new SistemaBuilder();
	}

	public SistemaService recuperar(Long id) {
		Sistema sistema = dao.getByPrimaryKey(id);
		builder.withInstance(sistema);
		return this;
	}
	
	public SistemaService ativar() {
		regra.ativar(builder.build());
		return this;
	}
	

	public SistemaService desativar() {
		regra.desativar(builder.build());
		return this;
	}

	public SistemaService remover() {
		regra.remover(builder.build());
		return this;
	}

	public SistemaService criar(Sistema sis) {
		builder.newInstance();
		sistemaValidator.check(sis);
		builder.comNome(sis.getTxSistema());
		builder.comAtivo(true);
		return this;
	}

	public Sistema salvar() {
		Sistema td = builder.build(); 
		regra.salvar( td );
		return td;
	}
	
	public Sistema atualizar()  {
		Sistema td = builder.build();
		regra.atualizar( td );
		return td;
	}
	
}
