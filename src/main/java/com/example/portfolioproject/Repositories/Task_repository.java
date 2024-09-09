package com.example.portfolioproject.Repositories;

import com.example.portfolioproject.Entities.Project;
import com.example.portfolioproject.Entities.Task;
import com.example.portfolioproject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Task_repository  extends JpaRepository<Task,Long> {
    List<Task> findByProject(Project project);
    List<Task> findByUser(User user);
}
