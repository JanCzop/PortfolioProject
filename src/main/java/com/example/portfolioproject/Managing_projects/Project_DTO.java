package com.example.portfolioproject.Managing_projects;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class Project_DTO {
    private String name;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private double priority;

    public Project_DTO(String name, String description, LocalDate start_date, LocalDate end_date, double priority) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Project_DTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", priority=" + priority +
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
}
