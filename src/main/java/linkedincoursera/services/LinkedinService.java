package linkedincoursera.services;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.*;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by harsh on 4/20/15.
 */
@Component
public class LinkedinService {
    private LinkedIn linkedIn;
    private LinkedInProfile linkedInProfile;
    private LinkedInProfileFull linkedInProfileFull;
    @Inject
    private ConnectionRepository connectionRepository;
    public LinkedIn getLinkedIn() {
        return linkedIn;
    }
    public LinkedInProfile getLinkedInProfile() {
        return linkedInProfile;
    }
    public LinkedInProfileFull getLinkedInProfileFull() {
        return linkedInProfileFull;
    }

    public void setApi(String access_token) {
        this.linkedIn = new LinkedInTemplate(access_token);
        this.linkedInProfile = linkedIn.profileOperations().getUserProfile();
        this.linkedInProfileFull = linkedIn.profileOperations().getUserProfileFull();
    }
    public List<String> getSkillSet() {
        return linkedInProfileFull.getSkills();
    }
    public List<Education> getEducations() {
        return linkedInProfileFull.getEducations();

    }
}
