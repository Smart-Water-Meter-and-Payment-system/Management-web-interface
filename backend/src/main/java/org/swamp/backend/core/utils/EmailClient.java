package org.swamp.backend.core.utils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.shared.CustomLogger;

public class EmailClient {
	private static String SENDER_ADDRESS = "golderalikaya@gmail.com";// change with your sender email
	private static String SENDER_PASSWORD = "vxarbgaqcjzzedvq";// change with your sender password
	private static String SMTP_PORT = "587";
	private static String SMTP_HOST = "smtp.gmail.com";

    private static String username;
    private static String password;

    private static Properties prop;

    private static Session session;

    public static void initMailService() {
        CustomLogger.log(EmailClient.class, "Initialising mail session...");

        if (session == null) {
            CustomLogger.log(EmailClient.class, "Initialising mail session...");

            prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", SMTP_HOST);
            prop.put("mail.smtp.port", SMTP_PORT);
            prop.put("mail.smtp.ssl.trust", "*");
            prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

            session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_ADDRESS, SENDER_PASSWORD);
                }
            });
            
            username=SENDER_ADDRESS;
            password=SENDER_PASSWORD;
        }
    }

    public static void main(String... args) {
        try {
            new EmailClient()
                    .sendMail("ttamalecollins@gmail.com", "TEST EMAIL", "This is my <b>Test</b> email");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(String toAddress, String subject, String body) throws Exception {
        initMailService();
        CustomLogger.log(EmailClient.class, "Sending email...");

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(HtmlTemplate.buildTeplatedEmail(subject, body), "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message, username, password);
    }

    public static void sendMails(List<String> toAddresses, String subject, String body,String cc) {
        initMailService();
        CustomLogger.log(EmailClient.class, "Sending emails...");

        for (String toAddress : toAddresses) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
                message.setSubject(subject);

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(HtmlTemplate.buildTeplatedEmail(subject, body), "text/html; charset=utf-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);

                Transport.send(message, username, password);
            } catch (Exception ex) {
                Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private File getFile() throws Exception {
        URI uri = this.getClass()
                .getClassLoader()
                .getResource("attachment.txt")
                .toURI();
        return new File(uri);
    }
}
