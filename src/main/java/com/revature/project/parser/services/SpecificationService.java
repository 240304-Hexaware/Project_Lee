package com.revature.project.parser.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.Field;
import com.revature.project.parser.models.Specification;
import com.revature.project.parser.models.User;
import com.revature.project.parser.repositories.SpecificationRepository;
import com.revature.project.parser.services.LocalStorageService.Folder;

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

  public Specification store(MultipartFile file, String userId) {
    Objects.requireNonNull(userId);
    try {
      Map<String, Field> parsedMap = objectMapper.readValue(file.getInputStream(),
          new TypeReference<Map<String, Field>>() {
          });
      Specification specification = new Specification(userId, parsedMap);
      specificationRepository.save(specification);
      localStorageService.store(file, Folder.SPECIFICATION);
      return specification;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Specification> findAllByUserId(String userId) throws UserNotFoundException {
    User found = userService.findByUserId(userId);
    if (found == null) {
      return Arrays.asList();
    }
    return specificationRepository.findAllByUserId(found.getId().toHexString());
  }

  public Specification findById(String userId) throws ItemNotFoundException {
    Optional<Specification> found = specificationRepository.findById(new ObjectId(userId));
    if (found.isEmpty()) {
      throw new ItemNotFoundException("No such specification file");
    }
    return found.get();
  }

}
