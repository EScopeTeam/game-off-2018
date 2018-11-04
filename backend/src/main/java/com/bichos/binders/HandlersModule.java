package com.bichos.binders;

import com.bichos.handlers.ApiHandler;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.authentication.LoginHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

import io.vertx.ext.auth.jwt.JWTAuth;

public class HandlersModule extends AbstractModule {

  @Override
  protected void configure() {
    final Multibinder<ApiHandler> apiHandlerBinder = Multibinder.newSetBinder(binder(), ApiHandler.class);
    apiHandlerBinder.addBinding().to(LoginHandler.class);
  }

  @Provides
  @Singleton
  public AuthenticationHandler provideAuthenticationHandler(final JWTAuth jwtAuth) {
    return new AuthenticationHandler(jwtAuth);
  }

}
