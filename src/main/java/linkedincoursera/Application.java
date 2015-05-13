package linkedincoursera;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import linkedincoursera.messenging.EmailSender;
import linkedincoursera.messenging.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.UnknownHostException;


@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@PropertySource(value = {"classpath:/properties/application.properties"},ignoreResourceNotFound = false)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    KafkaConsumer consumer() {
//        return new KafkaConsumer();
//    }

//    @Bean
//    EmailSender emailSender() { return new EmailSender(); }

    @Bean
    MongoClient client() throws UnknownHostException {
        return new MongoClient(new MongoClientURI("mongodb://harshank:password@ds047581.mongolab.com:47581/cmpe273"));
    }

}

