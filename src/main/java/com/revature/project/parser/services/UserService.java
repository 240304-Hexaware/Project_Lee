package com.revature.project.parser.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.project.parser.exceptions.UserNotFoundException;
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

  public String findHexIdByUsername(String username) {
    Optional<User> found = userRepository.findByUsername(username);
    if (found.isPresent()) {
      return found.get().getId().toHexString();
    }
    return null;
  }

  public User promoteUser(String username) throws UserNotFoundException {
    User found = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    System.out.println(found.getIsDisabled());
    found.setIsAdmin(true);
    return userRepository.save(found);
  }

  public User demoteUser(String username) throws UserNotFoundException {
    User found = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    found.setIsAdmin(false);
    return userRepository.save(found);
  }

  public User disableUser(String username) throws UserNotFoundException {
    User found = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    found.setIsDisabled(true);
    return userRepository.save(found);
  }

  public User enableUser(String username) throws UserNotFoundException {
    User found = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    found.setIsDisabled(false);
    return userRepository.save(found);
  }

}
