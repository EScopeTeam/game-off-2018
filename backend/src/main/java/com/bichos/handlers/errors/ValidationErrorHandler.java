package com.bichos.handlers.errors;

import com.bichos.exceptions.ValidationException;
import com.bichos.handlers.ApiErrorHandler;
import com.bichos.mappers.ErrorMappers;
import com.bichos.models.ApiEntityErrors;
import com.bichos.utils.HandlerUtils;
import com.bichos.utils.HttpStatus;

import io.vertx.ext.web.RoutingContext;

public class ValidationErrorHandler implements ApiErrorHandler {

  @Override
  public void handle(final RoutingContext context) {
    final ApiEntityErrors response = ErrorMappers.mapValidationExceptionToApiErrors((ValidationException) context.failure());
    context.response().setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.getStatusCode());
    HandlerUtils.jsonResponse(context, response);
  }

}
