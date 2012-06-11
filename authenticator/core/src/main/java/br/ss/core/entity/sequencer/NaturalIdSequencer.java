package br.ss.core.entity.sequencer;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;

import br.ss.core.entity.NaturalIdentifier;

@ConversationScoped
public class NaturalIdSequencer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer actualId = 0;
	
	public void generate( NaturalIdentifier identifier ){
		identifier.setNaturalIdentifier(++actualId);
	}
	
}
