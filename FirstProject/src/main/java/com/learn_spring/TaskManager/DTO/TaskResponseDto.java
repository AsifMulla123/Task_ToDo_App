package com.learn_spring.TaskManager.DTO;

import com.learn_spring.TaskManager.Entity.Task_Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;
    private Task_Status status;
    private LocalDateTime dueDate;
    private LocalDateTime created;
    private LocalDateTime updated;


}
