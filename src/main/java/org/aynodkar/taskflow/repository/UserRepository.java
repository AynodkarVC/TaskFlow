package org.aynodkar.taskflow.repository;

import org.aynodkar.taskflow.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends MongoRepository<User,String> {

}
