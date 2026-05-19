package com.learn_spring.TaskManager.Service.impl;

import com.learn_spring.TaskManager.DTO.TaskResponseDto;
import com.learn_spring.TaskManager.Exception.TaskNotFoundException;
import com.learn_spring.TaskManager.Mapper.TaskMapper;
import com.learn_spring.TaskManager.DTO.CreateTaskRequestDto;
import com.learn_spring.TaskManager.Entity.Task;
import com.learn_spring.TaskManager.Repository.TaskRepository;
import com.learn_spring.TaskManager.Service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskResponseDto> findAllTasks(){

        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(taskMapper::toTaskResponseDto).toList();
    }

    @Override
    public TaskResponseDto findWithId(long id) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found by id: " + id));

        return taskMapper.toTaskResponseDto(task);

    }

    @Override
    public void createTask(CreateTaskRequestDto createTaskRequestDto) {

        Task task = taskMapper.toEntity(createTaskRequestDto);

        if(task.getId() == null){
            task.setCreated(LocalDateTime.now());
        }
        task.setUpdated(LocalDateTime.now());
        taskRepository.save(task);
    }
    @Override
    public void updateTask(CreateTaskRequestDto createTaskRequestDto, long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found by id: " + id));

        task.setTitle(createTaskRequestDto.getTitle());
        task.setDescription(createTaskRequestDto.getDescription());
        task.setStatus(createTaskRequestDto.getStatus());
        task.setDueDate(createTaskRequestDto.getDueDate());
        task.setUpdated(LocalDateTime.now());

        taskRepository.save(task);
    }


    @Override
    public void deleteTaskById(long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found by id: " + id));

        taskRepository.delete(task);

    }


}


