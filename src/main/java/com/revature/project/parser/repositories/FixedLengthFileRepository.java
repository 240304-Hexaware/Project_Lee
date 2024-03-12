package com.revature.project.parser.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.project.parser.models.FixedLengthFile;

public interface FixedLengthFileRepository extends MongoRepository<FixedLengthFile, ObjectId> {

}
