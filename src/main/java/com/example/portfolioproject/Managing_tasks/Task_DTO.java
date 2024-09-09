package com.example.portfolioproject.Managing_tasks;

import com.example.portfolioproject.Entities.Project;
import com.example.portfolioproject.Entities.Task;
import com.example.portfolioproject.Entities.User;


import java.time.LocalDate;
import java.util.UUID;

public class Task_DTO {

    private String name;
    private String description;
    private LocalDate creation_date;
    private LocalDate due_date;
    private LocalDate update_date;
    private Task.Task_status task_status;
    private double priority;
    private UUID user_id;
    private Long project_id;

    public Task_DTO(String name, String description, LocalDate creation_date, LocalDate due_date, LocalDate update_date,
                    Task.Task_status task_status, double priority, UUID user_id, Long project_id) {
        this.name = name;
        this.description = description;
        this.creation_date = creation_date;
        this.due_date = due_date;
        this.update_date = update_date;
        this.task_status = task_status;
        this.priority = priority;
        this.user_id = user_id;
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "Task_DTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creation_date=" + creation_date +
                ", due_date=" + due_date +
                ", update_date=" + update_date +
                ", task_status=" + task_status +
                ", priority=" + priority +
                ", user=" + user_id +
                ", project=" + project_id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public LocalDate getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDate update_date) {
        this.update_date = update_date;
    }

    public Task.Task_status getTask_status() {
        return task_status;
    }

    public void setTask_status(Task.Task_status task_status) {
        this.task_status = task_status;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }
}
