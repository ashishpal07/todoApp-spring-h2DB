package com.scaler.todoApp.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest(showSql = true)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void canCreateTask() {
        TaskEntity task = new TaskEntity();
        task.setName("test task");
        task.setDueDate(new Date());
        taskRepository.save(task);
    }

}
