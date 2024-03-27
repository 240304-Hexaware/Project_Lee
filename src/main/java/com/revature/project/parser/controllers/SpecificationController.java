package com.revature.project.parser.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.Specification;
import com.revature.project.parser.services.SpecificationService;
import com.revature.project.parser.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/specs")
public class SpecificationController {

  private final SpecificationService specificationService;
  private final JwtTokenUtil jwtTokenUtil;

  public SpecificationController(SpecificationService specificationService, JwtTokenUtil jwtTokenUtil) {
    this.specificationService = specificationService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Specification saveSpecifications(@RequestPart("file") MultipartFile file, @RequestPart("name") String name,
      HttpServletRequest request)
      throws InvalidJwtException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    return specificationService.store(file, name, userId);
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public List<Specification> getAll(HttpServletRequest request) throws UserNotFoundException, InvalidJwtException {
    String userId = jwtTokenUtil.getUserIdFromRequest(request);
    return specificationService.findAllByUserId(userId);
  }
}
