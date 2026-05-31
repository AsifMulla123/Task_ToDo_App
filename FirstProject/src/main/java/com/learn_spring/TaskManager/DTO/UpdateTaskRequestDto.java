package com.learn_spring.TaskManager.DTO;

import com.learn_spring.TaskManager.Entity.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateTaskRequestDto {

    @NotBlank(message = "Title cannot be blank.")
    @Size(max = 50)
    private String title;

    @Size(max = 500)
    private String description;

    private TaskStatus status;

    @Future(message = "Due date should be in future!")
    private LocalDateTime dueDate;


}
