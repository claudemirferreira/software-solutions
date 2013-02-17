package br.com.ss.centralaamar.controller;

import javax.mail.internet.AddressException;

public interface RegistrationService {
	
	public void enviarEmail() throws Exception, AddressException;

}
