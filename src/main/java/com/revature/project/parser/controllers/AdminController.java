package com.revature.project.parser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.exceptions.UnauthorizedException;
import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.User;
import com.revature.project.parser.services.UserService;
import com.revature.project.parser.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admins")
public class AdminController {

  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  public AdminController(UserService userService, JwtTokenUtil jwtTokenUtil) {
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PatchMapping("/promote/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User promoteUser(@PathVariable("userId") String userId, HttpServletRequest request)
      throws UserNotFoundException, InvalidJwtException, UnauthorizedException {
    validateAuthorization(request);
    return userService.promoteUser(userId);
  }

  @PatchMapping("/demote/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User demoteUser(@PathVariable("userId") String userId, HttpServletRequest request)
      throws UserNotFoundException, UnauthorizedException, InvalidJwtException {
    validateAuthorization(request);
    return userService.demoteUser(userId);
  }

  @PatchMapping("/disable/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User disableUser(@PathVariable("userId") String userId, HttpServletRequest request)
      throws UserNotFoundException, UnauthorizedException, InvalidJwtException {
    validateAuthorization(request);
    return userService.disableUser(userId);
  }

  @PatchMapping("/enable{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User enableUser(@PathVariable("userId") String userId, HttpServletRequest request)
      throws UserNotFoundException, InvalidJwtException, UnauthorizedException {
    validateAuthorization(request);
    return userService.enableUser(userId);
  }

  private void validateAuthorization(HttpServletRequest request) throws InvalidJwtException, UnauthorizedException {
    if (!jwtTokenUtil.isAdmin(request)) {
      throw new UnauthorizedException("unauthorized");
    }
  }

  // TODO: Delete archived files
  // TODO: Delete or modify records

}
