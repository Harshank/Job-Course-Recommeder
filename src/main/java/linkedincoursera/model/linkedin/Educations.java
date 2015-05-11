package linkedincoursera.model.linkedin;

import java.util.Date;

/**
 * Created by harsh on 4/18/15.
 */
public class Educations {
    private final String activities;

    private final String degree;

    private String fieldOfStudy;

    private String id;

    private String notes;

    private String schoolName;

    private Date startDate;

    private Date endDate;

    public Educations(String activities, String degree, String fieldOfStudy, String id, String notes,
                     String schoolName, Date startDate, Date endDate) {
        this.activities = activities;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.id = id;
        this.notes = notes;
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getActivities() {
        return activities;
    }

    public String getDegree() {
        return degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public String getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
