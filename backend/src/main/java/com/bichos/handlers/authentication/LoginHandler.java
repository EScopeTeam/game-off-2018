package com.bichos.handlers.authentication;

import com.bichos.exceptions.InvalidLoginException;
import com.bichos.mappers.LoginMappers;
import com.bichos.models.ApiLoginRequest;
import com.bichos.services.AuthenticationService;
import com.bichos.utils.HandlerUtils;
import com.bichos.utils.HttpStatusCode;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginHandler implements Handler<RoutingContext> {

  private final AuthenticationService authenticationService;

  @Override
  public void handle(final RoutingContext context) {
    final ApiLoginRequest request = HandlerUtils.jsonRequest(context, ApiLoginRequest.class);
    authenticationService.login(request.getUsername(), request.getPassword()).setHandler(result -> {
      if (result.succeeded()) {
        HandlerUtils.jsonResponse(context, LoginMappers.mapTokenToResponse(result.result()));
      } else if (result.cause() != null && result.cause() instanceof InvalidLoginException) {
        context.fail(HttpStatusCode.UNAUTHORIZED.getStatusCode());
      } else {
        context.fail(result.cause());
      }
    });
  }

}
