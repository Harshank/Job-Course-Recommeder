package linkedincoursera.controller;


import linkedincoursera.model.Categories;
import linkedincoursera.model.Course;
import linkedincoursera.model.QuestionCountSOF;
import linkedincoursera.repository.CourseraRepo;
import linkedincoursera.repository.StackOverflowRepo;
import linkedincoursera.services.AuthorizationService;
import linkedincoursera.services.CourseraService;
import linkedincoursera.services.LinkedinService;
import linkedincoursera.services.StackoverflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.linkedin.api.Education;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PropertySource(value = {"classpath:/properties/application.properties"},ignoreResourceNotFound = false)
public class MainController {
    static boolean toBeInserted= true;
    @Value("${api.key}")
    private String apikey;
    @Value("${api.secret}")
    private String apisecret;
    private String redirect_uri = "http%3A%2F%2Flocalhost%3A8080%2Fauth%2Flinkedin";
    @Autowired
    AuthorizationService authService;
    @Autowired
    LinkedinService linkedinService;
    @Autowired
    CourseraService courseraService;
    @Autowired
    public StackoverflowService stackoverflowService;
    @Autowired
    public CourseraRepo courseraRepo;
    @RequestMapping("/")
    public String index() {
    	String url = "https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id="+apikey+"&redirect_uri="+redirect_uri+"&state=987654321&scope=r_fullprofile";
        return "redirect:"+url;
    }

    @RequestMapping("/login")
    public String login() {
        return "greeting";
    }

    @RequestMapping("/auth/linkedin")
    public String authenticate(Model model, @RequestParam String code, @RequestParam String state) {
        String access_token = authService.authorizeLinkedinByPost(code, redirect_uri, apikey, apisecret);
        try {
            linkedinService.setApi(access_token);
            LinkedInProfile basicProf = linkedinService.getLinkedInProfile();
            List<String> skillSet = linkedinService.getSkillSet();
            List <Education> educationsList = linkedinService.getEducations();
            List<Course> courses = courseraService.fetchCourses();
            List<Categories> categoryList = courseraService.getCategoriesList();
            //courseraRepo.addCourses(courses);
            //courseraRepo.addCategories(categoryList);
            //List<Categories> categories = courseraRepo.findAll();
            //for(Categories category : categories) {
            //    System.out.println(category.getName());
            //}
            //List<Categories> csCategories = courseraRepo.findCategories("shortName", "^cs-");
            //for(Categories category : csCategories) {
            //    System.out.println(category.getName());
            //}
            Query query = new Query(Criteria.where("shortDescription").regex("java|Java").and("language").is("en"));
            List<Course> javaCourses = courseraRepo.findCourses(query);
            for(Course course :javaCourses) {
                System.out.println(course.getName());
            }
            System.out.println(courses.get(0).getLinks().getCategories());
            courseraService.filterCourses("java");
            if(basicProf!=null)
                model.addAttribute("userName",basicProf.getFirstName()+" "+basicProf.getLastName());
            else model.addAttribute("userName","Anonymous");
            model.addAttribute("education", educationsList);
            model.addAttribute("skills", skillSet);
            model.addAttribute("courses", courses);
//            linkedinService.getCompanyJobs(access_token);
            List<QuestionCountSOF> qtnCountSof = stackoverflowService.fetchMostAskedQuestionsStackoverflow();
            if(toBeInserted) {
                courseraRepo.addCourses(courses);
                courseraRepo.addCategories(categoryList);
                StackOverflowRepo.addQuestionsCount(qtnCountSof);
                toBeInserted = false;
            }
            System.out.println(qtnCountSof);

//            courses.forEach(course -> System.out.println(course.getId() + " " + course.getLanguage() + " " + course.getName() + " " + course.getShortName()));
//            System.out.println();
//            System.out.println("***************EDUCATION*******************");
//            educationsList.forEach(education -> System.out.println(education.getDegree() + " " + education.getFieldOfStudy() + " " + education.getSchoolName()));
//`            System.out.println("***************SKILLS*******************");
//            System.out.println(skillSet);
//            System.out.println("***************COURSES*******************");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }

}