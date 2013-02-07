package br.com.ss.centralaamar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.mail.internet.MimeMessage;

import lombok.Getter;
import lombok.Setter;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.com.ss.centralaamar.component.FacesUtil;
import br.com.ss.centralaamar.component.Mail;
import br.com.ss.centralaamar.model.entity.User;

@Component("simpleRegistrationService")
@ManagedBean(name = "simpleRegistrationService")
@Scope("session")
public class SimpleRegistrationService implements RegistrationService {

	@Getter
	@Setter
	private Mail mail = new Mail();

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void register(User user) throws Exception {

		sendConfirmationEmail(user);
	}

	private void sendConfirmationEmail(final User user) throws Exception {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(user.getAdress());
				message.setFrom("centralaamar@gmail.com"); // could be
															// parameterized...
				Map<String, String> model = new HashMap<String, String>();
				model.put("user", user.getAdress());

				message.setSubject(getMail().getAssunto());

				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "emailBody.vm", model);
				message.setText(text, true);
			}
		};
		System.out.println("entrou no enviar email");
		this.mailSender.send(preparator);
		System.out.println("email enviado");
		FacesUtil.exibirMensagemSucesso("Email enviado com sucesso !");
	}

	public void enviarEmail() throws Exception {
		User user = new User();
		user.setAdress("alvaraesam@gmail.com");
		register(user);
		
//		user.setAdress("waltinhovale@hotmail.com");
//		register(user);
//		
//		user.setAdress("robsonrf@gmail.com");
//		register(user);
		
//		waltinhovale@hotmail.com
//		robsonrf@gmail.com
	}

}