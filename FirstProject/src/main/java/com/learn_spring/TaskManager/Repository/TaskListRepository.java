package com.learn_spring.TaskManager.Repository;

import com.learn_spring.TaskManager.Entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,Long> {
}
