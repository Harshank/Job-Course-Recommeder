package linkedincoursera.services;

import linkedincoursera.model.Categories;
import linkedincoursera.model.CategoryElement;
import linkedincoursera.model.Course;
import linkedincoursera.model.Elements;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by harsh on 4/20/15.
 */
@Component
public class CourseraService {

    public RestTemplate restTemplate = new RestTemplate();

    public List<Course> fetchCourses() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(headers);
        try{
            // {elements:[arrOfCourseObjects]} .. need to keep the names same to map
            ResponseEntity<Elements> response = restTemplate.exchange(
                    "https://api.coursera.org/api/catalog.v1/courses?includes=categories&fields=language,photo," +
                            "largeIcon,instructor,previewLink,shortDescription",
                    HttpMethod.GET, stringHttpEntity, Elements.class);
            System.out.println(response.getStatusCode());
            Elements elements= response.getBody();
            return elements.getElements();
        }catch(HttpClientErrorException e){
            throw new Exception(e);
        }
    }

    public List<Categories> getCategoriesList() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(headers);
        try{
            ResponseEntity<CategoryElement> response = restTemplate.exchange(
                    "https://api.coursera.org/api/catalog.v1/categories",
                    HttpMethod.GET, stringHttpEntity, CategoryElement.class);
            System.out.println(response.getStatusCode());
            CategoryElement elements= response.getBody();
            return elements.getElements();
        }catch(HttpClientErrorException e){
            throw new Exception(e);
        }
    }



    public void filterCourses(String searchParam) {

    }

    public ArrayList<Categories> filterCsCategories(ArrayList<Categories> categories) {
        /**
         * Filter computer science categories from list of categories
         */
        ArrayList<Categories> csCategories = new ArrayList<Categories>();
        for(Categories category : categories) {
            if(category.getShortName().startsWith("cs-")) {
                csCategories.add(category);
            }
        }
        return categories;
    }

    public ArrayList<Course> filterCsCourses(ArrayList<Course> courses, ArrayList<Categories> categories) {
        ArrayList<Course> csCourses = new ArrayList<Course>();

        ArrayList<Integer> csCategoryIds = new ArrayList<Integer>();
        ArrayList<Categories> csCategories = filterCsCategories(categories);
        for(Categories csCategory : csCategories) {
            csCategoryIds.add(csCategory.getId());
        }

        for(Course course : courses) {
            List<Integer> courseCategories = course.getLinks().getCategories();
            for(Integer courseCategory : courseCategories) {
                if(csCategoryIds.contains(courseCategory)) {
                    csCourses.add(course);
                    break;
                }
            }
        }

        return csCourses;
    }
}
