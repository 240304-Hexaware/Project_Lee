package com.revature.project.parser.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.project.parser.models.Specification;
import com.revature.project.parser.services.SpecificationService;

@RestController
public class SpecificationController {

  private final SpecificationService specificationService;

  public SpecificationController(SpecificationService specificationService) {
    this.specificationService = specificationService;
  }

  @PostMapping("/specs/{username}")
  @ResponseStatus(HttpStatus.OK)
  public Specification saveSpecifications(@RequestParam("file") MultipartFile file,
      @PathVariable("username") String username) {
    return specificationService.store(file, username);
  }

  @GetMapping("/specs/{username}")
  @ResponseStatus(HttpStatus.OK)
  public List<Specification> getAll(@PathVariable("username") String username) {
    return specificationService.findAll(username);
  }
}
