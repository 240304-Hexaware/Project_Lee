package com.revature.project.parser.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.services.LocalStorageService.Folder;

public interface StorageService {
  String store(MultipartFile file, Folder folderName);

  String readFileAsString(String filePath) throws IOException;
}
