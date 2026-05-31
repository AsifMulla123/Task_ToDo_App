package com.learn_spring.TaskManager.DTO;

import com.learn_spring.TaskManager.Entity.TaskListStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTaskListRequestDto {

    @NotBlank(message = "Title Cannot be blank!")
    @Size(min = 3, max = 50, message = "Minimum 3 and maximum 50 characters are allowed!")
    private String taskListTitle;

    @Size(max = 500, message = "Maximum 500 characters are allowed!")
    private String taskListDescription;

    @NotNull(message = "Status can't be blank! It must be OPEN or CLOSED")
    private TaskListStatus taskListStatus;

}
