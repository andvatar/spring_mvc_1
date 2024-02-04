package ru.javarush.tarasov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.tarasov.entity.Status;
import ru.javarush.tarasov.entity.Task;
import ru.javarush.tarasov.repository.TaskRepository;

import java.util.Optional;
import java.util.function.*;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService{
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveTask(Task task) {
        this.repository.save(task);
    }

    @Override
    public Page<Task> getTasks(int pageNumber, int pageSize) {
        Sort sort = Sort.by("Id").ascending();
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    @Override
    public void deleteTask(Task task) {
        this.repository.delete(task);
    }

    @Override
    public Task addTask(String description, Status status) {
        Task task = new Task(description, status);
        saveTask(task);
        return(task);
    }

    @Override
    public Task getTaskById(int id) {
        Optional<Task> task = repository.findById(id) ;
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    @Transactional
    public Task editTask(int id, String description, Status status) {
        Task task = getTaskById(id);
        task.setDescription(description);
        task.setStatus(status);
        saveTask(task);
        return task;
    }
}
