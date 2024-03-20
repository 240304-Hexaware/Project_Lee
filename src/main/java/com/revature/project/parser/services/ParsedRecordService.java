package com.revature.project.parser.services;

import java.io.IOException;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public ParsedRecord process(String userId, String rawFileId, String specId)
      throws IOException, ItemNotFoundException, UserNotFoundException {
    validateUserId(userId);
    FixedLengthFile rawFile = fixedLengthFileService.findById(rawFileId);
    Specification spec = specificationService.findById(specId);

    Map<String, String> parsedData = getParsedData(rawFile, spec);

    // store parsed record with metadataId being null to get an ID of the parsed
    // data
    ParsedRecord initialRecord = createParsedRecord(userId, parsedData); // without metadataId

    FileMetadata createdMetadata = fileMetadataService.create(rawFileId, initialRecord.getId().toHexString(), specId);

    // update fixed-length file and parsed record with created metadata
    FixedLengthFile updatedFixedLengthFile = updateFixedLengthFileWithMetadata(rawFile, createdMetadata);
    fixedLengthFileService.save(updatedFixedLengthFile);

    ParsedRecord updatedRecord = updateRecordWithMetadata(initialRecord, createdMetadata);
    return parsedRecordRepository.save(updatedRecord);
  }

  private Map<String, String> getParsedData(FixedLengthFile rawFile, Specification spec) throws IOException {
    String rawData = localStorageService.readFileAsString(rawFile.getFilePath());
    return FileParser.readStringFields(rawData, spec.getSpecs());
  }

  private ParsedRecord createParsedRecord(String userId, Map<String, String> parsedData) {
    return parsedRecordRepository.save(new ParsedRecord(userId, null, new Document(parsedData)));
  }

  private FixedLengthFile updateFixedLengthFileWithMetadata(FixedLengthFile oldFile, FileMetadata createdMetadata) {
    return new FixedLengthFile(oldFile, createdMetadata.getId().toHexString());
  }

  private ParsedRecord updateRecordWithMetadata(ParsedRecord initialRecord, FileMetadata createdMetadata) {
    return new ParsedRecord(initialRecord, createdMetadata.getId().toHexString());
  }

  private void validateUserId(String userId) throws UserNotFoundException {
    if (userService.findByUserId(userId) == null) {
      throw new UserNotFoundException("No user found");
    }
  }

}
