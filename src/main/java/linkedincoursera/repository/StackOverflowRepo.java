package linkedincoursera.repository;

import linkedincoursera.model.stackoverflow.QuestionCountSOF;
import linkedincoursera.util.DBConnection;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Created by harsh on 5/9/15.
 */
public class StackOverflowRepo {
    static MongoTemplate mongoTemplate = DBConnection.getConnection();

    public static void addQuestionsCount(List<QuestionCountSOF> qtnCountSof) {
        for(QuestionCountSOF q : qtnCountSof) {
            mongoTemplate.save(q,"sofQuestions");
        }
    }
}
