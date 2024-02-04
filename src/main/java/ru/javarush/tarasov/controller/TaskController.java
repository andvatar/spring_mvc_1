package ru.javarush.tarasov.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javarush.tarasov.entity.Task;
import ru.javarush.tarasov.entity.TaskInfo;
import ru.javarush.tarasov.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView viewHomePage(ModelAndView modelAndView) {
        return findPaginated(1, modelAndView);
    }

    @GetMapping("/page={pageNo}")
    public ModelAndView findPaginated(@PathVariable("pageNo") int pageNo,
                                      ModelAndView modelAndView) {
        int pageSize = 5;
        Page<Task> taskPage = service.getTasks(pageNo, pageSize);
        List<Task> taskList = taskPage.getContent();

        int totalPages = taskPage.getTotalPages();

        modelAndView.addObject("taskPage", taskPage);
        modelAndView.addObject("tasks", taskList);
        modelAndView.addObject("currentPage", pageNo);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        modelAndView.setViewName("tasks");

        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteTask(@PathVariable("id") int id,
                                   ModelAndView modelAndView) {
        modelAndView.setViewName("tasks");

        service.deleteTask(service.getTaskById(id));

        return modelAndView;
    }

    @PostMapping(value = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView updateTask(@PathVariable("id") int id,
                           @RequestBody TaskInfo taskInfo,
                           ModelAndView modelAndView)
    {
        /*Task task = service.getTaskById(id);
        task.setStatus(taskInfo.status());
        task.setDescription(taskInfo.description());
        service.saveTask(task);*/

        service.editTask(id, taskInfo.description(), taskInfo.status());

        modelAndView.setViewName("tasks");

        return modelAndView;
    }

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView addTask(@RequestBody TaskInfo taskInfo,
                        ModelAndView modelAndView)
    {
        service.addTask(taskInfo.description(), taskInfo.status());

        modelAndView.setViewName("tasks");

        return modelAndView;
    }
}
