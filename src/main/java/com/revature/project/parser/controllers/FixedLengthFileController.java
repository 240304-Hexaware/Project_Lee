package com.revature.project.parser.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.FixedLengthFile;
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
  public FixedLengthFile upload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
      throws InvalidJwtException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    return fixedLengthFileService.store(file, userId);
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public List<FixedLengthFile> getAll(HttpServletRequest request) throws InvalidJwtException, UserNotFoundException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    return fixedLengthFileService.findAllByUserId(userId);
  }

  @GetMapping("/blob/{fileId}")
  @ResponseStatus(HttpStatus.OK)
  public String download(@PathVariable("fileId") String fileId, HttpServletRequest request)
      throws InvalidJwtException, ItemNotFoundException, IOException {
    jwtTokenUtil.getUserIdFromRequest(request);
    return fixedLengthFileService.getFile(fileId);
  }
}
