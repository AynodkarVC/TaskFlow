package org.aynodkar.taskflow.controller;

import org.aynodkar.taskflow.entity.Task;
import org.aynodkar.taskflow.service.TaskService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task create(@RequestBody Task task){
        task.setCreatedAt(LocalDateTime.now());
        taskService.createTask(task);
        return task;
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Task> findById(@PathVariable String id){
        return taskService.findTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("id/{id}")
    public boolean deleteById(@PathVariable String id){
        taskService.deleteTaskById(id);
        return true;
    }

    @DeleteMapping
    public boolean deleteAllTasks(){
        taskService.deleteAllTasks();
        return true;
    }

    @PutMapping("id/{id}")
    public Task update(@PathVariable String id, @RequestBody Task updatedTask){
        return taskService.updateTaskById(id, updatedTask);
    }

}
