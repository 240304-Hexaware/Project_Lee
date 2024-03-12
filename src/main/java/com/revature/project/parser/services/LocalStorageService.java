package com.revature.project.parser.services;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalStorageService implements StorageService {

  private static final String PATH_TO_SPEC = "src\\main\\java\\com\\revature\\project\\files\\specification";

  private final Path rootLocation;

  public LocalStorageService() {
    rootLocation = Paths.get(PATH_TO_SPEC);
  }

  // https://spring.io/guides/gs/uploading-files
  @Override
  public void store(MultipartFile file) {
    Path destinationFile = this.rootLocation
        .resolve(Paths.get(file.getOriginalFilename())).normalize()
        .toAbsolutePath();
    try {
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile,
            StandardCopyOption.REPLACE_EXISTING);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
