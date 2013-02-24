package br.com.ss.centralaamar.controller;

import java.math.BigDecimal;

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

	public void enviarEmail() {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/spring/jboss-as-spring-mvc-context.xml");

		SimpleEmail enviaEmailSimples = (SimpleEmail) appCtx
				.getBean("simpleMail");
		enviaEmailSimples.enviar();

	}

	
	public static void doPartitionMail() {
		
		int emailListSize = 95;
		int qtToSend = 10;
		int loop = new BigDecimal(emailListSize).divide( new BigDecimal(qtToSend)).intValue();
		BigDecimal remainder = new BigDecimal(95).remainder(new BigDecimal(10));
		
		for ( int i = 0 ; i < loop ; i++ ) {
			
			// envia o email pela qtde maxima definida para envio
			fireMail(qtToSend);
			
		}
		
		if ( remainder.intValue() > 0 ) {
			
			// envia o restante
			fireMail(remainder.intValue());
		}
		
	}
	
	static int count = 0;
	
	private static void fireMail( final int qtde ) {
		long timeToSleep = 2000;	// 2 segundos
		
		try {
			
			Thread.sleep(timeToSleep);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// TODO chamar o metodo de envio de email
				
				System.out.println(++count + "-   " + qtde);
				
			}
		});

		thread.start();
		
	}
	
	
	public static void main(String args[]) {
//		ApplicationContext appCtx = new ClassPathXmlApplicationContext(
//				"classpath:/META-INF/spring/jboss-as-spring-mvc-context.xml");
//
//		SimpleEmail enviaEmailSimples = (SimpleEmail) appCtx
//				.getBean("simpleMail");
//		enviaEmailSimples.enviar();

		doPartitionMail();
		
	}

}