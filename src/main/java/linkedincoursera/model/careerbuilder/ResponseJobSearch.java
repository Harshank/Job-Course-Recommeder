package linkedincoursera.model.careerbuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Created by melissa on 5/11/15.
 */
@JacksonXmlRootElement(localName = "ResponseJobSearch")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseJobSearch{

    private String errors;
    private String timeResponseSent;
    private double timeElapsed;
    private int totalPages;
    private int totalCount;
    private int firstItemIndex;
    private int lastItemIndex;
    private List<JobSearchResult> results;

    public String getErrors() {
        return errors;
    }

    @JsonProperty("Errors")
    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getTimeResponseSent() {
        return timeResponseSent;
    }

    @JsonProperty("TimeResponseSent")
    public void setTimeResponseSent(String timeResponseSent) {
        this.timeResponseSent = timeResponseSent;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    @JsonProperty("TimeElapsed")
    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("TotalPages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    @JsonProperty("TotalCount")
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getFirstItemIndex() {
        return firstItemIndex;
    }

    @JsonProperty("FirstItemIndex")
    public void setFirstItemIndex(int firstItemIndex) {
        this.firstItemIndex = firstItemIndex;
    }

    public int getLastItemIndex() {
        return lastItemIndex;
    }

    @JsonProperty("LastItemIndex")
    public void setLastItemIndex(int lastItemIndex) {
        this.lastItemIndex = lastItemIndex;
    }

    public List<JobSearchResult> getResults() {
        return results;
    }

    @JsonProperty("Results")
    public void setResults(List<JobSearchResult> results) {
        this.results = results;
    }

}
