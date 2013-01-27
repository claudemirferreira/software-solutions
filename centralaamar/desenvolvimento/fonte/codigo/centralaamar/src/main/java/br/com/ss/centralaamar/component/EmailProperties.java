package br.com.ss.centralaamar.component;

import java.util.Properties;

public class EmailProperties {

	public static Properties getProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		return props;
	}

}
