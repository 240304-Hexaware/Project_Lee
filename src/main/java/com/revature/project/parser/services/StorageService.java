package com.revature.project.parser.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  void store(MultipartFile file);
}
