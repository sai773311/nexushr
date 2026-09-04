package com.nexushr.auth.recruitment.interview.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateEmail;

    private String interviewer;
    private String roundName;
    private String meetingUrl;
    private Integer rating;
    private String feedback;
    private String recommendation;

    private LocalDateTime interviewTime;

    @Enumerated(EnumType.STRING)
    private InterviewStatus status =
            InterviewStatus.SCHEDULED;

    public Interview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }

    public String getRoundName() { return roundName; }
    public void setRoundName(String value) { roundName = value; }
    public String getMeetingUrl() { return meetingUrl; }
    public void setMeetingUrl(String value) { meetingUrl = value; }
    public Integer getRating() { return rating; }
    public void setRating(Integer value) { rating = value; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String value) { feedback = value; }
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String value) { recommendation = value; }
}