package com.bichos.mappers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import com.bichos.exceptions.ValidationException;
import com.bichos.models.ApiEntityError;
import com.bichos.models.ApiEntityErrors;

public final class ErrorMappersUtils {

  private ErrorMappersUtils() {
  }

  public static ApiEntityErrors mapValidationExceptionToApiErrors(final ValidationException exception) {
    final ApiEntityErrors result = new ApiEntityErrors();
    result.setErrors(exception.getErrors().stream().map(ErrorMappersUtils::mapValidationErrorToApiError).collect(Collectors.toList()));

    return result;
  }

  private static ApiEntityError mapValidationErrorToApiError(final ConstraintViolation<Object> error) {
    final ApiEntityError result = new ApiEntityError();
    result.setAttribute(error.getPropertyPath().toString());
    result.setCode(error.getMessageTemplate());
    result.setDescription(error.getMessage());
    result.setParams(mapErrorParams(error.getConstraintDescriptor().getAttributes()));

    return result;
  }

  private static Map<String, String> mapErrorParams(final Map<String, Object> params) {
    return params == null ? new HashMap<>()
        : params.entrySet().stream().filter(ErrorMappersUtils::filterParam).collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
  }

  private static boolean filterParam(final Map.Entry<String, Object> param) {
    final String key = param.getKey();

    return !"payload".equals(key) && !"groups".equals(key) && !"message".equals(key);
  }

}
