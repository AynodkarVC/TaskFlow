package org.aynodkar.taskflow.service;

import org.aynodkar.taskflow.entity.Task;
import org.aynodkar.taskflow.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskService {



    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public void createTask(Task task){
        taskRepository.save(task);
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

        if(updatedTask.getTitle() != null && !updatedTask.getTitle().isEmpty()){
            oldTask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getDescription() != null && !updatedTask.getDescription().isEmpty()){
            oldTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getCompleted() != null){
            oldTask.setCompleted(updatedTask.getCompleted());
        }
        return taskRepository.save(oldTask);
    }

}
