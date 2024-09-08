package com.example.portfolioproject.Repositories;

import com.example.portfolioproject.Entities.Project;
import com.example.portfolioproject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Project_repository extends JpaRepository<Project, Long> {
    Optional<Project> findById(Long project_id);
    List<Project> findAllByUsersContains(User user);
}
