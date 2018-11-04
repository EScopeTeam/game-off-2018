package com.bichos.binders;

import com.bichos.handlers.ApiHandler;
import com.bichos.handlers.ApiWSHandler;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.authentication.LoginHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

import io.vertx.ext.auth.jwt.JWTAuth;

public class HandlersModule extends AbstractModule {

  @Override
  protected void configure() {
    final Multibinder<ApiHandler> apiHandlerBinder = Multibinder.newSetBinder(binder(), ApiHandler.class);
    bindApiHandler(apiHandlerBinder, LoginHandler.class);

    /* FIXME final Multibinder<ApiWSHandler> apiWSHandlerBinder = */Multibinder.newSetBinder(binder(), ApiWSHandler.class);
  }

  private void bindApiHandler(final Multibinder<ApiHandler> apiHandlerBinder, final Class<? extends ApiHandler> clazz) {
    binder().bind(clazz).in(Singleton.class);
    apiHandlerBinder.addBinding().to(Key.get(clazz));
  }

  /*
   * FIXME private void bindApiWSHandler(final Multibinder<ApiWSHandler>
   * apiHandlerBinder, final Class<? extends ApiWSHandler> clazz) {
   * binder().bind(clazz).in(Singleton.class);
   * apiHandlerBinder.addBinding().to(Key.get(clazz)); }
   */

  @Provides
  @Singleton
  public AuthenticationHandler provideAuthenticationHandler(final JWTAuth jwtAuth) {
    return new AuthenticationHandler(jwtAuth);
  }

}
