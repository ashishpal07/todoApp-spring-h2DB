package com.scaler.todoApp.task;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class TaskDto {
    @Nullable
    String name;

    @Nullable
    Date dueDate;

    @Nullable
    Boolean done;
}
