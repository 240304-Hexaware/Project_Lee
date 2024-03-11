package com.revature.project.parser.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.models.User;
import com.revature.project.parser.payload.request.LoginRequest;
import com.revature.project.parser.payload.request.RegistrationRequest;
import com.revature.project.parser.services.UserService;

@RestController
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public User login(@RequestBody LoginRequest loginRequest) {
    return null;
  }

  @PostMapping("/register")
  public User register(@RequestBody RegistrationRequest registrationRequest) {
    return null;
  }

}
