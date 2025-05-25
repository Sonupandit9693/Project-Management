package com.sonu.controller;


import com.sonu.model.Chat;
import com.sonu.model.Project;
import com.sonu.model.User;
import com.sonu.response.MessageResponse;
import com.sonu.service.ProjectService;
import com.sonu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Project>> getProject(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String token
    ) throws Exception {

        User user = userService.findUserProfileByToken(token);

        List<Project> projects = projectService.getProjectByTeam(user, category, tag);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String token
    ) throws Exception {

        User user = userService.findUserProfileByToken(token);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String token,
            @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserProfileByToken(token);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String token,
            @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserProfileByToken(token);
        Project updatedUproject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updatedUproject, HttpStatus.OK);
    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String token
    ) throws Exception {
        User user = userService.findUserProfileByToken(token);
        projectService.deleteProject(projectId, user.getId());

        MessageResponse messageResponse = new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> serachProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String token
    ) throws Exception {
        User user = userService.findUserProfileByToken(token);
        List<Project> projects = projectService.serachProjecs(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String token
    ) throws Exception {
        User user = userService.findUserProfileByToken(token);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
