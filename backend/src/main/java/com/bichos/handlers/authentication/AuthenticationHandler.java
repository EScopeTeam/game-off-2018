package com.bichos.handlers.authentication;

import io.vertx.core.Handler;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.JWTAuthHandler;

public class AuthenticationHandler implements Handler<RoutingContext> {

  private final JWTAuthHandler authHandler;

  public AuthenticationHandler(final JWTAuth jwtAuth) {
    authHandler = JWTAuthHandler.create(jwtAuth);
  }

  @Override
  public void handle(final RoutingContext event) {
    authHandler.handle(event);
  }

}
