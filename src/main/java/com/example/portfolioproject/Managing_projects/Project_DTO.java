package com.example.portfolioproject.Managing_projects;

import com.example.portfolioproject.Entities.Entity_DTO_validator;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class Project_DTO implements Entity_DTO_validator {
    private String name;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private double priority;
    private UUID manager_id;
    private Set<UUID> users_id;
    private Set<Long> tasks_id;

    public Project_DTO(String name, String description, LocalDate start_date, LocalDate end_date, double priority, UUID manager_id, Set<UUID> users_id, Set<Long> tasks_id) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.priority = priority;
        this.manager_id = manager_id;
        this.users_id = users_id;
        this.tasks_id = tasks_id;
    }

    @Override
    public String toString() {
        return "Project_DTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", priority=" + priority +
                ", manager_id=" + manager_id +
                ", users_id=" + users_id +
                ", tasks_id=" + tasks_id +
                '}';
    }

    @Override
    public boolean validate_DTO() {
        return name != null &&
                start_date != null;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public UUID getManager_id() {
        return manager_id;
    }

    public void setManager_id(UUID manager_id) {
        this.manager_id = manager_id;
    }

    public Set<UUID> getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Set<UUID> users_id) {
        this.users_id = users_id;
    }

    public Set<Long> getTasks_id() {
        return tasks_id;
    }

    public void setTasks_id(Set<Long> tasks_id) {
        this.tasks_id = tasks_id;
    }
}
