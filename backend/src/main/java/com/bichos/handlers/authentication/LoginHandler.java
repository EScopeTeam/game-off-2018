package com.bichos.handlers.authentication;

import com.bichos.exceptions.InvalidLoginException;
import com.bichos.services.AuthenticationService;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginHandler implements Handler<RoutingContext> {

  private static final int BAD_REQUEST_CODE = 400;

  private final AuthenticationService authenticationService;

  // TODO use model, validate model and use utils for response
  @Override
  public void handle(final RoutingContext context) {
    final JsonObject body = context.getBodyAsJson();
    authenticationService.login(body.getString("username"), body.getString("password")).setHandler(result -> {
      if (result.succeeded()) {
        context.response().putHeader("content-type", "application/json").end(new JsonObject().put("token", result.result()).encode());
      } else if (result.cause() != null && result.cause() instanceof InvalidLoginException) {
        context.fail(BAD_REQUEST_CODE); // TODO use enum
      } else {
        context.fail(result.cause());
      }
    });
  }

}
