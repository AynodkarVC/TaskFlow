package org.aynodkar.taskflow.repository;

import org.aynodkar.taskflow.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

@org.springframework.stereotype.Repository
public interface TaskRepository extends MongoRepository<Task, String> {

}
