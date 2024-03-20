package com.revature.project.parser.exceptions;

public class InvalidJwtException extends Exception {
  public InvalidJwtException() {
    super("Invalid or expired JWT");
  }
}
