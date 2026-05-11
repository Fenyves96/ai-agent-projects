package com.contevent.backend.service;

import com.contevent.backend.model.UserGroup;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GroupService {

    private final Map<Long, UserGroup> groups = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Optional<UserGroup> findById(Long id) {
        return Optional.ofNullable(groups.get(id));
    }

    public UserGroup create(UserGroup group) {
        group.setId(idCounter.getAndIncrement());
        groups.put(group.getId(), group);
        return group;
    }
}