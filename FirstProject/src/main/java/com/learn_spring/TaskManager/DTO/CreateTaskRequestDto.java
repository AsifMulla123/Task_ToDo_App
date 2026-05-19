package com.learn_spring.TaskManager.DTO;

import com.learn_spring.TaskManager.Entity.Task_Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTaskRequestDto {

    @NotBlank(message = "title cannot be blank")
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be empty!")
    private Task_Status status;

    @NotNull(message="DueDate cannot be empty!")
    @Future(message="DueDate should be in future!")
    private LocalDateTime dueDate;

}