package com.learn_spring.TaskManager.Repository;

import com.learn_spring.TaskManager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


}
