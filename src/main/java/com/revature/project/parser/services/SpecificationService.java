package com.revature.project.parser.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project.parser.models.Specification;
import com.revature.project.parser.models.User;
import com.revature.project.parser.repositories.SpecificationRepository;

@Service
public class SpecificationService {

  private final SpecificationRepository specificationRepository;
  private final LocalStorageService localStorageService;
  private final UserService userService;
  private final ObjectMapper objectMapper;

  public SpecificationService(SpecificationRepository specificationRepository, LocalStorageService localStorageService,
      UserService userService,
      ObjectMapper objectMapper) {
    this.specificationRepository = specificationRepository;
    this.localStorageService = localStorageService;
    this.userService = userService;
    this.objectMapper = objectMapper;
  }

  public Specification store(MultipartFile file, String username) {
    try {
      Map<String, com.revature.project.parser.models.Field> parsedMap = objectMapper.readValue(file.getInputStream(),
          new TypeReference<Map<String, com.revature.project.parser.models.Field>>() {
          });
      Specification specification = new Specification();
      String foundUserId = userService.findHexIdByUsername(username);
      specification.setUserId(foundUserId);
      specification.setSpecs(parsedMap);
      specificationRepository.save(specification);
      localStorageService.store(file, "specification");
      return specification;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Specification> findAll(String username) {
    User found = userService.findByUsername(username);
    if (found == null) {
      return Arrays.asList();
    }
    return specificationRepository.findAllByUserId(found.getId().toHexString());
  }

  public Specification findById(String id) {
    return specificationRepository.findById(new ObjectId(id)).get();
  }

}
