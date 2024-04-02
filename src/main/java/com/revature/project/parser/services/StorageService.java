package com.revature.project.parser.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.services.LocalStorageService.Folder;

public interface StorageService {
  String store(MultipartFile file, Folder folderName);

  List<String> readFileAsString(String filePath) throws IOException;

  String readFileFromPath(String filePath) throws IOException;
}
