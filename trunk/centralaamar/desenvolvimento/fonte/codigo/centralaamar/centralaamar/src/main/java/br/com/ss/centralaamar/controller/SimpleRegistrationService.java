package br.com.ss.centralaamar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.mail.internet.AddressException;
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

@Component("simpleRegistrationService")
@ManagedBean(name = "simpleRegistrationService")
@Scope("session")
public class SimpleRegistrationService implements RegistrationService {

	@Autowired
	private IMembroService service;

	@Getter
	@Setter
	private List<Membro> resultList = new ArrayList<Membro>();

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

	public void register(Membro membro) throws Exception {

		sendConfirmationEmail(membro);
	}

	private void sendConfirmationEmail(final Membro membro) throws Exception {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(membro.getEmail());
				message.setFrom("claudemirramosferreira@gmail.com"); // could be
															// parameterized...
				Map<String, String> model = new HashMap<String, String>();
				model.put("user", membro.getEmail());

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

	public void enviarEmail() {
		int count = 0;

		this.resultList = service.search(new Membro());
		Membro membro = new Membro();
		for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
			membro = (Membro) iterator.next();
			if (membro.getEmail() != null && membro.getIdMembro() > 25
					&& membro.getEmail().length() > 6) {
				try {
					register(membro);
				} catch (AddressException adde) {
					System.out
							.println("===================== inicio ocorreu um erro =====================");
					System.out.println(membro.getIdMembro() + " - "
							+ membro.getEmail());
					System.out
							.println("===================== fim ocorreu um erro =====================");
					adde.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				count++;
				System.out.println(" email " + count + " " + membro.getEmail());
			}

			System.out.println("total de email enviados = " + count);

		}

	}

}