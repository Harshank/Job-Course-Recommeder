package linkedincoursera.model.udacity;

import java.util.List;

/**
 * Created by melissa on 5/10/15.
 */
public class UdacityCourse {

    private String key;
    private String title;
    private String subtitle;
    private String homepage;
    private String short_summary;
    private String summary;
    private String expected_learning;
    private String syllabus;
    private String image;
    private String banner_image;
    private List<String> tracks;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_summary() {
        return short_summary;
    }

    public void setShort_summary(String short_summary) {
        this.short_summary = short_summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExpected_learning() {
        return expected_learning;
    }

    public void setExpected_learning(String expected_learning) {
        this.expected_learning = expected_learning;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }
}

