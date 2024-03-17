package com.revature.project.parser.services;

import java.util.Objects;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.models.FixedLengthFile;
import com.revature.project.parser.repositories.FixedLengthFileRepository;
import com.revature.project.parser.services.LocalStorageService.Folder;

@Service
public class FixedLengthFileService {

  private final FixedLengthFileRepository fixedLengthFileRepository;
  private final StorageService storageService;

  public FixedLengthFileService(FixedLengthFileRepository fixedLengthFileRepository, StorageService storageService) {
    this.fixedLengthFileRepository = fixedLengthFileRepository;
    this.storageService = storageService;
  }

  public void store(MultipartFile file, String userId) {
    Objects.requireNonNull(userId);
    String filePath = storageService.store(file, Folder.FLATFILE);
    FixedLengthFile fixedLengthFile = new FixedLengthFile(file.getOriginalFilename(), userId, filePath, null);
    fixedLengthFileRepository.save(fixedLengthFile);
  }

  public FixedLengthFile findById(String rawFileId) throws ItemNotFoundException {
    Optional<FixedLengthFile> found = fixedLengthFileRepository.findById(new ObjectId(rawFileId));
    if (found.isEmpty()) {
      throw new ItemNotFoundException("No flat file exists");
    }
    return found.get();
  }

  public FixedLengthFile save(FixedLengthFile fixedlengthFile) {
    return fixedLengthFileRepository.save(fixedlengthFile);
  }

}
