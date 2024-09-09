package com.example.portfolioproject.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private String name;
    @Column(nullable = true, unique = false)
    private String description;
    @Column(nullable = false, unique = false)
    private LocalDate start_date;
    @Column(nullable = true, unique = false)
    private LocalDate due_date;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = true, unique = false)
    private User manager;

    @ManyToMany
    @JoinTable(
            name = "project_users",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @Column(nullable = true, unique = false)
    private double priority;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();


    public Project() {
    }

    public Project(String name, String description, LocalDate start_date, LocalDate due_date, double priority) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.due_date = due_date;
        this.priority = priority;
    }

    public Project(String name, String description, LocalDate start_date, LocalDate due_date, User manager, double priority) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.due_date = due_date;
        this.manager = manager;
        this.priority = priority;
    }

    public Project(String name, String description, LocalDate start_date, LocalDate due_date, User manager, Set<User> users, double priority, HashSet<Task> tasks) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.due_date = due_date;
        this.manager = manager;
        this.users = users;
        this.priority = priority;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", start_date=" + start_date +
                ", due_date=" + due_date +
                ", manager=" + manager +
                ", users=" + users +
                ", priority=" + priority +
                ", tasks=" + tasks +
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
