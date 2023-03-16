package com.scaler.todoApp.notes;


import com.scaler.todoApp.common.BaseEntity;
import com.scaler.todoApp.task.TaskEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 100)
    String title;

    @Column(name = "body", nullable = false, length = 1000)
    String body;

    @JoinColumn(name = "task_id")
    @ManyToOne(cascade = CascadeType.ALL)
    TaskEntity task;
}
