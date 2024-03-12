package com.revature.project.parser.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.project.parser.models.Specification;

public interface SpecificationRepository extends MongoRepository<Specification, ObjectId> {

  List<Specification> findAllByUserId(String userId);

}
