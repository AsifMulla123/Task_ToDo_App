package com.learn_spring.TaskManager.Entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "tasks")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false, nullable = false)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name="due_date")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private Task_Status status;

    @Column(name="created")
    private LocalDateTime created;

    @Column(name="updated")
    private LocalDateTime updated;


    public Task(Long id, LocalDateTime updated, LocalDateTime created, Task_Status status, LocalDateTime dueDate, String description, String title) {
        this.id = id;
        this.updated = updated;
        this.created = created;
        this.status = status;
        this.dueDate = dueDate;
        this.description = description;
        this.title = title;

    }

    public Task() {

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }


}
