package com.contevent.backend.controller;

import com.contevent.backend.model.UserGroup;
import com.contevent.backend.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<UserGroup> createGroup(@RequestBody Map<String, String> body) {
        UserGroup group = new UserGroup();
        group.setName(body.get("name"));
        group.setDescription(body.get("description"));
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.create(group));
    }
}