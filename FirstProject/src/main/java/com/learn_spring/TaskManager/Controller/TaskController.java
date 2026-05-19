package com.learn_spring.TaskManager.Controller;
import com.learn_spring.TaskManager.DTO.CreateTaskRequestDto;
import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.Mapper.TaskMapper;
import com.learn_spring.TaskManager.Service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity <List<TaskResponseDto>> findAllTasks() {

        List<TaskResponseDto> tasks = taskService.findAllTasks();

        return  ResponseEntity.ok(tasks);
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable long id) {

        TaskResponseDto task = taskService.findWithId(id);

        return ResponseEntity.ok(task);

    }

    @PostMapping("/tasks")
    public ResponseEntity<String> saveTask(@Valid @RequestBody CreateTaskRequestDto createTaskRequestDto) {
        taskService.createTask(createTaskRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<String> updateTask(@PathVariable long id, @Valid @RequestBody CreateTaskRequestDto createTaskRequestDto) {
        taskService.updateTask(createTaskRequestDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {

        taskService.deleteTaskById(id);

        return ResponseEntity.noContent().build();

    }
}
