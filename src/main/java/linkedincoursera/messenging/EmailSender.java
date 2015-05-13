package linkedincoursera.messenging;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailSender {
    private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private final static String USERNAME = "linkedincoursera@gmail.com";
    private final static String PASSWORD = "cmpe273proj";

    private static Properties props;

    public EmailSender() {
        props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
    }

    public static void sendEmail(String to, String courseName, String courseHomelink, String courseInstructor) {
        String subject = MessageFormat.format(
                "Coursera reminder: {0} is starting in 7 days!", courseName);
        String body = MessageFormat.format("Register at {0}", courseHomelink);

        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }});

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(USERNAME));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(body);
            msg.setSentDate(new Date());
            Transport.send(msg);
        }catch (MessagingException e){ System.out.println(e.toString());}
    }
}
