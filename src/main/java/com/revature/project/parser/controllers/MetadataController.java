package com.revature.project.parser.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.payload.response.FileMetadataDto;
import com.revature.project.parser.services.FileMetadataService;
import com.revature.project.parser.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

  private final FileMetadataService fileMetadataService;
  private final JwtTokenUtil jwtTokenUtil;

  public MetadataController(FileMetadataService fileMetadataService, JwtTokenUtil jwtTokenUtil) {
    this.fileMetadataService = fileMetadataService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public List<FileMetadataDto> getMetadata(HttpServletRequest request) throws InvalidJwtException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    return fileMetadataService.findByUserId(userId);
  }

}
