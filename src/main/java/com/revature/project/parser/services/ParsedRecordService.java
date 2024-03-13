package com.revature.project.parser.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.models.FixedLengthFile;
import com.revature.project.parser.models.ParsedRecord;
import com.revature.project.parser.models.Specification;
import com.revature.project.parser.repositories.ParsedRecordRepository;
import com.revature.project.parser.utils.FileParser;

@Service
public class ParsedRecordService {

  private final UserService userService;
  private final SpecificationService specificationService;
  private final FixedLengthFileService fixedLengthFileService;
  private final ParsedRecordRepository parsedRecordRepository;

  public ParsedRecordService(UserService userService, FixedLengthFileService fixedLengthFileService,
      SpecificationService specificationService, ParsedRecordRepository parsedRecordRepository) {
    this.userService = userService;
    this.fixedLengthFileService = fixedLengthFileService;
    this.specificationService = specificationService;
    this.parsedRecordRepository = parsedRecordRepository;
  }

  public ParsedRecord process(String username, String rawFileId, String specId)
      throws IOException, ItemNotFoundException {
    String userId = userService.findHexIdByUsername(username);
    FixedLengthFile rawFile = fixedLengthFileService.findById(rawFileId);
    Specification spec = specificationService.findById(specId);

    String rawData = FileParser.readCompleteChars(new File(rawFile.getFilePath()));
    Map<String, String> parsed = FileParser.readStringFields(rawData, spec.getSpecs());
    ParsedRecord parsedRecord = new ParsedRecord(userId, null, new Document(parsed));

    // TODO: metadata
    return parsedRecordRepository.save(parsedRecord);
  }
}
