package br.ss.core.message;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import lombok.Getter;
import br.ss.core.exception.ExceptionMessage;

@RequestScoped
public class CoreMessage {

	@Getter
	private List<ExceptionMessage> messages;
	
	@PostConstruct
	public void init() {
		messages = new ArrayList<ExceptionMessage>();
	}
	
	public void info( String descricao, String target ) {
		messages.add( new InfoMessage( descricao, target ) );
	}
	
	public void info( String descricao ) {
		info( descricao, null );
	}
	
	public void warn( String descricao, String target ) {
		messages.add( new WarnMessage( descricao, target ) );
	}
	
	public void warn( String descricao ) {
		warn( descricao, null );
	}
	
	public void error( String descricao, String target ) {
		messages.add( new ErrorMessage( descricao, target ) );
	}
	
	public void error( String descricao ) {
		error( descricao, null );
	}
	
	public void add( ExceptionMessage msg ) {
		if ( msg != null ) {
			messages.add( msg );
		}
	}
	
	public boolean hasMessages() {
		return messages.size() > 0;
	}
	
}
