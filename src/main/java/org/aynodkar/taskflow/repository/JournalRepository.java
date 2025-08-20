package org.aynodkar.taskflow.repository;

import org.aynodkar.taskflow.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

interface JournalRepository extends MongoRepository<User,String> {
}
