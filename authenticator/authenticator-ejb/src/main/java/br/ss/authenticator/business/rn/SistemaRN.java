package br.ss.authenticator.business.rn;

import javax.inject.Inject;

import br.ss.authenticator.model.dao.SistemaDAO;
import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.esteriotipo.BusinessRule;
import br.ss.core.transaction.qualifier.Transactional;

@BusinessRule
public class SistemaRN {
	
	@Inject
	private SistemaDAO dao;
	

	@Transactional
	public void ativar(Sistema sistema) {
		sistema.setAtivo(true);
	}

	@Transactional
	public void desativar(Sistema sistema) {
		sistema.setAtivo(false);
	}

	@Transactional
	public void remover(Sistema sistema) {
		dao.remove(sistema);
	}

	@Transactional
	public void salvar(Sistema sistema) {
		dao.persist(sistema);
	}
	
	@Transactional
	public void atualizar(Sistema sistema) {
		dao.merge(sistema);
	}
	

}
