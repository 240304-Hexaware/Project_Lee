package com.revature.project.parser.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.project.parser.models.FileMetadata;

public interface FileMetadataRepository extends MongoRepository<FileMetadata, ObjectId> {

  List<FileMetadata> findAllByUserId(String userId);

}
