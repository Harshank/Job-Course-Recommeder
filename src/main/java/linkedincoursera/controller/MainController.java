package linkedincoursera.controller;


import linkedincoursera.model.careerbuilder.JobSearchResult;
import linkedincoursera.model.coursera.Categories;
import linkedincoursera.model.coursera.Course;
import linkedincoursera.model.udacity.UdacityCourse;
import linkedincoursera.repository.CourseraRepo;
import linkedincoursera.repository.UdacityRepo;
import linkedincoursera.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.linkedin.api.Education;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    UdacityService udacityService;
    @Autowired
    public StackoverflowService stackoverflowService;
    @Autowired
    public CareerBuilderService careerBuilderService;
    @Autowired
    public CourseraRepo courseraRepo;
    @Autowired
    public UdacityRepo udacityRepo;
    static String access_token="";
    @RequestMapping("/")
    public String index() {
    	String url = "https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id="+apikey+"&redirect_uri="+redirect_uri+"&state=987654321&scope=r_fullprofile";
        return "redirect:"+url;
    }

    @RequestMapping("/login")
    public String login() {
        return "greeting";
    }
    @RequestMapping("/main")
    public String homepage(Model model) {
        getDetails(model);
        return "main";
    }

    @RequestMapping("/auth/linkedin")
    public String authenticate(Model model, @RequestParam String code, @RequestParam String state) {
        access_token = authService.authorizeLinkedinByPost(code, redirect_uri, apikey, apisecret);
        getDetails(model);
        return "main";
    }
    @RequestMapping("/recommendation")
    public String recommendScreen(Model model) {
        LinkedInProfile basicProf = linkedinService.getLinkedInProfile();
        List<String> skillSet = linkedinService.getSkillSet();
        model.addAttribute("skills",skillSet);
        model.addAttribute("userName", basicProf.getFirstName() + " " + basicProf.getLastName());
        return "recommendations";
    }
    public void getDetails(Model model) {
        try {
            linkedinService.setApi(access_token);
            LinkedInProfile basicProf = linkedinService.getLinkedInProfile();
            String profilePhotoUrl = linkedinService.getLinkedInProfile().getProfilePictureUrl();
            List<String> skillSet = linkedinService.getSkillSet();
            List<Education> educationsList = linkedinService.getEducations();
            List<Course> courses = courseraService.fetchCourses();
            List<Categories> categoryList = courseraService.getCategoriesList();
            //courseraRepo.addCourses(courses);
            //courseraRepo.addCategories(categoryList);
            System.out.println(linkedinService.getLinkedInProfileFull().getPositions().get(0).getCompany().getName());
            System.out.println(courses.get(0).getLinks().getCategories());
            courseraService.filterCourses("java");
            if (basicProf != null)
                model.addAttribute("userName", basicProf.getFirstName() + " " + basicProf.getLastName());
            else model.addAttribute("userName", "Anonymous");
            model.addAttribute("profilePhotoUrl", profilePhotoUrl);
            model.addAttribute("education", educationsList);
            model.addAttribute("skills", skillSet);
            model.addAttribute("summary", linkedinService.getLinkedInProfile().getSummary());
            model.addAttribute("courses", courses);
            //udacityRepo.addCourses(udacityCourses);
            model.addAttribute("positions", linkedinService.getLinkedInProfileFull().getPositions());
//            linkedinService.getCompanyJobs(access_token);
            //List<QuestionCountSOF> qtnCountSof = stackoverflowService.fetchMostAskedQuestionsStackoverflow();
            //if(toBeInserted) {
//                courseraRepo.addCourses(courses);
//                courseraRepo.addCategories(categoryList);
//                StackOverflowRepo.addQuestionsCount(qtnCountSof);
            //    toBeInserted = false;
            //}

//            courses.forEach(course -> System.out.println(course.getId() + " " + course.getLanguage() + " " + course.getName() + " " + course.getShortName()));
//            System.out.println();
//            System.out.println("***************EDUCATION*******************");
//            educationsList.forEach(education -> System.out.println(education.getDegree() + " " + education.getFieldOfStudy() + " " + education.getSchoolName()));
//`            System.out.println("***************SKILLS*******************");
//            System.out.println(skillSet);
//            System.out.println("***************COURSES*******************");
//            toBeInserted = false;
            List<JobSearchResult> jobs = careerBuilderService.fetchJobs("Java");
            for(JobSearchResult job : jobs) {
                System.out.println(job.getJobTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/recommendations")
    public void recommendCourses(Model model, @RequestParam String skill) {
        try {
            List<Course> courseraCourses = courseraService.fetchCourses(skill);
            ArrayList<Course> filteredCourseraCourses = new ArrayList<Course>();
            for (Course course : courseraCourses) {
                if(course.getLanguage().equals("en")) {
                    filteredCourseraCourses.add(course);
                }
            }

            List<UdacityCourse> udacityCourses = udacityService.fetchCourses();
            List<UdacityCourse> filteredUdacityCourses = UdacityService.searchCourses(udacityCourses, skill);

            List<JobSearchResult> jobs = careerBuilderService.fetchJobs(skill);

            System.out.println("COURSERA:");
            for (Course course : filteredCourseraCourses) {
                System.out.println(course.getName());
            }

            System.out.println("UDACITY:");
            for(UdacityCourse course : filteredUdacityCourses) {
                System.out.println(course.getTitle());
            }

            System.out.println("CAREERBUILDER:");
            for(JobSearchResult job : jobs) {
                System.out.println(job.getJobTitle());
            }

            model.addAttribute("courseraCourses", filteredCourseraCourses);
            model.addAttribute("udacityCourses", filteredUdacityCourses);
            model.addAttribute("jobs", jobs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}