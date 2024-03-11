package com.revature.project.parser.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.project.parser.models.User;
import com.revature.project.parser.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

}
