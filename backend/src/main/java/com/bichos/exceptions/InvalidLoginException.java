package com.bichos.exceptions;

public class InvalidLoginException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidLoginException() {
    super("Username or password are not right.");
  }

}
