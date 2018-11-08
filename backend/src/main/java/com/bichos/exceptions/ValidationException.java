package com.bichos.exceptions;

import static java.util.stream.Collectors.joining;

import java.util.Set;

import javax.validation.ConstraintViolation;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = 8886676067105660606L;

  private final Set<ConstraintViolation<Object>> errors;

  public ValidationException(final Set<ConstraintViolation<Object>> errors) {
    super("There were some validation errors: " + formatMessage(errors));
    this.errors = errors;
  }

  private static <T> String formatMessage(final Set<ConstraintViolation<T>> errors) {
    return errors.stream().map(error -> "[" + error.getPropertyPath() + ": " + error.getMessage() + "]").collect(joining(", "));
  }

}
