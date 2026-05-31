package com.learn_spring.TaskManager.Controller;

import com.learn_spring.TaskManager.DTO.CreateTaskListRequestDto;
import com.learn_spring.TaskManager.DTO.TaskListResponseDto;
import com.learn_spring.TaskManager.DTO.UpdateTaskListRequestDto;
import com.learn_spring.TaskManager.Service.ITaskListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskListController {

    final ITaskListService taskListService;

    public TaskListController(ITaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping("/task_lists")
    public ResponseEntity <List<TaskListResponseDto>> getTaskLists() {

        List<TaskListResponseDto> taskList = taskListService.findAllTaskLists();

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/task_lists/{listId}")
    public ResponseEntity <TaskListResponseDto> getTaskListByID(@PathVariable Long listId) {
        TaskListResponseDto taskList = taskListService.findTaskListById(listId);

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }
    @PostMapping("/task_lists")
    public ResponseEntity<String> saveTaskList(@Valid @RequestBody CreateTaskListRequestDto createListDto) {

        taskListService.createTaskList(createListDto);
        return new ResponseEntity<>("task list created", HttpStatus.CREATED);
    }

    @PutMapping("/task_lists/{listId}")
    public ResponseEntity<String> updateTaskList(@Valid @RequestBody UpdateTaskListRequestDto updateListDto, @PathVariable Long listId) {
        taskListService.updateTaskList(updateListDto, listId);

        return new ResponseEntity<>("task list updated", HttpStatus.OK);
    }

    @DeleteMapping("/task_lists/{listId}")
    public ResponseEntity<String> deleteTaskList(@Valid @PathVariable Long listId) {
        taskListService.deleteTaskListByTaskListId(listId);

        return new ResponseEntity<>("task list deleted", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/task_lists")
    public ResponseEntity<String> deleteAllTaskLists() {

        taskListService.deleteAllTaskLists();
        return new ResponseEntity<>("task list deleted", HttpStatus.NO_CONTENT);
    }



}
