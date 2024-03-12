package com.revature.project.parser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.services.FixedLengthFileService;

@RestController
public class FixedLengthFileController {

  private final FixedLengthFileService fixedLengthFileService;

  public FixedLengthFileController(FixedLengthFileService fixedLengthFileService) {
    this.fixedLengthFileService = fixedLengthFileService;
  }

  @PostMapping("/files/{username}")
  @ResponseStatus(HttpStatus.OK)
  public void upload(@RequestParam("file") MultipartFile file, @PathVariable("username") String username) {
    fixedLengthFileService.store(file, username);
  }
}
