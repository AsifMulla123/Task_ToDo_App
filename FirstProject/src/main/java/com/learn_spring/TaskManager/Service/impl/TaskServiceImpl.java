package com.learn_spring.TaskManager.Service.impl;

import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.DTO.UpdateTaskRequestDto;
import com.learn_spring.TaskManager.Entity.TaskList;
import com.learn_spring.TaskManager.Exception.TaskNotFoundException;
import com.learn_spring.TaskManager.Mapper.TaskMapper;
import com.learn_spring.TaskManager.DTO.CreateTaskRequestDto;
import com.learn_spring.TaskManager.Entity.Task;
import com.learn_spring.TaskManager.Repository.TaskListRepository;
import com.learn_spring.TaskManager.Repository.TaskRepository;
import com.learn_spring.TaskManager.Service.ITaskService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository  taskListRepository;
    private final TaskMapper taskMapper;


    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskListRepository taskListRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskResponseDto> findAllTasks(long listId) {

        TaskList taskList = taskListRepository.findById(listId).
                orElseThrow(() -> new TaskNotFoundException("Task not found"));

        List<Task> tasks = taskRepository.getTasksByTaskListId(listId);

        return tasks.stream()
                .map(taskMapper::toTaskResponseDto).toList();
    }

    @Override
    public TaskResponseDto findWithId(long id) {

        Task task = taskRepository.findById(id).
                orElseThrow(()-> new TaskNotFoundException("Task not found by id: " + id));

        return taskMapper.toTaskResponseDto(task);

    }

    @Override
    public void createTask(long listId, CreateTaskRequestDto createTaskRequestDto) {

        TaskList taskList = taskListRepository.
                findById(listId).
                orElseThrow(()-> new TaskNotFoundException("TaskList not found by id: " + listId));

        Task task = taskMapper.toEntity(createTaskRequestDto);

        task.setTaskList(taskList);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(UpdateTaskRequestDto updateDto, long listId, long id) {
        TaskList taskList = taskListRepository.findById(listId).orElseThrow(
                ()-> new TaskNotFoundException("TaskList not found by id: " + listId));
        Task task = taskRepository.findById(id).
                orElseThrow(() -> new TaskNotFoundException("Task not found by id: " + id));

        taskMapper.updateEntity(updateDto, task);
        taskRepository.save(task);


    }

    @Override
    public void deleteTaskById(long listId, long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found by id: " + id));

        taskRepository.delete(task);

    }

    @Override
    public void deleteAllTasks(long listId) {

        TaskList taskList = taskListRepository.findById(listId).orElseThrow(
                () -> new TaskNotFoundException("TaskList not found by id: " + listId));
        taskRepository.deleteAll();
    }


}


