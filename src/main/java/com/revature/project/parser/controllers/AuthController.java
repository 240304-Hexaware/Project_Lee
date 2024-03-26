package com.revature.project.parser.controllers;

import java.time.Duration;

import javax.security.auth.login.CredentialException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.UserAlreadyExistsException;
import com.revature.project.parser.models.User;
import com.revature.project.parser.payload.request.LoginRequest;
import com.revature.project.parser.payload.request.RegistrationRequest;
import com.revature.project.parser.payload.response.LoginResponse;
import com.revature.project.parser.services.UserService;
import com.revature.project.parser.utils.JwtTokenUtil;
import com.revature.project.parser.utils.PasswordEncoderUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class AuthController {

  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public LoginResponse authenticate(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response)
      throws CredentialException {
    User found = userService.findByUsername(loginRequest.username());
    if (found == null) {
      throw new CredentialException("Invalid username or password");
    }
    if (found.getIsDisabled().booleanValue()) {
      throw new CredentialException("The user has been banned");
    }
    if (PasswordEncoderUtil.matches(loginRequest.password(), found.getPassword())) {
      String createdJwt = jwtTokenUtil.createJwt(found);
      ResponseCookie cookie = ResponseCookie.from(JwtTokenUtil.TOKEN_NAME, createdJwt)
          .httpOnly(true)
          .secure(true)
          .path("/")
          .build();
      response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
      return new LoginResponse(found.getId().toHexString(), found.getUsername(), found.getIsAdmin());
    }
    throw new CredentialException("Invalid username or password");
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public User register(@RequestBody @Valid RegistrationRequest registrationRequest) throws UserAlreadyExistsException {
    User requestedUser = new User(registrationRequest.username(),
        PasswordEncoderUtil.encodePassword(registrationRequest.password()));
    if (userService.findByUsername(registrationRequest.username()) != null) {
      throw new UserAlreadyExistsException();
    }

    return userService.saveUser(requestedUser);
  }

  @PostMapping("/logout")
  @ResponseStatus(HttpStatus.OK)
  public void logout(HttpServletResponse response) {
    ResponseCookie emptyCookie = createEmptyCookie();
    response.addHeader(HttpHeaders.SET_COOKIE, emptyCookie.toString());
  }

  private ResponseCookie createEmptyCookie() {
    return ResponseCookie.from(JwtTokenUtil.TOKEN_NAME, "")
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(Duration.ofDays(0))
        .build();
  }
}
