package com.revature.project.parser.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.models.ParsedRecord;
import com.revature.project.parser.services.ParsedRecordService;
import com.revature.project.parser.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/records")
public class ParsedRecordController {

  private final ParsedRecordService parsedRecordService;
  private final JwtTokenUtil jwtTokenUtil;

  public ParsedRecordController(ParsedRecordService parsedRecordService, JwtTokenUtil jwtTokenUtil) {
    this.parsedRecordService = parsedRecordService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public List<ParsedRecord> getAllBySpecification(@RequestParam(required = true) String specId,
      HttpServletRequest request)
      throws InvalidJwtException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    return parsedRecordService.findBySpecification(userId, specId);
  }
}
