package com.example.portfolioproject.Managing_tasks;


import com.example.portfolioproject.Entities.Project;
import com.example.portfolioproject.Entities.Task;
import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Repositories.Project_repository;
import com.example.portfolioproject.Repositories.Task_repository;
import com.example.portfolioproject.Repositories.User_repository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Task_service {

    private final Task_repository task_repository;
    private final Project_repository project_repository;
    private final User_repository user_repository;

    public Task_service(Task_repository task_repository, Project_repository project_repository, User_repository user_repository) {
        this.task_repository = task_repository;
        this.project_repository = project_repository;
        this.user_repository = user_repository;
    }


    public Task create_task(Task_DTO task_dto){
        if(task_dto.getProject_id() == null)
            throw new IllegalArgumentException("Project must be specified!");
        Project project = project_repository.findById(task_dto.getProject_id())
                .orElseThrow(() -> new Exc_entity_not_found("Project with ID: " + task_dto.getProject_id() + " does not exist!"));
        User user = null;
        if(task_dto.getUser_id() != null)
            user = user_repository.findById(task_dto.getUser_id())
                    .orElse(null);
        Task task = new Task(
                task_dto.getName(),
                task_dto.getDescription(),
                task_dto.getCreation_date(),
                task_dto.getDue_date(),
                task_dto.getTask_status(),
                task_dto.getPriority(),
                user,
                project
        );
        return task_repository.save(task);
    }

    public ResponseEntity<Task> get_task(Long task_id){
        return null;
    }
    public ResponseEntity<List<Task>> get_all_tasks(){
        return null;
    }
    public ResponseEntity<String> delete_task(){
        return null;
    }
    public ResponseEntity<String> update_task(){
        return null;
    }


}
