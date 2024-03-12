package com.revature.project.parser.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
  public ParsedRecord process(@RequestBody TaskRequest taskRequest) throws IOException {
    return parsedRecordService.process(taskRequest.username(), taskRequest.rawFileId(), taskRequest.specId());
  }

}
