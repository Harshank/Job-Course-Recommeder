package linkedincoursera.messenging;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import linkedincoursera.model.coursera.Course;
import linkedincoursera.model.coursera.Session;
import linkedincoursera.model.coursera.UserCourseList;
import linkedincoursera.repository.UserCourseRepo;
import linkedincoursera.services.CourseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Scheduled(fixedRate = 3000)
    public void sendMessage() {
        KeyedMessage<String, String> msg2 = new KeyedMessage<String, String>(TOPIC, "test");
        producer.send(msg2);
        List<UserCourseList> userCourseLists = userCourseRepo.findAll();
        HashMap<Integer, Session> sessionMap = new HashMap<Integer, Session>();
        try {
            List<Session> sessions = courseraService.getSessions();
            for (Session session : sessions) {
                sessionMap.put(session.getId(), session);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Calendar cal = Calendar.getInstance();
        int currentDayOfYear = cal.get(Calendar.DAY_OF_YEAR);

        for (UserCourseList userCourseList : userCourseLists) {
            for(Course course : userCourseList.getCourses()) {
                try {
                    List<Integer> sessions = course.getLinks().getSessions();
                    for (Integer session : sessions) {
                        Session s = sessionMap.get(session);
                        int startD = s.getStartDay();
                        int startM = s.getStartMonth();
                        int startY = s.getStartYear();

                        String dateString = String.format("%02d%02d%04d", startM, startD, startY);
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = sdf.parse(dateString);
                        GregorianCalendar greg = new GregorianCalendar();
                        greg.setTime(date);
                        int startDayOfYear = greg.get(GregorianCalendar.DAY_OF_YEAR);
                        if (currentDayOfYear + 7 > startDayOfYear) {
                            String msg = course.getName();
                            KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC, msg);
                            producer.send(data);
                        }
                    }
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}