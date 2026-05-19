package com.learn_spring.TaskManager.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDto {

    private String message;
    private int status;
    LocalDateTime timestamp;
    public ErrorResponseDto(String message, int status, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
