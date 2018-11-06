package com.bichos.handlers.errors;

import java.util.Map;

import com.bichos.handlers.ApiErrorHandler;
import com.google.inject.Inject;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ErrorHandler implements Handler<RoutingContext> {

  @SuppressWarnings("rawtypes")
  private final Map<Class, ApiErrorHandler> errorsHandlers;

  @Override
  public void handle(final RoutingContext context) {
    if (context.failure() == null) {
      context.response().setStatusCode(context.statusCode()).end();
    } else {
      final ApiErrorHandler handler = errorsHandlers.get(context.failure().getClass());
      if (handler == null) {
        log.error("Unexpected error in handler.", context.failure());
        context.response().end();
      } else {
        handler.handle(context);
      }
    }
  }

}
