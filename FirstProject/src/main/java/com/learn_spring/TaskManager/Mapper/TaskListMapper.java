package com.learn_spring.TaskManager.Mapper;

import com.learn_spring.TaskManager.DTO.CreateTaskListRequestDto;
import com.learn_spring.TaskManager.DTO.TaskListResponseDto;
import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.DTO.UpdateTaskListRequestDto;
import com.learn_spring.TaskManager.Entity.TaskList;
import org.springframework.stereotype.Component;

@Component
public class TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public TaskList toEntity(CreateTaskListRequestDto createTaskListRequestDto) {
        TaskList taskList = new TaskList();
        taskList.setTitle(createTaskListRequestDto.getTitle());
        taskList.setDescription(createTaskListRequestDto.getDescription());

        return taskList;
    }

    public TaskList updateEntity(UpdateTaskListRequestDto updateDto, TaskList taskList) {

        taskList.setTitle(updateDto.getTaskListTitle());
        taskList.setDescription(updateDto.getTaskListDescription());
        taskList.setTaskListStatus(updateDto.getTaskListStatus());

        return taskList;

    }

    public TaskListResponseDto toTaskListResponseDto(TaskList taskList) {
        TaskListResponseDto taskListResponseDto = new TaskListResponseDto();

        taskListResponseDto.setId(taskList.getId());
        taskListResponseDto.setTitle(taskList.getTitle());
        taskListResponseDto.setDescription(taskList.getDescription());
        taskListResponseDto.setStatus(taskList.getTaskListStatus());
        taskListResponseDto.setUpdated(taskList.getUpdated());
        taskListResponseDto.setCreated(taskList.getCreated());
        taskListResponseDto.setTasks(
                taskList.getTasks()
                        .stream()
                        .map(taskMapper::toTaskResponseDto).toList());

        return taskListResponseDto;
    }


}
