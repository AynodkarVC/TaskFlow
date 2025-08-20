package org.aynodkar.taskflow.service;

import org.aynodkar.taskflow.entity.Task;
import org.aynodkar.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task createTask(Task task){
        task.setCreatedAt(LocalDateTime.now());
        taskRepository.save(task);
        return task;
    }

    public Optional<Task> findTaskById(String id){
        return taskRepository.findById(id);
    }

    public void deleteTaskById(String id){
        taskRepository.deleteById(id);
    }

    public void deleteAllTasks(){
        taskRepository.deleteAll();
    }

    public Task updateTaskById(String id, Task updatedTask){
        Task oldTask = taskRepository.findById(id).orElse(null);

        if(oldTask != null){
            if (updatedTask.getTitle() != null && !updatedTask.getTitle().isEmpty()){
                oldTask.setId(updatedTask.getTitle());
            }
            if (updatedTask.getDescription() != null && !updatedTask.getDescription().isEmpty()){
                oldTask.setDescription(updatedTask.getDescription());
            }
            if (updatedTask.getPriority() != null && !updatedTask.getPriority().isEmpty()){
                oldTask.setPriority(updatedTask.getPriority());
            }
            if (updatedTask.getCompleted() != null){
                oldTask.setCompleted(updatedTask.getCompleted());
            }
            if (updatedTask.getUserId() != null && !updatedTask.getUserId().isEmpty()){
                oldTask.setUserId(updatedTask.getUserId());
            }
            if (updatedTask.getPriority() != null && !updatedTask.getPriority().isEmpty()){
                oldTask.setPriority(updatedTask.getPriority());
            }
            if (updatedTask.getDueDate() != null){
                oldTask.setDueDate(updatedTask.getDueDate());
            }
        }

        return taskRepository.save(oldTask);
    }

}
