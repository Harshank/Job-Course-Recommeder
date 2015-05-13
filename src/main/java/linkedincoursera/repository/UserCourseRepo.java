package linkedincoursera.repository;

import linkedincoursera.model.coursera.UserCourseList;
import linkedincoursera.util.DBConnection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCourseRepo {
    static MongoTemplate mongoTemplate = DBConnection.getConnection();

    public void addUserCourseList(UserCourseList courses) {
        mongoTemplate.save(courses, "userCourses");
    }

    public List<UserCourseList> getUserCourseList(String id) {
        return mongoTemplate.find(new Query(Criteria.where("id").is(id)), UserCourseList.class);
    }

    public List<UserCourseList> findAll() {
        return mongoTemplate.findAll(UserCourseList.class);
    }
}
