package com.revature.project.parser.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.CredentialException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project.parser.exceptions.InvalidJwtException;
import com.revature.project.parser.exceptions.ItemNotFoundException;
import com.revature.project.parser.exceptions.UserAlreadyExistsException;
import com.revature.project.parser.exceptions.UserNotFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError handleUserNotFound(UserNotFoundException e) {
    return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
  }

  @ExceptionHandler(CredentialException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ApiError handleCredentialException(CredentialException e) {
    return new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
  }

  @ExceptionHandler(InvalidJwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ApiError handleInvalidJwtException(InvalidJwtException e) {
    return new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ApiError handleUserAleadyExists(UserAlreadyExistsException e) {
    return new ApiError(HttpStatus.CONFLICT.value(), e.getMessage(), null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    List<String> details = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());
    return new ApiError(HttpStatus.BAD_REQUEST.value(), "Invalid input", details);
  }

  @ExceptionHandler(ItemNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError handleItemNotFound(ItemNotFoundException e) {
    return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handleException(Exception e) {
    return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
  }

  record ApiError(int status, String title, List<String> details) {
  }

}
