package com.bichos.exceptions;

public class UserAlreadyCreatedException extends RuntimeException {

  private static final long serialVersionUID = 9055803811285096732L;

  public UserAlreadyCreatedException() {
    super("Email or nickname were already created.");
  }

}
