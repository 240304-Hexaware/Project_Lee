package com.revature.project.parser.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.ParsingFailedException;
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
      throws IOException, ItemNotFoundException, UserNotFoundException, ParsingFailedException {
    validateUserId(userId);
    FixedLengthFile rawFile = fixedLengthFileService.findById(rawFileId);
    Specification spec = specificationService.findById(specId);

    List<Map<String, Object>> parsedData = getParsedData(rawFile, spec);

    // store parsed record with metadataId being null to get an ID of the parsed
    // data
    ParsedRecord initialRecord = createParsedRecord(userId, parsedData); // without metadataId

    FileMetadata createdMetadata = fileMetadataService.create(rawFileId, initialRecord.getId().toHexString(), specId,
        userId, new Date(System.currentTimeMillis()));

    // update fixed-length file and parsed record with created metadata
    FixedLengthFile updatedFixedLengthFile = updateFixedLengthFileWithMetadata(rawFile, createdMetadata);
    fixedLengthFileService.save(updatedFixedLengthFile);

    ParsedRecord updatedRecord = updateRecordWithMetadata(initialRecord, createdMetadata);
    return parsedRecordRepository.save(updatedRecord);
  }

  private List<Map<String, Object>> getParsedData(FixedLengthFile rawFile, Specification spec)
      throws IOException, ParsingFailedException {
    List<String> rawData = localStorageService.readFileAsString(rawFile.getFilePath());
    return FileParser.readStringFieldsAsList(rawData, spec.getSpecs());
  }

  private ParsedRecord createParsedRecord(String userId, List<Map<String, Object>> parsedData) {
    return parsedRecordRepository
        .save(new ParsedRecord(userId, null, parsedData.stream().map(Document::new).toList()));
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

  public ParsedRecord findById(String parsedDataId) {
    return parsedRecordRepository.findById(parsedDataId);
  }

  public List<ParsedRecord> findBySpecification(String userId, String specId) {
    List<FileMetadata> found = fileMetadataService.findAllByUserIdAndSpecificationId(userId, specId);
    return found.stream().map(metadata -> findById(metadata.getParsedDataId())).toList();
  }

  public List<ParsedRecord> findByParsedDataId(String parsedDataId) {
    return List.of(parsedRecordRepository.findById(parsedDataId));
  }

  public List<ParsedRecord> findAllForUser(String userId) {
    return parsedRecordRepository.findAllByUserId(userId);
  }

}
