package linkedincoursera.services;

import linkedincoursera.model.Course;
import linkedincoursera.model.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by harsh on 4/20/15.
 */
@Component
public class CourseraService {

    public RestTemplate restTemplate = new RestTemplate();
    public List<Course> fetchCourses(String searchTerm) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(headers);
        try{
            // {elements:[arrOfCourseObjects]} .. need to keep the names same to map
            ResponseEntity<Elements> response = restTemplate.exchange("https://api.coursera.org/api/catalog.v1/courses?fields=language&q=search&query="+searchTerm, HttpMethod.GET, stringHttpEntity, Elements.class);
            Elements elements= response.getBody();
            return elements.getElements();
        }catch(HttpClientErrorException e){
            throw new Exception(e);
        }
    }
}
