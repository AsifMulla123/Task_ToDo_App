package com.learn_spring.TaskManager.Controller;
import com.learn_spring.TaskManager.DTO.CreateTaskRequestDto;
import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.DTO.UpdateTaskRequestDto;
import com.learn_spring.TaskManager.Service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/task_lists/{listId}/tasks")
    public ResponseEntity <List<TaskResponseDto>> findAllTasks(@Valid @PathVariable Long listId) {

        List<TaskResponseDto> tasks = taskService.findAllTasks(listId);

        return  ResponseEntity.ok(tasks);
    }
    @GetMapping("/task_lists/{listId}/tasks/{id}")
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable long listId, @PathVariable long id) {

        TaskResponseDto task = taskService.findWithId(id);

        return ResponseEntity.ok(task);

    }

//    @PostMapping("/tasks")
//    public ResponseEntity<String> saveTask(@Valid @RequestBody CreateTaskRequestDto createTaskRequestDto) {
//        taskService.createTask(createTaskRequestDto);
//        return new ResponseEntity<>("task created!", HttpStatus.CREATED);
//    }

    @PostMapping("/task_lists/{listId}/tasks")
    public ResponseEntity<String> saveTask(@Valid @PathVariable long listId, @Valid @RequestBody CreateTaskRequestDto createListDto) {
        taskService.createTask(listId,createListDto);

        return new ResponseEntity<>("task created", HttpStatus.CREATED);
    }



    @PutMapping("/task_lists/{listId}/tasks/{id}")
    public ResponseEntity<String> updateTask(@PathVariable long listId, @PathVariable long id, @Valid @RequestBody UpdateTaskRequestDto updateDto) {
        taskService.updateTask(updateDto, listId, id);
        return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/task_lists/{listId}/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long listId, @PathVariable long id) {

        taskService.deleteTaskById(listId, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("task_lists/{listId}/tasks")
    public ResponseEntity<String> deleteAllTasks(@PathVariable long listId) {
        taskService.deleteAllTasks(listId);

        return ResponseEntity.noContent().build();
    }


}
