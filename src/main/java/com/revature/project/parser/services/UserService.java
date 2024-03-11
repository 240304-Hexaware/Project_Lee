package com.revature.project.parser.services;

import java.util.List;
import java.util.Optional;

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

  public User saveUser(User requestedUser) {
    return userRepository.save(requestedUser);
  }

  public User findByUsername(String username) {
    Optional<User> found = userRepository.findByUsername(username);
    if (found.isPresent()) {
      return found.get();
    }
    return null;
  }

}
