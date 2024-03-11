package com.revature.project.parser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.UserNotFoundException;
import com.revature.project.parser.models.User;
import com.revature.project.parser.services.UserService;

@RestController
@RequestMapping("/admins")
public class AdminController {

  private final UserService userService;

  public AdminController(UserService userService) {
    this.userService = userService;
  }

  @PatchMapping("/promote")
  @ResponseStatus(HttpStatus.OK)
  public User promoteUser(@RequestParam("username") String username) throws UserNotFoundException {
    return userService.promoteUser(username);
  }

  @PatchMapping("/demote")
  @ResponseStatus(HttpStatus.OK)
  public User demoteUser(@RequestParam("username") String username) throws UserNotFoundException {
    return userService.demoteUser(username);
  }

  @PatchMapping("/disable")
  @ResponseStatus(HttpStatus.OK)
  public User disableUser(@RequestParam("username") String username) throws UserNotFoundException {
    return userService.disableUser(username);
  }

  @PatchMapping("/enable")
  @ResponseStatus(HttpStatus.OK)
  public User enableUser(@RequestParam("username") String username) throws UserNotFoundException {
    return userService.enableUser(username);
  }

  // TODO: Delete archived files
  // TODO: Delete or modify records

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFound(UserNotFoundException e) {
    return e.getMessage();
  }

}
