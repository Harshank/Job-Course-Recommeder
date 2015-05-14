package linkedincoursera.model.careerbuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by melissa on 5/11/15.
 */
@JacksonXmlRootElement(localName = "JobSearchResult")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobSearchResult {
    private String company;
    private String description;
    private String employmentType;
    private String educationRequired;
    private String experienceRequired;
    private String jobDetailsURL;
    private String location;
    private String city;
    private String state;
    private String jobTitle;

    public String getCompany() {
        return company;
    }

    @JsonProperty("Company")
    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    @JsonProperty("EmploymentType")
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getEducationRequired() {
        return educationRequired;
    }

    @JsonProperty("EducationRequired")
    public void setEducationRequired(String educationRequired) {
        this.educationRequired = educationRequired;
    }

    public String getExperienceRequired() {
        return experienceRequired;
    }

    @JsonProperty("ExperienceRequired")
    public void setExperienceRequired(String experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public String getJobDetailsURL() {
        return jobDetailsURL;
    }

    @JsonProperty("JobDetailsURL")
    public void setJobDetailsURL(String jobDetailsURL) {
        this.jobDetailsURL = jobDetailsURL;
    }

    public String getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    @JsonProperty("JobTitle")
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj == null || obj.getClass() != this.getClass()) { return false; }
        JobSearchResult guest = (JobSearchResult) obj;
        return (jobTitle.equals(guest.jobTitle)||company.equals(guest.company));
    }
    public int hashCode() {
        return jobTitle.length()+company.length();
    }
    public String toString() {
        return jobTitle;
    }

}
