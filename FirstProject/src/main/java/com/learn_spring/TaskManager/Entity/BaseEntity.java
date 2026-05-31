package com.learn_spring.TaskManager.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(name = "created", updatable = false)
    private LocalDateTime created;
    @Column(name = "updated")
    private LocalDateTime updated;

    @PrePersist
    void onCreate(){
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate(){
        this.updated = LocalDateTime.now();
    }


}
