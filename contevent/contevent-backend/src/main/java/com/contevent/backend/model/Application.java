package com.contevent.backend.model;

public class Application {
    private Long id;
    private Long eventId;
    private Long applicantId;
    private ApplicationStatus status;
    private String message;

    public Application() {}

    public Application(Long id, Long eventId, Long applicantId, ApplicationStatus status, String message) {
        this.id = id;
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.status = status;
        this.message = message;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}