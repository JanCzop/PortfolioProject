package com.example.portfolioproject.Managing_tasks;


import com.example.portfolioproject.Entities.Project;
import com.example.portfolioproject.Entities.Task;
import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Exceptions.Exc_null_data;
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
        if(task_dto == null) throw new Exc_null_data("Null data!");
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

    public Task get_task(Long task_id){
        if(task_id == null) throw new Exc_null_data("Null data!");
        return task_repository.findById(task_id)
                .orElseThrow(() -> new Exc_entity_not_found("Task with ID: " + task_id + " not found!"));

    }
    public List<Task> get_all_tasks(){
        return task_repository.findAll();
    }
    public void delete_task(Long task_id){
        if(task_id == null) throw new Exc_null_data("Null data!");
        Task task = task_repository.findById(task_id)
                .orElseThrow(() -> new Exc_entity_not_found("Task with ID: " + task_id + " does not exist!"));
        task_repository.delete(task);
    }
    public Task update_task(Long task_id, Task_DTO task_dto){
        if(task_id == null || task_dto == null) throw new Exc_null_data("Null data!");
        Task task = task_repository.findById(task_id)
                .orElseThrow(() -> new Exc_entity_not_found("Task with ID: " + task_id + " does not exist!"));
        task.setName(task_dto.getName());
        task.setDescription(task.getDescription());
        task.setCreation_date(task_dto.getCreation_date());
        task.setDue_date(task_dto.getDue_date());
        task.setUpdate_date(task_dto.getUpdate_date());
        task.setTask_status(task_dto.getTask_status());

        User user = null;
        Project project = null;

        if (task_dto.getUser_id() != null)
            user = user_repository.findById(task_dto.getUser_id())
                    .orElseThrow(() -> new Exc_entity_not_found("User with ID: " + task_dto.getUser_id() + " does not exist!"));
        if (task_dto.getProject_id() != null)
             project = project_repository.findById(task_dto.getProject_id())
                    .orElseThrow(() -> new Exc_entity_not_found("Project with ID: " + task_dto.getProject_id() + " does not exist!"));

        task.setUser(user);
        task.setProject(project);

        return task_repository.save(task);
    }


}
