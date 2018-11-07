package com.bichos.services.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.bichos.exceptions.ValidationException;
import com.bichos.services.ValidationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JavaxValidationService implements ValidationService {

  private final Validator validator;

  @Override
  public <T> void validate(final T obj) {
    final Set<ConstraintViolation<Object>> errors = validator.validate(obj);
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

}
