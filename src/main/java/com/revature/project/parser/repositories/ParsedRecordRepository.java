package com.revature.project.parser.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.project.parser.models.ParsedRecord;

public interface ParsedRecordRepository extends MongoRepository<ParsedRecord, ObjectId> {

  ParsedRecord findById(String parsedDataId);

  List<ParsedRecord> findAllByUserId(String userId);

}
