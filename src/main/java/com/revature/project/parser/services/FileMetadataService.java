package com.revature.project.parser.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.revature.project.parser.models.FileMetadata;
import com.revature.project.parser.models.FixedLengthFile;
import com.revature.project.parser.models.ParsedRecord;
import com.revature.project.parser.models.Specification;
import com.revature.project.parser.payload.response.FileMetadataDto;
import com.revature.project.parser.repositories.FileMetadataRepository;

@Service
public class FileMetadataService {

  private final FileMetadataRepository fileMetadataRepository;
  private final SpecificationService specificationService;
  private final FixedLengthFileService fixedLengthFileService;

  @Autowired
  @Lazy
  private final ParsedRecordService parsedRecordService;

  public FileMetadataService(FileMetadataRepository fileMetadataRepository,
      FixedLengthFileService fixedLengthFileService,
      SpecificationService specificationService) {
    this.fileMetadataRepository = fileMetadataRepository;
    this.specificationService = specificationService;
    this.fixedLengthFileService = fixedLengthFileService;
    this.parsedRecordService = null;
  }

  public FileMetadata create(String rawFileId, String parsedDataId, String specId, String userId) {
    Objects.requireNonNull(rawFileId);
    Objects.requireNonNull(parsedDataId);
    Objects.requireNonNull(specId);
    FileMetadata metadata = new FileMetadata(rawFileId, parsedDataId, specId, userId);
    return fileMetadataRepository.save(metadata);
  }

  public List<FileMetadataDto> findByUserId(String userId) {
    List<FileMetadata> found = fileMetadataRepository.findAllByUserId(userId);
    return found.stream().map(metadata -> {
      try {
        Specification specFile = specificationService.findById(metadata.getSpecificationId());
        FixedLengthFile flatFile = fixedLengthFileService.findById(metadata.getRawFileId());
        ParsedRecord parsedRecord = parsedRecordService.findById(metadata.getParsedDataId());
        return new FileMetadataDto(metadata.getId().toHexString(), flatFile, parsedRecord, specFile);
      } catch (Exception e) {
        return null;
      }
    }).filter(Objects::nonNull).collect(Collectors.toList());
  }

  public List<FileMetadata> findAllByUserIdAndSpecificationId(String userId, String specId) {
    return fileMetadataRepository.findAllByUserIdAndSpecificationId(userId, specId);
  }

}
