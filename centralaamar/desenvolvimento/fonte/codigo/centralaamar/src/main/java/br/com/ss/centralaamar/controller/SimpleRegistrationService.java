package br.com.ss.centralaamar.controller;

import java.util.HashMap;
import java.util.List;
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
import br.com.ss.centralaamar.model.entity.Membro;
import br.com.ss.centralaamar.service.IMembroService;
import br.com.ss.centralaamar.service.MembroService;

@Component("simpleRegistrationService")
@ManagedBean(name = "simpleRegistrationService")
@Scope("session")
public class SimpleRegistrationService implements RegistrationService {

	@Getter
	@Setter
	private Mail mail = new Mail();

	@Autowired
	private IMembroService service;

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

	public void register(Membro user) throws Exception {

		sendConfirmationEmail(user);
	}

	private void sendConfirmationEmail(final Membro user) throws Exception {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(user.getEmail());
				message.setFrom("centralaamar@gmail.com"); // could be
															// parameterized...
				Map<String, String> model = new HashMap<String, String>();
				model.put("user", user.getEmail());

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

		List<Membro> membros = service.search(new Membro());
		int count = 0;
		for (Membro membro : membros) {
			if (membro.getEmail() != null && membro.getEmail().length() > 5) {
				count++;
				System.out.println("email = " + count + " - "
						+ membro.getEmail());
				register(membro);
			}
		}
		System.out.println("total = " + count);
		//
		// User user = new User();
		// user.setAdress("claudemir.ferreira@fucapi.br");

		// user.setAdress("waltinhovale@hotmail.com");
		// register(user);
		//
		// user.setAdress("robsonrf@gmail.com");
		// register(user);

		// waltinhovale@hotmail.com
		// robsonrf@gmail.com
	}

}