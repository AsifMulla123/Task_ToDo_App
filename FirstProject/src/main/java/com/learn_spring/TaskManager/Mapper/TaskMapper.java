package com.learn_spring.TaskManager.Mapper;

import com.learn_spring.TaskManager.DTO.CreateTaskRequestDto;
import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.Entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    // REQUEST DTO -> ENTITY
    public Task toEntity(CreateTaskRequestDto dto) {

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());

        return task;
    }

    // ENTITY -> RESPONSE DTO
    public TaskResponseDto toTaskResponseDto(Task task) {

        TaskResponseDto dto = new TaskResponseDto();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setCreated(task.getCreated());
        dto.setUpdated(task.getUpdated());

        return dto;
    }
}
