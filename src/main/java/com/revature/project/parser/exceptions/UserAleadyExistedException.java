package com.revature.project.parser.exceptions;

public class UserAleadyExistedException extends Exception {

  public UserAleadyExistedException() {
    super("Username is aleady taken");
  }

}
