package com.bichos.handlers.authentication;

import com.bichos.exceptions.UserAlreadyCreatedException;
import com.bichos.handlers.ApiHandler;
import com.bichos.mappers.SignUpMappers;
import com.bichos.models.ApiSignUpRequest;
import com.bichos.services.AuthenticationService;
import com.bichos.services.ValidationService;
import com.bichos.utils.HandlerUtils;
import com.bichos.utils.HttpStatus;
import com.google.inject.Inject;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SignUpHandler implements ApiHandler {

  private static final String PATH = "/signup";
  private static final HttpMethod METHOD = HttpMethod.POST;

  private final ValidationService validationService;

  private final AuthenticationService authenticationService;

  @Override
  public void handle(final RoutingContext context) {
    final ApiSignUpRequest request = HandlerUtils.jsonRequest(context, ApiSignUpRequest.class);
    validationService.validate(request);

    authenticationService.signUp(SignUpMappers.mapApiToModel(request)).setHandler(result -> {
      if (result.succeeded()) {
        context.response().setStatusCode(HttpStatus.CREATED.getStatusCode());
      } else if (result.failed() && result.cause() instanceof UserAlreadyCreatedException) {
        context.fail(HttpStatus.CONFLICT.getStatusCode());
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
