package linkedincoursera.messenging;

/**
 * Created by melissa on 5/11/15.
 */

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;

@Component
public class KafkaConsumer extends  Thread {
    final static String TOPIC = "email_courses";
    ConsumerConnector consumerConnector;

    public KafkaConsumer(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect","localhost:2181");
        properties.put("group.id","test-group");
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
        this.start();
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream =  consumerMap.get(TOPIC).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while(it.hasNext()) {
            sendEmail("mbromley56@gmail.com");
            System.out.println(new String(it.next().message()));
        }
    }

    private static void printMessages(ByteBufferMessageSet messageSet) throws UnsupportedEncodingException {
        for(MessageAndOffset messageAndOffset: messageSet) {
            ByteBuffer payload = messageAndOffset.message().payload();
            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);
            System.out.println(new String(bytes, "UTF-8"));
        }
    }

    private void sendEmail(String to) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "linkedincoursera@gmail.com";//
        final String password = "cmpe273proj";
        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("linkedincoursera@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("linkedincoursera@gmail.com", false));
            msg.setSubject("Hello");
            msg.setText("How are you");
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
        }catch (MessagingException e){ System.out.println("Erreur d'envoi, cause: " + e);}
    }
}