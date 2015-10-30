package com.devi.miscellnious;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void main(String[] args) {
		new SendMail("devikiran90@gmail.com", "asdfasdf");
	}
	
	public  SendMail(String receivermail,String activationid){

		final String username = "sumit.kumar6933@gmail.com";
		final String password = "$devikiran1263";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receivermail));
			message.setSubject("Testing Subject");
			message.setText("Dear user ,"
					+ "\n\n Please click the following link to activate your account "
					+ "http://10.30.84.34:8080/apartmentDemo/activation.html?uid="+activationid +"&umail="+receivermail);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}