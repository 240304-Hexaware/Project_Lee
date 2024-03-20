package com.revature.project.parser.exceptions;

public class UserAleadyExistsException extends Exception {

  public UserAleadyExistsException() {
    super("Username is aleady taken");
  }

}
