package com.learn_spring.TaskManager.Service;

import com.learn_spring.TaskManager.DTO.*;

import java.util.List;

public interface ITaskListService {

    // for Task_Lists
    List<TaskListResponseDto> findAllTaskLists();
    TaskListResponseDto findTaskListById(long id);
    void createTaskList(CreateTaskListRequestDto createList);
    void updateTaskList(UpdateTaskListRequestDto updateDto, long id);
    void deleteTaskListByTaskListId(long id);
    void deleteAllTaskLists();
}
