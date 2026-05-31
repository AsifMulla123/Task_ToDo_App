package com.learn_spring.TaskManager.Service;
import com.learn_spring.TaskManager.DTO.CreateTaskRequestDto;
import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.DTO.UpdateTaskRequestDto;

import java.util.List;

public interface ITaskService{
    // for Tasks
    List<TaskResponseDto> findAllTasks(long listId);
    void createTask(long listId, CreateTaskRequestDto createTaskRequestDto);
    TaskResponseDto findWithId(long id);
    void updateTask(UpdateTaskRequestDto updateDto, long listId, long id);
    void deleteTaskById(long listId, long id);
    void deleteAllTasks(long listId);


}
