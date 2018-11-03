package com.bichos.verticles;

import com.bichos.binders.ConfigModule;
import com.bichos.binders.HandlersModule;
import com.bichos.binders.RepositoriesModule;
import com.bichos.binders.ServicesModule;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.authentication.LoginHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiVerticle extends AbstractVerticle {

  private static final String KEY_PORT = "port";
  private static final int DEFAULT_PORT = 8080;

  private static final String API_DESCRIPTION_PATH = "src/main/resources/api-v1.yml";

  private Injector injector;

  @Override
  public void start(final Future<Void> fStart) {
    initializeGuice();

    OpenAPI3RouterFactory.create(vertx, API_DESCRIPTION_PATH, routerFactoryResult -> {
      if (routerFactoryResult.succeeded()) {
        final OpenAPI3RouterFactory routerFactory = routerFactoryResult.result();
        addHandlersToRoute(routerFactory);
        final Router router = routerFactory.getRouter();

        final int port = config().getInteger(KEY_PORT, DEFAULT_PORT);
        final HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(port, (serverResult) -> handleServerStart(port, serverResult, fStart));
      } else {
        fStart.fail(routerFactoryResult.cause());
      }
    });
  }

  private void initializeGuice() {
    injector = Guice.createInjector(new ConfigModule(vertx, config()), new RepositoriesModule(), new ServicesModule(), new HandlersModule());
  }

  private void handleServerStart(final int port, final AsyncResult<HttpServer> serverResult, final Future<Void> fStart) {
    if (serverResult.succeeded()) {
      log.info("HttpServer started on port " + port);
      fStart.complete();
    } else {
      fStart.fail(serverResult.cause());
    }
  }

  private void addHandlersToRoute(final OpenAPI3RouterFactory routerFactory) {
    routerFactory
        .addHandlerByOperationId("login", injector.getInstance(LoginHandler.class))
        .addSecurityHandler("bearerAuth", injector.getInstance(AuthenticationHandler.class));
  }

}
