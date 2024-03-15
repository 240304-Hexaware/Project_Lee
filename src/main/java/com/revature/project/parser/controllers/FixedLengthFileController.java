package com.revature.project.parser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.services.FixedLengthFileService;
import com.revature.project.parser.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/files")
public class FixedLengthFileController {

  private final FixedLengthFileService fixedLengthFileService;
  private final JwtTokenUtil jwtTokenUtil;

  public FixedLengthFileController(FixedLengthFileService fixedLengthFileService, JwtTokenUtil jwtTokenUtil) {
    this.fixedLengthFileService = fixedLengthFileService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws InvalidJwtException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    fixedLengthFileService.store(file, userId);
  }
}
