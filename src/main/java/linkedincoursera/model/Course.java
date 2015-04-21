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
//    @JsonProperty("id")Integer id;
//    @JsonProperty("shortName")String shortName;
//    @JsonProperty("name")String name;
//    @JsonProperty("language")String language;
//    @JsonProperty("links")List<Links> links;

    private Integer id;
    private String shortName;
    private String name;
    private String language;


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
}