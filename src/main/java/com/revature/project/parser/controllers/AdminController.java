package com.revature.project.parser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @PatchMapping("/promote/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User promoteUser(@PathVariable("userId") String userId) throws UserNotFoundException {
    return userService.promoteUser(userId);
  }

  @PatchMapping("/demote/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User demoteUser(@PathVariable("userId") String userId) throws UserNotFoundException {
    return userService.demoteUser(userId);
  }

  @PatchMapping("/disable/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User disableUser(@PathVariable("userId") String userId) throws UserNotFoundException {
    return userService.disableUser(userId);
  }

  @PatchMapping("/enable{userId}")
  @ResponseStatus(HttpStatus.OK)
  public User enableUser(@PathVariable("userId") String userId) throws UserNotFoundException {
    return userService.enableUser(userId);
  }

  // TODO: Delete archived files
  // TODO: Delete or modify records

}
