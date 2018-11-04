package com.bichos.verticles;

import java.util.Set;

import com.bichos.binders.GuiceInitializer;
import com.bichos.handlers.ApiHandler;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.errors.ErrorHandler;
import com.google.inject.Injector;
import com.google.inject.Key;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiVerticle extends AbstractVerticle {

  private static final String KEY_PORT = "port";
  private static final int DEFAULT_PORT = 8080;

  private Injector injector;

  @Override
  public void start(final Future<Void> fStart) {
    initializeGuice();

    final Router router = Router.router(vertx);
    addHandlersToRoute(router);

    final int port = config().getInteger(KEY_PORT, DEFAULT_PORT);
    final HttpServer server = vertx.createHttpServer();
    server.requestHandler(router::accept).listen(port, (serverResult) -> handleServerStart(port, serverResult, fStart));
  }

  private void initializeGuice() {
    injector = GuiceInitializer.initialize(vertx, config());
  }

  private void handleServerStart(final int port, final AsyncResult<HttpServer> serverResult, final Future<Void> fStart) {
    if (serverResult.succeeded()) {
      log.info("HttpServer started on port " + port);
      fStart.complete();
    } else {
      fStart.fail(serverResult.cause());
    }
  }

  private void addHandlersToRoute(final Router router) {
    router.route().order(ApiHandler.BODY_ORDER).handler(BodyHandler.create());
    router.route().order(ApiHandler.AUTHENTICATION_ORDER).handler(injector.getInstance(AuthenticationHandler.class));

    for (final ApiHandler handler : getApiHandlers()) {
      router
          .route(handler.getMethod(), handler.getPath())
          .order(handler.getOrder())
          .handler(handler);
    }

    router.route().failureHandler(injector.getInstance(ErrorHandler.class));
  }

  private Set<ApiHandler> getApiHandlers() {
    return injector.getInstance(new Key<Set<ApiHandler>>() {
    });
  }

}
