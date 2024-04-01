package com.revature.project.parser.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalStorageService implements StorageService {

  private static final String BASE_PATH = "src\\main\\resources\\files";

  public enum Folder {
    FLATFILE, SPECIFICATION
  }

  // https://spring.io/guides/gs/uploading-files
  @Override
  public String store(MultipartFile file, Folder folderName) {
    Path location = null;
    switch (folderName) {
      case FLATFILE:
        location = Paths.get(BASE_PATH, "flatfile");
        break;
      case SPECIFICATION:
        location = Paths.get(BASE_PATH, "specification");
        break;
      default:
        assert (false);
        break;
    }
    return storeAt(location, file);
  }

  // TODO: randomize file name to avoid duplicates
  private String storeAt(Path baseLocation, MultipartFile file) {
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

  @Override
  public List<String> readFileAsString(String filePath) throws IOException {
    Objects.requireNonNull(filePath);
    FileInputStream stream = new FileInputStream(new File(filePath));
    List<String> read = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
      while (reader.ready()) {
        read.add(reader.readLine());
      }
    }

    return read;
  }

}
