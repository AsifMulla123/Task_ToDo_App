package com.learn_spring.TaskManager.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity @Getter @Setter @ToString
@RequiredArgsConstructor
@Table(name="task_lists")
public class TaskList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable=false)
    private Long id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="list_description")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskListStatus taskListStatus = TaskListStatus.OPEN;

    @OneToMany(mappedBy = "taskList",
            cascade = {CascadeType.ALL, CascadeType.PERSIST} ,orphanRemoval = true)
    @ToString.Exclude
    private List<Task> tasks = new ArrayList<>();

}
