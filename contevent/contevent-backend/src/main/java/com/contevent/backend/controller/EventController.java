package com.contevent.backend.controller;

import com.contevent.backend.model.CreatorType;
import com.contevent.backend.model.Event;
import com.contevent.backend.model.EventStatus;
import com.contevent.backend.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> listEvents(@RequestParam(required = false) String category) {
        return eventService.findAll(category);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Map<String, Object> body) {
        Event event = new Event();
        event.setTitle((String) body.get("title"));
        event.setDescription((String) body.get("description"));
        event.setCategory((String) body.get("category"));
        event.setLocation((String) body.get("location"));
        event.setMaxParticipants((int) body.get("maxParticipants"));

        Object creatorIdObj = body.get("creatorId");
        if (creatorIdObj instanceof Number n) {
            event.setCreatorId(n.longValue());
        }
        Object creatorTypeObj = body.get("creatorType");
        if (creatorTypeObj instanceof String s) {
            event.setCreatorType(CreatorType.valueOf(s));
        }

        Object dateTimeObj = body.get("dateTime");
        if (dateTimeObj instanceof String s) {
            String dt = s;
            if (dt.length() == 16) {
                dt = dt + ":00";
            }
            event.setDateTime(LocalDateTime.parse(dt));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(event));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Long eventId) {
        return eventService.findById(eventId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}