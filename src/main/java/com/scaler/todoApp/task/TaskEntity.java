package com.scaler.todoApp.task;

import com.scaler.todoApp.common.BaseEntity;
import com.scaler.todoApp.notes.NoteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "due_date", nullable = false)
    Date dueDate;

    @Column(name = "done", nullable = false)
    boolean done;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    List<NoteEntity> notes;

}