package com.example.portfolioproject.Managing_projects;

import com.example.portfolioproject.Entities.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class Project_controller {

    private final Project_service project_service;

    public Project_controller(Project_service project_service) {
        this.project_service = project_service;
    }

    @PostMapping("create/blank")
    public ResponseEntity<Project> create_blank_project(@RequestBody Project_DTO project_dto) {
        Project project = project_service.create_blank_project(project_dto);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PostMapping("create/managed")
    public ResponseEntity<Project> create_managed_project(@RequestBody Project_DTO project_dto, @RequestParam UUID manager_id) {
        Project project = project_service.create_managed_project(project_dto, manager_id);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<Project> get_project_by_id(@PathVariable Long project_id) {
        Project project = project_service.get_project_by_id(project_id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> get_all_projects() {
        List<Project> projects = project_service.get_all_projects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping("/{project_id}")
    public ResponseEntity<Project> update_project(@PathVariable Long project_id, @RequestBody Project_DTO project_dto) {
        Project project = project_service.update_project(project_id, project_dto);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<String> delete_project(@PathVariable Long project_id) {
        project_service.delete_project(project_id);
        return new ResponseEntity<>("Successfully deleted Project with ID: " + project_id, HttpStatus.OK);
    }

    @PostMapping("/{project_id}/members")
    public ResponseEntity<String> add_member_to_project(@PathVariable Long project_id, @RequestParam UUID user_id) {
        project_service.add_member_to_project(project_id, user_id);
        return new ResponseEntity<>("Successfully added member to Project with ID: " + project_id, HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/projects")
    public ResponseEntity<List<Project>> get_projects_of_user(@PathVariable UUID user_id) {
        List<Project> projects = project_service.get_projects_of_user(user_id);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

}
