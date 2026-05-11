package com.contevent.backend.model;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime dateTime;
    private String location;
    private int maxParticipants;
    private Long creatorId;
    private CreatorType creatorType;
    private EventStatus status;

    public Event() {}

    public Event(Long id, String title, String description, String category,
                 LocalDateTime dateTime, String location, int maxParticipants,
                 Long creatorId, CreatorType creatorType, EventStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.dateTime = dateTime;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.creatorId = creatorId;
        this.creatorType = creatorType;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
    public Long getCreatorId() { return creatorId; }
    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }
    public CreatorType getCreatorType() { return creatorType; }
    public void setCreatorType(CreatorType creatorType) { this.creatorType = creatorType; }
    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }
}