package com.bichos.binders;

import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.authentication.LoginHandler;
import com.bichos.services.AuthenticationService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import io.vertx.ext.auth.jwt.JWTAuth;

public class HandlersModule extends AbstractModule {

  @Provides
  @Singleton
  public AuthenticationHandler provideAuthenticationHandler(final JWTAuth jwtAuth) {
    return new AuthenticationHandler(jwtAuth);
  }

  @Provides
  @Singleton
  public LoginHandler provideLoginHandler(final AuthenticationService authService) {
    return new LoginHandler(authService);
  }

}
