package org.sp.news.model.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.sp.news.exception.EmailException;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	String host = "smtp.gmail.com";
	String user = "lecture230502@gmail.com";
	String password = "jptagcpgeismeion";
	Properties props = new Properties();
	

	public void send() throws EmailException{
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(user, password);
	            }	
		});
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("zino1187@naver.com"));
			message.setSubject("회원가입을 축하합니다");
			message.setContent("<h1>this is</h1> content", "text/html;charset=utf-8");
			
			Transport.send(message);
			System.out.println("Success Message Send");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("메일발송 실패", e);
		}
	}

}
