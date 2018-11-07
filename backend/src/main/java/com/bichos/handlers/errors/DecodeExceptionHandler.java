package com.bichos.handlers.errors;

import com.bichos.handlers.ApiErrorHandler;
import com.bichos.utils.HttpStatus;

import io.vertx.ext.web.RoutingContext;

public class DecodeExceptionHandler implements ApiErrorHandler {

  @Override
  public void handle(final RoutingContext context) {
    context.response().setStatusCode(HttpStatus.BAD_REQUEST.getStatusCode()).end();
  }

}
