package linkedincoursera.repository;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import linkedincoursera.model.Categories;
import linkedincoursera.model.Course;
import linkedincoursera.util.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by harsh on 5/9/15.
 */

@Component
public class CourseraRepo {
    static MongoTemplate mongoTemplate = DBConnection.getConnection();
    @Autowired
    MongoClient client;

    public void addCourses(List<Course> courses) {
        for(Course c:courses) {
            mongoTemplate.save(c);
        }
    }

    public void addCategories(List <Categories> categoryList) {
        for(Categories cat:categoryList) {
            mongoTemplate.save(cat);
        }
    }
}
