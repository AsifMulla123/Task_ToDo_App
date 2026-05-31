package com.learn_spring.TaskManager.Repository;

import com.learn_spring.TaskManager.Entity.Task;
import com.learn_spring.TaskManager.Entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getTasksByTaskListId(long listId);
}
