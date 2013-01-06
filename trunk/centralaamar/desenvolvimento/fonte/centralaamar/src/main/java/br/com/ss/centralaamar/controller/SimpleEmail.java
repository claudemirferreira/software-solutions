package br.com.ss.centralaamar.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component("simpleEmail")
@ManagedBean(name = "simpleEmail")
@Scope("session")
public class SimpleEmail {
	// atributos que serão injetados pelo Spring
	
	private MailSender mailSender;
	private SimpleMailMessage defaultMessage;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setDefaultMessage(SimpleMailMessage defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	// método responsável pelo envio de e-mail
	public void enviar() {
		SimpleMailMessage message = new SimpleMailMessage(this.defaultMessage);
		message.setFrom("alvaraesam@gmail.com");
		message.setText("teste teste teste teste teste");

		try {
			this.mailSender.send(message);
		} catch (MailException ex) {
			ex.printStackTrace();
		}
	}
	
	public void enviarEmail(){
		ApplicationContext appCtx = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/spring/jboss-as-spring-mvc-context.xml");

		SimpleEmail enviaEmailSimples = (SimpleEmail) appCtx
				.getBean("simpleMail");
		enviaEmailSimples.enviar();
		
	}

	public static void main(String args[]) {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/spring/jboss-as-spring-mvc-context.xml");

		SimpleEmail enviaEmailSimples = (SimpleEmail) appCtx
				.getBean("simpleMail");
		enviaEmailSimples.enviar();

	}

}