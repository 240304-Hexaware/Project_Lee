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

  private static final String BASE_PATH = "src\\main\\java\\com\\revature\\project\\files";

  // https://spring.io/guides/gs/uploading-files
  @Override
  public String store(MultipartFile file) {
    return store(file, "");
  }

  @Override
  public String store(MultipartFile file, String folderName) {
    String str1 = "";
    if (folderName != null && folderName.length() > 0) {
      str1 += "\\" + folderName;
    }
    Path baseLocation = Paths.get(BASE_PATH + str1);
    Path destinationFile = baseLocation
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
    return destinationFile.toString();
  }

}
