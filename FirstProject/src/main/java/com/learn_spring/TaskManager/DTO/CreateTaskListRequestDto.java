package com.learn_spring.TaskManager.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskListRequestDto {

    @Size(min = 3, max=50, message = "Minimum 3 and maximum 50 characters are allowed!")
    @NotBlank(message = "Title cannot be blank minimum 3 characters required!")
    private String title;

    @Size(max=500, message = "Maximum 500 characters are allowed!")
    private String description;

}
