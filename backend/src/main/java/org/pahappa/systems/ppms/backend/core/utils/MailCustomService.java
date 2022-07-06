package org.pahappa.systems.ppms.backend.core.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailCustomService {
	
	private String smtpHost = "smtp.gmail.com";
	private String smtpPort = "587";
	private String senderPassword = "";
	private String senderEmail = "";
	private String subject = "";
	private String body = "";
	private String toAddress = "";

	/**
	 * 
	 * 
	 * @param subject
	 * @param body
	 * @param toAddress
	 */
	public void setParameters(String subject, String body, String toAddress) {
		this.subject = subject;
		this.body = body;
		this.toAddress = toAddress;
	}
	
	public void sendHtmlEmail() {
        try {
        	Session session = getSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	private Session getSession() { 
        Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHost);
	    props.put("mail.smtp.port", smtpPort);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
 
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });
 
        return session;
    }
	
	/**
	 * @return the smtpHost
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * @param smtpHost the smtpHost to set
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * @return the smtpPort
	 */
	public String getSmtpPort() {
		return smtpPort;
	}

	/**
	 * @param smtpPort the smtpPort to set
	 */
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * @return the senderPassword
	 */
	public String getSenderPassword() {
		return senderPassword;
	}

	/**
	 * @param senderPassword the senderPassword to set
	 */
	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

	/**
	 * @return the senderEmail
	 */
	public String getSenderEmail() {
		return senderEmail;
	}

	/**
	 * @param senderEmail the senderEmail to set
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

}
