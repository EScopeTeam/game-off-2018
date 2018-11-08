package com.bichos.handlers.errors;

import com.bichos.exceptions.ResourceNotFoundException;
import com.bichos.handlers.ApiErrorHandler;
import com.bichos.utils.HttpStatus;

import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundHandler implements ApiErrorHandler {

  @Override
  public void handle(final RoutingContext context) {
    final ResourceNotFoundException exception = (ResourceNotFoundException) context.failure();
    log.info("Resource not found " + exception.getMethod() + " " + exception.getPath());
    context.response().setStatusCode(HttpStatus.NOT_FOUND.getStatusCode()).end();
  }

}
