package com.revature.project.parser.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  String store(MultipartFile file);

  String store(MultipartFile file, String folderName);

  String readFileAsString(String filePath) throws IOException;
}
