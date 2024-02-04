package ru.javarush.tarasov.service;

import org.springframework.data.domain.Page;
import ru.javarush.tarasov.entity.Status;
import ru.javarush.tarasov.entity.Task;

import java.util.List;

public interface TaskService {
    //List<Task> getAllTasks();
    Page<Task> getTasks(int pageNumber, int pageSize);
    void deleteTask(Task task);
    void saveTask(Task task);
    Task addTask(String description, Status status);
    //Task getTaskById(int Id);

    Task getTaskById(int Id);

    public Task editTask(int id, String description, Status status);
}
