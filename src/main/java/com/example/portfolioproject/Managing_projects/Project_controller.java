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
    public ResponseEntity<String> create_blank_project(@RequestBody Project_DTO project_dto){
        try {
            Project project = project_service.create_blank_project(project_dto);
            return new ResponseEntity<>("Successfully created Project with ID: " + project.getId(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("create/managed")
    public ResponseEntity<String> create_managed_project(@RequestBody Project_DTO project_dto, @RequestParam UUID manager_id){
        try {
            Project project = project_service.create_managed_project(project_dto,manager_id);
            return new ResponseEntity<>("Successfully created Project with ID: " + project.getId() +
                    "and set it's manager with ID: ", HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{project_id}")
    public ResponseEntity<Project> get_project_by_id(@PathVariable Long project_id){
        try {
            Project project = project_service.get_project_by_id(project_id);
            return new ResponseEntity<>(project,HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Project>> get_all_projects(){
        try {
            List<Project> projects = project_service.get_all_projects();
            return new ResponseEntity<>(projects,HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{project_id}")
    public ResponseEntity<String> update_project(@PathVariable Long project_id,Project_DTO project_dto){
        try {
            Project project = project_service.update_project(project_id,project_dto);
            return new ResponseEntity<>("Successfully updated Project with ID: " + project.getId(),HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{project_id}")
    public ResponseEntity<String> delete_project(@PathVariable Long project_id){
        try {
            project_service.delete_project(project_id);
            return new ResponseEntity<>("Successfully deleted Project with ID: " + project_id,HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/{project_id}/members")
    public ResponseEntity<String> add_member_to_project(@PathVariable Long project_id, @RequestParam UUID user_id){
        try {
            project_service.add_member_to_project(project_id,user_id);
            return new ResponseEntity<>("Successfully added member to Project with ID: " + project_id,HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<List<Project>> get_projects_of_user(@PathVariable UUID user_id){
        try {
            List<Project> projects = project_service.get_projects_of_user(user_id);
            return new ResponseEntity<>(projects,HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}
