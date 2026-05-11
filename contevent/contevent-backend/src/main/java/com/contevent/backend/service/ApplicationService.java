package com.contevent.backend.service;

import com.contevent.backend.model.Application;
import com.contevent.backend.model.ApplicationStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final Map<Long, Application> applications = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Application> findByEventId(Long eventId) {
        return applications.values().stream()
                .filter(a -> a.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }

    public Optional<Application> findById(Long id) {
        return Optional.ofNullable(applications.get(id));
    }

    public Application create(Application application) {
        application.setId(idCounter.getAndIncrement());
        if (application.getStatus() == null) {
            application.setStatus(ApplicationStatus.PENDING);
        }
        applications.put(application.getId(), application);
        return application;
    }

    public Application save(Application application) {
        applications.put(application.getId(), application);
        return application;
    }
}