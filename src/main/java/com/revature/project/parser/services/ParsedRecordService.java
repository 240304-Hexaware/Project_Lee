package com.revature.project.parser.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.project.parser.models.FixedLengthFile;
import com.revature.project.parser.models.ParsedRecord;
import com.revature.project.parser.models.Specification;
import com.revature.project.parser.utils.FileParser;

@Service
public class ParsedRecordService {

  private final UserService userService;
  private final SpecificationService specificationService;
  private final FixedLengthFileService fixedLengthFileService;

  public ParsedRecordService(UserService userService, FixedLengthFileService fixedLengthFileService,
      SpecificationService specificationService) {
    this.userService = userService;
    this.fixedLengthFileService = fixedLengthFileService;
    this.specificationService = specificationService;
  }

  public ParsedRecord process(String username, String rawFileId, String specId) throws IOException {
    String userId = userService.findHexIdByUsername(username);
    FixedLengthFile rawFile = fixedLengthFileService.findById(rawFileId);
    Specification spec = specificationService.findById(specId);

    String rawData = FileParser.readCompleteChars(new File(rawFile.getFilePath()));
    List<String> parsed = FileParser.readStringFields(rawData, spec.getSpecs());
    // TODO: do something with parsed
    // TODO: do something with FileMetadata
    return null;
  }

}
