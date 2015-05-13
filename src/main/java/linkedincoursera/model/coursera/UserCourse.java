package linkedincoursera.model.coursera;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by melissa on 5/13/15.
 */
public class UserCourse implements Serializable {
    public BigInteger get_id() {
        return _id;
    }

    public void set_id(BigInteger _id) {
        this._id = _id;
    }

    @Id
    private BigInteger _id;
    private int id;
    private String name;
    private String homeLink;
    private String instructor;
    private int startDay;
    private int startMonth;
    private int startYear;
    private String userEmail;

    public UserCourse(BigInteger _id, int id, String name, String homeLink, String instructor, int startDay, int startMonth, int startYear, String userEmail) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.homeLink = homeLink;
        this.instructor = instructor;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.userEmail = userEmail;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeLink() {
        return homeLink;
    }

    public void setHomeLink(String homeLink) {
        this.homeLink = homeLink;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
