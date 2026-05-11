package com.contevent.backend.service;

import com.contevent.backend.model.Event;
import com.contevent.backend.model.EventStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final Map<Long, Event> events = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Event> findAll(String category) {
        return events.values().stream()
                .filter(e -> category == null || category.isBlank() || category.equals(e.getCategory()))
                .collect(Collectors.toList());
    }

    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(events.get(id));
    }

    public Event create(Event event) {
        event.setId(idCounter.getAndIncrement());
        if (event.getStatus() == null) {
            event.setStatus(EventStatus.OPEN);
        }
        events.put(event.getId(), event);
        return event;
    }

    public Event save(Event event) {
        events.put(event.getId(), event);
        return event;
    }
}