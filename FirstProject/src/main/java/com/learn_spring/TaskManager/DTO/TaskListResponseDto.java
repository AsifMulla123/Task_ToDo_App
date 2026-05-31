package com.learn_spring.TaskManager.DTO;

import com.learn_spring.TaskManager.Entity.TaskListStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskListResponseDto {

    private Long id;
    private String title;
    private String description;
    private TaskListStatus status;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<TaskResponseDto> tasks;
}
