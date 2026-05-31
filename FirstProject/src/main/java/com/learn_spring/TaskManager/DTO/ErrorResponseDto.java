package com.learn_spring.TaskManager.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorResponseDto {

    private String apiPath;
    private String message;
    private int status;
    LocalDateTime timestamp;

    public ErrorResponseDto(String apiPath, String message, int status, LocalDateTime timestamp) {
        this.apiPath = apiPath;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
