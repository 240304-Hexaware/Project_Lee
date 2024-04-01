package com.revature.project.parser.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.ParsingFailedException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.ParsedRecord;
import com.revature.project.parser.payload.request.TaskRequest;
import com.revature.project.parser.services.ParsedRecordService;
import com.revature.project.parser.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class TaskController {

  private final ParsedRecordService parsedRecordService;
  private final JwtTokenUtil jwtTokenUtil;

  public TaskController(ParsedRecordService parsedRecordService, JwtTokenUtil jwtTokenUtil) {
    this.parsedRecordService = parsedRecordService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  // TODO: Make it to handle multiple flatfile ids
  @PostMapping("/tasks")
  @ResponseStatus(HttpStatus.OK)
  public ParsedRecord process(@RequestBody @Valid TaskRequest taskRequest, HttpServletRequest request)
      throws IOException, ItemNotFoundException, UserNotFoundException, InvalidJwtException, ParsingFailedException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    return parsedRecordService.process(userId, taskRequest.rawFileId(), taskRequest.specId());
  }

}
