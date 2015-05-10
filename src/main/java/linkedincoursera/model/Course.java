package linkedincoursera.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by harsh on 4/18/15.
 */
public class Course {

    private Integer id;
    private String shortName;
    private String name;
    private String language;
    private Category links;
    private String photo;
    private String shortDescription;
    private String instructor;
    private String previewLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Category getLinks() {
        return links;
    }

    public void setLinks(Category links) {
        this.links = links;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }
}