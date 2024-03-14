package com.revature.project.parser.services;

import java.io.IOException;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.FileMetadata;
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
  private final LocalStorageService localStorageService;
  private final FileMetadataService fileMetadataService;

  public ParsedRecordService(UserService userService, FixedLengthFileService fixedLengthFileService,
      SpecificationService specificationService, ParsedRecordRepository parsedRecordRepository,
      LocalStorageService localStorageService, FileMetadataService fileMetadataService) {
    this.userService = userService;
    this.fixedLengthFileService = fixedLengthFileService;
    this.specificationService = specificationService;
    this.parsedRecordRepository = parsedRecordRepository;
    this.localStorageService = localStorageService;
    this.fileMetadataService = fileMetadataService;
  }

  public ParsedRecord process(String userId, String rawFileId, String specId)
      throws IOException, ItemNotFoundException, UserNotFoundException {
    validateUserId(userId);
    FixedLengthFile rawFile = getValidFixedLengthFile(rawFileId);
    Specification spec = getValidSpecification(specId);

    String rawData = localStorageService.readFileAsString(rawFile.getFilePath());
    Map<String, String> parsed = FileParser.readStringFields(rawData, spec.getSpecs());
    ParsedRecord parsedRecord = new ParsedRecord(userId, null, new Document(parsed));
    ParsedRecord saved = parsedRecordRepository.save(parsedRecord);
    FileMetadata createdMetadata = fileMetadataService.create(rawFileId, saved.getId().toHexString(), specId);
    parsedRecord.setMetadataId(createdMetadata.getId().toHexString());
    return saved;
  }

  private void validateUserId(String userId) throws UserNotFoundException {
    if (userService.findByUserId(userId) == null) {
      throw new UserNotFoundException("User not found: " + userId);
    }
  }

  private FixedLengthFile getValidFixedLengthFile(String rawFileId) throws ItemNotFoundException {
    FixedLengthFile rawFile = fixedLengthFileService.findById(rawFileId);
    if (rawFile == null) {
      throw new ItemNotFoundException("Fixed-length file not found");
    }
    return rawFile;
  }

  private Specification getValidSpecification(String specId) throws ItemNotFoundException {
    Specification spec = specificationService.findById(specId);
    if (spec == null) {
      throw new ItemNotFoundException("Specification not found");
    }
    return spec;
  }
}
