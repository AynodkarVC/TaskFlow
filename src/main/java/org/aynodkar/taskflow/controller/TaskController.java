package org.aynodkar.taskflow.controller;

import org.aynodkar.taskflow.entity.Task;
import org.aynodkar.taskflow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return new ResponseEntity<>(taskService.getAllTasks(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        try {
            return new ResponseEntity<>(taskService.createTask(task),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Task>> findById(@PathVariable String id){
        Optional<Task> task = taskService.findTaskById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(taskService.findTaskById(id),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        Optional<Task> task = taskService.findTaskById(id);
        if (task.isPresent()) {
            taskService.deleteTaskById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAllTasks(){
        taskService.deleteAllTasks();
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> update(@PathVariable String id, @RequestBody Task updatedTask){
        Optional<Task> task = taskService.findTaskById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(taskService.updateTaskById(id,updatedTask),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
