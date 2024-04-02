package com.revature.project.parser.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.FixedLengthFile;
import com.revature.project.parser.models.User;
import com.revature.project.parser.repositories.FixedLengthFileRepository;
import com.revature.project.parser.services.LocalStorageService.Folder;

@Service
public class FixedLengthFileService {

  private final FixedLengthFileRepository fixedLengthFileRepository;
  private final StorageService storageService;
  private final UserService userService;

  public FixedLengthFileService(FixedLengthFileRepository fixedLengthFileRepository, StorageService storageService,
      UserService userService) {
    this.fixedLengthFileRepository = fixedLengthFileRepository;
    this.storageService = storageService;
    this.userService = userService;
  }

  public FixedLengthFile store(MultipartFile file, String userId) {
    Objects.requireNonNull(userId);
    String filePath = storageService.store(file, Folder.FLATFILE);
    FixedLengthFile fixedLengthFile = new FixedLengthFile(file.getOriginalFilename(), userId, filePath, null);
    return fixedLengthFileRepository.save(fixedLengthFile);
  }

  public FixedLengthFile findById(String rawFileId) throws ItemNotFoundException {
    Optional<FixedLengthFile> found = fixedLengthFileRepository.findById(new ObjectId(rawFileId));
    if (found.isEmpty()) {
      throw new ItemNotFoundException("No flat file found");
    }
    return found.get();
  }

  public FixedLengthFile save(FixedLengthFile fixedlengthFile) {
    return fixedLengthFileRepository.save(fixedlengthFile);
  }

  public List<FixedLengthFile> findAllByUserId(String userId) throws UserNotFoundException {
    User found = userService.findByUserId(userId);
    if (found == null) {
      return Arrays.asList();
    }
    return fixedLengthFileRepository.findAllByUserId(found.getId().toHexString());
  }

  public String getFile(String fileId) throws ItemNotFoundException, IOException {
    Optional<FixedLengthFile> found = fixedLengthFileRepository.findById(new ObjectId(fileId));
    if (found.isEmpty()) {
      throw new ItemNotFoundException("No flat file found");
    }
    return storageService.readFileFromPath(found.get().getFilePath());
  }

}
