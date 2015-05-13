package linkedincoursera.model.coursera;

import java.util.ArrayList;
import java.util.List;

public class UserCourseList {
    private int id;
    private List<Course> courses;

    public UserCourseList(int id) {
        this.id = id;
        courses = new ArrayList<Course>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
