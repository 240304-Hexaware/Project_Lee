package com.revature.project.parser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.UserAleadyExistedException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.User;
import com.revature.project.parser.payload.request.LoginRequest;
import com.revature.project.parser.payload.request.RegistrationRequest;
import com.revature.project.parser.services.UserService;
import com.revature.project.parser.utils.PasswordEncoderUtil;

@RestController
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public User authenticate(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
    // TODO: validate login request
    User found = userService.findByUsername(loginRequest.username());
    if (found != null && PasswordEncoderUtil.matches(loginRequest.password(), found.getPassword())) {
      return found;
    }
    throw new UserNotFoundException("Invalid credentials");
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public User register(@RequestBody RegistrationRequest registrationRequest) throws UserAleadyExistedException {
    // TODO: validate username and password
    User requestedUser = new User(registrationRequest.username(),
        PasswordEncoderUtil.encodePassword(registrationRequest.password()));
    if (userService.findByUsername(registrationRequest.username()) != null) {
      throw new UserAleadyExistedException();
    }

    return userService.saveUser(requestedUser);
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFound(UserNotFoundException e) {
    return e.getMessage();
  }

  @ExceptionHandler(UserAleadyExistedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String userAleadyExisted(UserAleadyExistedException e) {
    return e.getMessage();
  }
}
