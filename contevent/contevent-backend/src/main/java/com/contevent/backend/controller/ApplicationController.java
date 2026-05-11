package com.contevent.backend.controller;

import com.contevent.backend.model.Application;
import com.contevent.backend.model.ApplicationStatus;
import com.contevent.backend.service.ApplicationService;
import com.contevent.backend.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApplicationController {

    private final ApplicationService applicationService;
    private final EventService eventService;

    public ApplicationController(ApplicationService applicationService, EventService eventService) {
        this.applicationService = applicationService;
        this.eventService = eventService;
    }

    @PostMapping("/events/{eventId}/applications")
    public ResponseEntity<?> applyToEvent(@PathVariable Long eventId, @RequestBody Map<String, Object> body) {
        return eventService.findById(eventId)
                .map(event -> {
                    Application application = new Application();
                    application.setEventId(eventId);
                    Object applicantIdObj = body.get("applicantId");
                    if (applicantIdObj instanceof Number n) {
                        application.setApplicantId(n.longValue());
                    }
                    application.setMessage((String) body.get("message"));
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(applicationService.create(application));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/events/{eventId}/applications")
    public ResponseEntity<List<Application>> getApplications(@PathVariable Long eventId) {
        return eventService.findById(eventId)
                .map(e -> ResponseEntity.ok(applicationService.findByEventId(eventId)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/events/{eventId}/applications/{applicationId}")
    public ResponseEntity<?> reviewApplication(@PathVariable Long eventId,
                                                @PathVariable Long applicationId,
                                                @RequestBody Map<String, String> body) {
        return applicationService.findById(applicationId)
                .map(application -> {
                    if (!application.getEventId().equals(eventId)) {
                        return ResponseEntity.<Application>notFound().build();
                    }
                    String statusStr = body.get("status");
                    if (statusStr != null) {
                        application.setStatus(ApplicationStatus.valueOf(statusStr));
                    }
                    return ResponseEntity.ok(applicationService.save(application));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}