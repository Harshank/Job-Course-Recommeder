package linkedincoursera.messenging;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import linkedincoursera.model.coursera.UserCourse;
import linkedincoursera.repository.UserCourseRepo;
import linkedincoursera.services.CourseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class KafkaProducer {

    @Autowired
    CourseraService courseraService;
    @Autowired
    UserCourseRepo userCourseRepo;

    final static String TOPIC = "email_courses";

    private static Producer<String, String> producer;

    public KafkaProducer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        this.producer = producer;
    }

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        Calendar cal = Calendar.getInstance();
        int currentDayOfYear = cal.get(Calendar.DAY_OF_YEAR);

        List<UserCourse> courses = userCourseRepo.findAll();
        for(UserCourse course : courses) {
            try {
                int startD = course.getStartDay();
                int startM = course.getStartMonth();
                int startY = course.getStartYear();

                String dateString = String.format("%02d/%02d/%04d", startM, startD, startY);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date date = sdf.parse(dateString);
                GregorianCalendar greg = new GregorianCalendar();
                greg.setTime(date);
                int startDayOfYear = greg.get(GregorianCalendar.DAY_OF_YEAR);
                int currentYear = greg.get(GregorianCalendar.YEAR)+1;
                System.out.println(startY);
                System.out.println(currentYear);
                System.out.println(course.getUserEmail());
                if (((currentDayOfYear + 7 > startDayOfYear && startY == currentYear) || startY < currentYear)) {
                    String userEmail = course.getUserEmail();
                    String name = course.getName();
                    String homelink = course.getHomeLink();
                    String instructor = course.getInstructor();
                    String msg = MessageFormat.format("{0},{1},{2},{3}", userEmail, name, homelink, instructor);
                    KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC, msg);
                    producer.send(data);
                }
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}