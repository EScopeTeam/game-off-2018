package com.bichos.verticles;

import java.util.Set;

import com.bichos.binders.GuiceInitializer;
import com.bichos.exceptions.ResourceNotFoundException;
import com.bichos.handlers.ApiHandler;
import com.bichos.handlers.ApiWSHandler;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.errors.ErrorHandler;
import com.google.inject.Injector;
import com.google.inject.Key;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiVerticle extends AbstractVerticle {

  private static final String KEY_PORT = "port";
  private static final int DEFAULT_PORT = 8080;

  private static final String EVENTBUS_PATH = "/websocket";

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

  private void addHandlersToRoute(final Router router) {
    router.route().order(ApiHandler.BODY_ORDER).handler(BodyHandler.create());
    router.route().order(ApiHandler.AUTHENTICATION_ORDER).handler(injector.getInstance(AuthenticationHandler.class));

    for (final ApiHandler handler : getApiHandlers()) {
      router
          .route(handler.getMethod(), handler.getPath())
          .order(handler.getOrder())
          .handler(handler);
    }

    router.route(EVENTBUS_PATH + "/*").handler(createSockJsHandler());

    router.route()
        .order(ApiHandler.NOT_FOUND_ORDER)
        .handler(context -> context.fail(new ResourceNotFoundException(context.request().method().name(), context.request().path())));
    router.route().failureHandler(injector.getInstance(ErrorHandler.class));
  }

  private Set<ApiHandler> getApiHandlers() {
    return injector.getInstance(new Key<Set<ApiHandler>>() {
    });
  }

  private SockJSHandler createSockJsHandler() {
    final BridgeOptions options = new BridgeOptions();

    for (final ApiWSHandler handler : getApiWSHandlers()) {
      final String address = handler.getAddress();
      options.addInboundPermitted(ApiWSHandler.createPermittedOptionsFromAddress(address));
      vertx.eventBus().<JsonObject>consumer(address).handler(handler);
    }

    final SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
    sockJSHandler.bridge(options);

    return sockJSHandler;
  }

  private Set<ApiWSHandler> getApiWSHandlers() {
    return injector.getInstance(new Key<Set<ApiWSHandler>>() {
    });
  }

  private void handleServerStart(final int port, final AsyncResult<HttpServer> serverResult, final Future<Void> fStart) {
    if (serverResult.succeeded()) {
      log.info("HttpServer started on port " + port);
      fStart.complete();
    } else {
      fStart.fail(serverResult.cause());
    }
  }

}
