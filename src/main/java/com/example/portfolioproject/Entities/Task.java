package com.example.portfolioproject.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)

    private LocalDate creation_date;
    @Column(nullable = false)

    private LocalDate due_date;
    @Column(nullable = false)
    private LocalDate update_date;
    @Column(nullable = false)
    private Task_status task_status;

    public enum Task_status{
        NEW, IN_PROGRESS, COMPLETED, CANCELLED, PENDING, REVIEW, ON_HOLD, FAILED, DEFERRED
    }
    @Column(nullable = false)
    private double priority;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "project_id", referencedColumnName = "id")
    private Project project;

    public Task() {
    }

    public Task(String name, String description, LocalDate creation_date, LocalDate due_date, Task_status task_status, double priority) {
        this.name = name;
        this.description = description;
        this.creation_date = creation_date;
        this.due_date = due_date;
        this.update_date = due_date;
        this.task_status = task_status;
        this.priority = priority;
    }

    public Task(String name, String description, LocalDate creation_date, LocalDate due_date, Task_status task_status, double priority, User user, Project project) {
        this.name = name;
        this.description = description;
        this.creation_date = creation_date;
        this.due_date = due_date;
        this.update_date = due_date;
        this.task_status = task_status;
        this.priority = priority;
        this.user = user;
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creation_date=" + creation_date +
                ", due_date=" + due_date +
                ", update_date=" + update_date +
                ", task_status=" + task_status +
                ", priority=" + priority +
                ", user=" + user +
                ", project=" + project +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Task_status getTask_status() {
        return task_status;
    }

    public void setTask_status(Task_status task_status) {
        this.task_status = task_status;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
