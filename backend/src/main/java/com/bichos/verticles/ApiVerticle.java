package com.bichos.verticles;

import com.bichos.binders.GuiceHelper;
import com.bichos.config.RouterConfig;
import com.google.inject.Injector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiVerticle extends AbstractVerticle {

  private static final String KEY_PORT = "port";
  private static final int DEFAULT_PORT = 8080;

  private Injector injector;

  @Override
  public void start(final Future<Void> fStart) {
    initializeGuice();

    final RouterConfig routerConfig = injector.getInstance(RouterConfig.class);
    final Router router = routerConfig.getRouter();

    final int port = config().getInteger(KEY_PORT, DEFAULT_PORT);
    final HttpServer server = vertx.createHttpServer();
    server.requestHandler(router::accept).listen(port, (serverResult) -> handleServerStart(port, serverResult, fStart));
  }

  private void initializeGuice() {
    injector = GuiceHelper.initialize(vertx, config());
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
