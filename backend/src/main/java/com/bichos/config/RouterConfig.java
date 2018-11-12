package com.bichos.config;

import java.util.Set;

import com.bichos.exceptions.ResourceNotFoundException;
import com.bichos.handlers.ApiHandler;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.errors.ErrorHandler;
import com.google.inject.Inject;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RouterConfig {

  private static final int BODY_ORDER = 0;
  private static final int CORS_ORDER = 1;
  private static final int AUTHENTICATION_ORDER = 2;
  private static final int NOT_FOUND_ORDER = 50;
  private static final int DEFAULT_ORDER = 5;

  private static final String EVENTBUS_PATH = "/websocket";

  private final Vertx vertx;

  private final AuthenticationHandler authenticationHandler;

  private final Set<ApiHandler> handlers;

  private final ErrorHandler errorHandler;

  private final WebsocketConfig websocketConfig;

  public Router getRouter() {
    final Router router = Router.router(vertx);
    router.route().order(BODY_ORDER).handler(BodyHandler.create());

    router.route().order(CORS_ORDER).handler(context -> {
      context.response()
          .putHeader("Access-Control-Allow-Origin", "*")
          .putHeader("Access-Control-Allow-Credentials", "false");
      context.next();
    });

    for (final ApiHandler handler : handlers) {
      if (handler.hasAuthentication()) {
        router.route(handler.getMethod(), handler.getPath())
            .order(AUTHENTICATION_ORDER)
            .handler(authenticationHandler);
      }
      router.route(handler.getMethod(), handler.getPath())
          .order(DEFAULT_ORDER)
          .handler(handler);
    }

    router.route(EVENTBUS_PATH + "/*").handler(websocketConfig.getSockJSHandler());

    router.route().order(NOT_FOUND_ORDER)
        .handler(context -> context.fail(new ResourceNotFoundException(context.request().method().name(), context.request().path())));

    router.route().failureHandler(errorHandler);

    return router;
  }

}
