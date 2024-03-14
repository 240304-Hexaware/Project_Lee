package com.revature.project.parser.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.ParsedRecord;
import com.revature.project.parser.payload.request.TaskRequest;
import com.revature.project.parser.services.ParsedRecordService;

@RestController
public class TaskController {

  private final ParsedRecordService parsedRecordService;

  public TaskController(ParsedRecordService parsedRecordService) {
    this.parsedRecordService = parsedRecordService;
  }

  @PostMapping("/tasks")
  @ResponseStatus(HttpStatus.OK)
  public ParsedRecord process(@RequestBody TaskRequest taskRequest)
      throws IOException, ItemNotFoundException, UserNotFoundException {
    return parsedRecordService.process(taskRequest.userId(), taskRequest.rawFileId(), taskRequest.specId());
  }

  @ExceptionHandler(ItemNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String itemNotFound(ItemNotFoundException e) {
    return e.getMessage();
  }

  @ExceptionHandler(IOException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String ioException(IOException e) {
    return e.getMessage();
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFound(UserNotFoundException e) {
    return e.getMessage();
  }

}
