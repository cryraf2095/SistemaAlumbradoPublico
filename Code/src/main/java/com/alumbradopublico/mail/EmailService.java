package com.alumbradopublico.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.alumbradopublico.model.Employee;

public class EmailService {
	
	public static final String MAIL_ACCOUNT = "sistemaalumbradopublico@gmail.com";
	public static final String USER_NAME_MAIL_ACCOUNT = "sistemaalumbradopublico@gmail.com";
	public static final String PASSWORD_MAIL_ACCOUNT = "ydgufblxfanffvyr";
	public static final String MAIL_ACCOUNT_HOST = "smtp.gmail.com";
	public static final String SERVLET = "http://localhost:8080/SistemaAlumbradoPublico/activateAccount";
	
	private Properties props = new Properties();
	
	public EmailService() {
		this.props.put("mail.smtp.auth", "true");
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.host", MAIL_ACCOUNT_HOST);
		this.props.put("mail.smtp.port", "587");
	}
	
	public void sendMailRegisterEmployee(Employee employee) {
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME_MAIL_ACCOUNT, PASSWORD_MAIL_ACCOUNT);
			}
		});
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(MAIL_ACCOUNT));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employee.getEmail()));
			message.setSubject("Activación de cuenta para Sistema de Alumbrado Público");
			message.setContent("Bienvenido " + employee.getName() + " al sistema de alumbrado público. <br/>"
					+ "Usuario: " + "<br/>" 
					+ "Contraseña: " + employee.getPassword() + "<br/>"
					+ "Para activar tu cuenta da click en el siguiente link " 
					+ "<a href=\"" + SERVLET + "?email=" + employee.getEmail() + "\">Activar mi cuenta<a/>", "text/html");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
