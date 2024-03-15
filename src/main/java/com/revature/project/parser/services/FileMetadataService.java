package com.revature.project.parser.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.revature.project.parser.models.FileMetadata;
import com.revature.project.parser.repositories.FileMetadataRepository;

@Service
public class FileMetadataService {

  private final FileMetadataRepository fileMetadataRepository;

  public FileMetadataService(FileMetadataRepository fileMetadataRepository) {
    this.fileMetadataRepository = fileMetadataRepository;
  }

  public FileMetadata create(String rawFileId, String parsedDataId, String specId) {
    Objects.requireNonNull(rawFileId);
    Objects.requireNonNull(parsedDataId);
    Objects.requireNonNull(specId);
    FileMetadata metadata = new FileMetadata(rawFileId, parsedDataId, specId);
    return fileMetadataRepository.save(metadata);
  }

}
