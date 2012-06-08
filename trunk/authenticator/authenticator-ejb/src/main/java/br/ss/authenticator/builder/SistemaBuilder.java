package br.ss.authenticator.builder;

import javax.enterprise.context.RequestScoped;

import br.ss.authenticator.model.entity.Sistema;
import br.ss.core.builder.AbstractBuilder;

@RequestScoped
public class SistemaBuilder extends AbstractBuilder<SistemaBuilder, Sistema> {

	public SistemaBuilder comAtivo(Boolean ativo) {
		instance.setAtivo(ativo);
		return this;
	}

	public SistemaBuilder comNome(String nomeSistema) {
		instance.setTxSistema(nomeSistema);
		return this;
	}
	
	public SistemaBuilder comDescricao(String descricao) {
		instance.setTxDescricao(descricao);
		return this;
	}

	public SistemaBuilder comSigla(String sigla) {
		instance.setTxSigla(sigla);
		return this;
	}
	
}
