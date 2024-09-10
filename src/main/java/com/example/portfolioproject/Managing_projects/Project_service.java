package com.example.portfolioproject.Managing_projects;

import com.example.portfolioproject.Entities.Project;
import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Exceptions.Exc_null_data;
import com.example.portfolioproject.Repositories.Project_repository;
import com.example.portfolioproject.Repositories.User_repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Project_service {
    private final Project_repository project_repository;
    private final User_repository user_repository;

    public Project_service(Project_repository project_repository, User_repository user_repository) {
        this.project_repository = project_repository;
        this.user_repository = user_repository;
    }

    public Project create_blank_project(Project_DTO project_dto) {
        if(project_dto == null) throw new Exc_null_data("Null data!");
        return project_repository.save(new Project(
                project_dto.getName(),
                project_dto.getDescription(),
                project_dto.getStart_date(),
                project_dto.getEnd_date(),
                project_dto.getPriority()
        ));
    }

    public Project create_managed_project(Project_DTO project_dto, UUID manager_id) {
        if(project_dto == null || manager_id == null) throw new Exc_null_data("Null data!");
        User manager = user_repository.findById(manager_id)
                .orElseThrow(() -> new Exc_entity_not_found("Manager with ID " + manager_id + " does not exist!"));
        return project_repository.save(new Project(
                project_dto.getName(),
                project_dto.getDescription(),
                project_dto.getStart_date(),
                project_dto.getEnd_date(),
                manager,
                project_dto.getPriority()
        ));
    }

    public Project get_project_by_id(Long project_id) {
        if(project_id == null) throw new Exc_null_data("Null data!");
        return project_repository.findById(project_id)
                .orElseThrow(() -> new Exc_entity_not_found("Project with ID " + project_id + " does not exist!"));
    }

    public List<Project> get_all_projects() {
        return project_repository.findAll();
    }

    public Project update_project(Long project_id, Project_DTO project_dto) {
        if(project_id == null || project_dto == null) throw new Exc_null_data("Null data!");
        Project project = project_repository.findById(project_id)
                .orElseThrow(() -> new Exc_entity_not_found("Project with ID " + project_id + " does not exist!"));
        project.setName(project_dto.getName());
        project.setDescription(project_dto.getDescription());
        project.setStart_date(project_dto.getStart_date());
        project.setDue_date(project_dto.getEnd_date());
        project.setPriority(project_dto.getPriority());
        return project_repository.save(project);
    }

    public void delete_project(Long project_id) {
        if(project_id == null) throw new Exc_null_data("Null data!");
        Project project = project_repository.findById(project_id)
                .orElseThrow(() -> new Exc_entity_not_found("Project with ID " + project_id + " does not exist!"));
        project_repository.delete(project);
    }

    public void add_member_to_project(Long project_id, UUID user_id) {
        if(user_id == null || project_id == null) throw new Exc_null_data("Null data!");
        Project project = project_repository.findById(project_id)
                .orElseThrow(() -> new EntityNotFoundException("Project with ID " + project_id + " does not exist!"));
        User user = user_repository.findById(user_id)
                .orElseThrow(() -> new Exc_entity_not_found("User with ID " + user_id + " does not exist!"));
        project.getUsers().add(user);
        project_repository.save(project);
    }

    public List<Project> get_projects_of_user(UUID user_id) {
        if(user_id == null) throw new Exc_null_data("Null data!");
        User user = user_repository.findById(user_id)
                .orElseThrow(() -> new Exc_entity_not_found("User with ID " + user_id + " does not exist!"));
        return project_repository.findAllByUsersContains(user);
    }
}
