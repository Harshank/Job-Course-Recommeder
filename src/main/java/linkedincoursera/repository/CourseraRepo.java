package linkedincoursera.repository;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import linkedincoursera.model.Categories;
import linkedincoursera.model.Course;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by harsh on 5/9/15.
 */

@Repository
public class CourseraRepo {
    MongoTemplate mongoTemplate;
    public CourseraRepo()
    {

        try
        {

            String mongoUri = "mongodb://harshank:password@ds047581.mongolab.com:47581/cmpe273"; //To connect using driver via URI
            MongoClientURI mongoLabUrl = new MongoClientURI(mongoUri);
            //To authenticate the user.
            MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(mongoLabUrl.getUsername(), mongoLabUrl.getDatabase(), mongoLabUrl.getPassword());
            //To connect to the mongo server.
            MongoClient mongoClient = new MongoClient(new ServerAddress("ds047581.mongolab.com",47581), Arrays.asList(mongoCredential));
            mongoTemplate = new MongoTemplate(mongoClient,mongoLabUrl.getDatabase());
        }
        catch(Exception e)
        {
        }
    }

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
