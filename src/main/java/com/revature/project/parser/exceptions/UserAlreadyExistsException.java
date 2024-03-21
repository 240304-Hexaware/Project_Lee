package com.revature.project.parser.exceptions;

public class UserAlreadyExistsException extends Exception {

  public UserAlreadyExistsException() {
    super("Username is already taken");
  }

}
