package com.bichos.handlers.authentication;

import com.bichos.exceptions.InvalidLoginException;
import com.bichos.handlers.ApiHandler;
import com.bichos.mappers.LoginMappersUtils;
import com.bichos.models.ApiLoginRequest;
import com.bichos.services.AuthenticationService;
import com.bichos.services.ValidationService;
import com.bichos.utils.HandlerUtils;
import com.bichos.utils.HttpStatus;
import com.google.inject.Inject;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class LoginHandler implements ApiHandler {

  private static final String PATH = "/login";
  private static final HttpMethod METHOD = HttpMethod.POST;

  private final AuthenticationService authenticationService;

  private final ValidationService validationService;

  @Override
  public void handle(final RoutingContext context) {
    final ApiLoginRequest request = HandlerUtils.jsonRequest(context, ApiLoginRequest.class);
    validationService.validate(request);

    authenticationService.login(request.getUsername(), request.getPassword()).setHandler(result -> {
      if (result.succeeded()) {
        HandlerUtils.jsonResponse(context, LoginMappersUtils.mapTokenToResponse(result.result()));
      } else if (result.cause() != null && result.cause() instanceof InvalidLoginException) {
        context.fail(HttpStatus.UNAUTHORIZED.getStatusCode());
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Override
  public String getPath() {
    return PATH;
  }

  @Override
  public HttpMethod getMethod() {
    return METHOD;
  }

  @Override
  public boolean hasAuthentication() {
    return false;
  }

}
