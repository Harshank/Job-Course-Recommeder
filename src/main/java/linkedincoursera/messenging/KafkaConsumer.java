package linkedincoursera.messenging;

/**
 * Created by melissa on 5/11/15.
 */

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import linkedincoursera.model.coursera.Course;
import linkedincoursera.repository.CourseraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class KafkaConsumer extends  Thread {

    @Autowired
    CourseraRepo courseraRepo;
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
            String [] msg = new String(it.next().message()).split(",");
            String email = msg[0];
            String courseName = msg[1];
            String courseHomelink = msg[2];
            String courseInstructor = msg[3];
            EmailSender.sendEmail(email, courseName, courseHomelink, courseInstructor);
        }
    }
}