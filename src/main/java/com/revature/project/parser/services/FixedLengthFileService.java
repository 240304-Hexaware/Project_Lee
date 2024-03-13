package com.revature.project.parser.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.models.FixedLengthFile;
import com.revature.project.parser.repositories.FixedLengthFileRepository;

@Service
public class FixedLengthFileService {

  private final FixedLengthFileRepository fixedLengthFileRepository;
  private final UserService userService;
  private final StorageService storageService;

  public FixedLengthFileService(FixedLengthFileRepository fixedLengthFileRepository, UserService userService,
      StorageService storageService) {
    this.fixedLengthFileRepository = fixedLengthFileRepository;
    this.userService = userService;
    this.storageService = storageService;
  }

  public void store(MultipartFile file, String username) {
    String filePath = storageService.store(file, "flatfile");
    String userId = userService.findHexIdByUsername(username);
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

}
