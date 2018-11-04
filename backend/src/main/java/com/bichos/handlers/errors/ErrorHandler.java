package com.bichos.handlers.errors;

import com.bichos.exceptions.ValidationException;
import com.bichos.mappers.ErrorMappers;
import com.bichos.models.ApiEntityErrors;
import com.bichos.utils.HandlerUtils;
import com.bichos.utils.HttpStatus;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorHandler implements Handler<RoutingContext> {

  @Override
  public void handle(final RoutingContext context) {
    if (context.failure() == null) {
      context.response().end();
    } else {
      if (context.failure() instanceof ValidationException) {
        final ApiEntityErrors response = ErrorMappers.mapValidationExceptionToApiErrors((ValidationException) context.failure());
        context.response().setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.getStatusCode());
        HandlerUtils.jsonResponse(context, response);
      } else {
        log.error("Unexpected error in handler.", context.failure());
        context.response().end();
      }
    }
  }

}
