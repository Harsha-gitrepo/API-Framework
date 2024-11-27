package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class getCourse {



    private String url;
    private String services;
    private String expertise;
    private courses courses;
    private String instructor;
    @JsonProperty("linkedIn")
    private String linkedIn;

    @JsonIgnoreProperties(ignoreUnknown = true)

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }
    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public POJO.courses getCourses() {
        return courses;
    }
    public void setCourses(POJO.courses courses) {
        this.courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedin() {
        return linkedIn;
    }
    public void setLinkedin(String linkedin) {
        this.linkedIn = linkedIn;
    }


}
