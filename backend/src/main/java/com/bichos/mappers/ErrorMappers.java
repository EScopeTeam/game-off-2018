package com.bichos.mappers;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import com.bichos.exceptions.ValidationException;
import com.bichos.models.ApiEntityError;
import com.bichos.models.ApiEntityErrors;

public final class ErrorMappers {

  private ErrorMappers() {
  }

  public static ApiEntityErrors mapValidationExceptionToApiErrors(final ValidationException exception) {
    final ApiEntityErrors result = new ApiEntityErrors();
    result.setErrors(exception.getErrors().stream().map(ErrorMappers::mapValidationErrorToApiError).collect(Collectors.toList()));

    return result;
  }

  private static ApiEntityError mapValidationErrorToApiError(final ConstraintViolation<Object> error) {
    final ApiEntityError result = new ApiEntityError();
    result.setAttribute(error.getPropertyPath().toString());
    result.setCode(error.getMessageTemplate());
    result.setDescription(error.getMessage());

    return result;
  }

}
