package com.learn_spring.TaskManager.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Entity
@Table(name="task_lists")
public class Task_List {
    @Id
    @Column(name="id",unique=true,updatable = false, nullable=false)
    private Long id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="created", nullable=false)
    private LocalDateTime created;

    @Column(name="updated", nullable=false)
    private LocalDateTime updated;

}
