package linkedincoursera.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harsh on 5/9/15.
 */
public class Categories implements Serializable {
    private int id;
    private String shortName;
    private String name;
    private List links;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

//    public List getLinks() {
//        return links;
//    }
//
//    public void setLinks(List links) {
//        this.links = links;
//    }
}
