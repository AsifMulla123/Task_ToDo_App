package com.learn_spring.TaskManager.Service;
import com.learn_spring.TaskManager.DTO.CreateTaskRequestDto;
import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.DTO.UpdateTaskRequestDto;

import java.util.List;

public interface ITaskService{

    List<TaskResponseDto> findAllTasks();
    void createTask(CreateTaskRequestDto createTaskRequestDto);
    TaskResponseDto findWithId(long id);
    void updateTask(UpdateTaskRequestDto updateDto, long id);
    void deleteTaskById(long id) ;

}
