package com.revature.project.parser.controllers;

import javax.security.auth.login.CredentialException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.UserAleadyExistedException;
import com.revature.project.parser.models.User;
import com.revature.project.parser.payload.request.LoginRequest;
import com.revature.project.parser.payload.request.RegistrationRequest;
import com.revature.project.parser.payload.response.LoginResponse;
import com.revature.project.parser.services.UserService;
import com.revature.project.parser.utils.PasswordEncoderUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public LoginResponse authenticate(@RequestBody @Valid LoginRequest loginRequest) throws CredentialException {
    User found = userService.findByUsername(loginRequest.username());
    if (found != null &&
        !found.getIsDisabled() &&
        PasswordEncoderUtil.matches(loginRequest.password(), found.getPassword())) {
      // TODO: token
      return new LoginResponse(found.getId().toHexString(), found.getUsername(), found.getIsAdmin(), null);
    }
    throw new CredentialException("Invalid credentials");
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public User register(@RequestBody @Valid RegistrationRequest registrationRequest) throws UserAleadyExistedException {
    User requestedUser = new User(registrationRequest.username(),
        PasswordEncoderUtil.encodePassword(registrationRequest.password()));
    if (userService.findByUsername(registrationRequest.username()) != null) {
      throw new UserAleadyExistedException();
    }

    return userService.saveUser(requestedUser);
  }
}
