package com.revature.project.parser.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.project.parser.models.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

}
