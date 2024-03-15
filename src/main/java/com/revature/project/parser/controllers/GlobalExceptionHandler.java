package com.revature.project.parser.controllers;

import javax.security.auth.login.CredentialException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.UserAleadyExistedException;
import com.revature.project.parser.exceptions.UserNotFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFound(UserNotFoundException e) {
    return e.getMessage();
  }

  @ExceptionHandler(CredentialException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public String credentialException(CredentialException e) {
    return e.getMessage();
  }

  @ExceptionHandler(InvalidJwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public String invalidJwtException(InvalidJwtException e) {
    return e.getMessage();
  }

  @ExceptionHandler(UserAleadyExistedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public String userAleadyExisted(UserAleadyExistedException e) {
    return e.getMessage();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String methodArgumentNotValid(MethodArgumentNotValidException e) {
    return "Invalid credentials";
  }

  @ExceptionHandler(ItemNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String itemNotFound(ItemNotFoundException e) {
    return e.getMessage();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String exception(Exception e) {
    return e.getMessage();
  }

}
