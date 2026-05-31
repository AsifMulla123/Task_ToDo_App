package com.learn_spring.TaskManager.Service.impl;

import com.learn_spring.TaskManager.DTO.CreateTaskListRequestDto;
import com.learn_spring.TaskManager.DTO.TaskListResponseDto;
import com.learn_spring.TaskManager.DTO.UpdateTaskListRequestDto;
import com.learn_spring.TaskManager.Entity.TaskList;

import com.learn_spring.TaskManager.Mapper.TaskListMapper;
import com.learn_spring.TaskManager.Repository.TaskListRepository;
import com.learn_spring.TaskManager.Service.ITaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements ITaskListService {

    final TaskListRepository taskListRepository;
    final TaskListMapper taskListMapper;

    public TaskListServiceImpl(TaskListRepository taskListRepository, TaskListMapper taskListMapper) {
        this.taskListRepository = taskListRepository;
        this.taskListMapper = taskListMapper;
    }

    @Override
    public List<TaskListResponseDto> findAllTaskLists(){

        List<TaskList> taskList = taskListRepository.findAll();

        return taskList.stream().map(taskListMapper::toTaskListResponseDto).toList();
    }

    @Override
    public TaskListResponseDto findTaskListById(long id){
        TaskList taskList = taskListRepository.findById(id).orElseThrow(RuntimeException::new);

        return taskListMapper.toTaskListResponseDto(taskList);
    }

    @Override
    public void createTaskList(CreateTaskListRequestDto createList){

        TaskList taskList = taskListMapper.toEntity(createList);
        taskListRepository.save(taskList);
    }

    @Override
    public void updateTaskList(UpdateTaskListRequestDto updateList, long id){

        TaskList taskList = taskListRepository.findById(id).orElseThrow(RuntimeException::new);
        taskListMapper.updateEntity(updateList, taskList);
        taskListRepository.save(taskList);
    }

    @Override
    public void deleteTaskListByTaskListId(long id){

        TaskList taskList = taskListRepository.findById(id).orElseThrow(RuntimeException::new);
        taskListRepository.deleteById(id);
    }

    @Override
    public void deleteAllTaskLists() {
        taskListRepository.deleteAll();
    }


}
