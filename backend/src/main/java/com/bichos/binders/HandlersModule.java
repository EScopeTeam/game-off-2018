package com.bichos.binders;

import com.bichos.exceptions.ResourceNotFoundException;
import com.bichos.exceptions.ValidationException;
import com.bichos.handlers.ApiErrorHandler;
import com.bichos.handlers.ApiHandler;
import com.bichos.handlers.ApiWSHandler;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.authentication.LoginHandler;
import com.bichos.handlers.authentication.WsAuthenticationHandler;
import com.bichos.handlers.errors.DecodeExceptionHandler;
import com.bichos.handlers.errors.ResourceNotFoundHandler;
import com.bichos.handlers.errors.ValidationErrorHandler;
import com.bichos.handlers.players.HelloHandler;
import com.bichos.handlers.websockets.CloseConnectionHandler;
import com.bichos.services.AuthenticationService;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

import io.vertx.core.json.DecodeException;
import io.vertx.ext.auth.jwt.JWTAuth;

public class HandlersModule extends AbstractModule {

  @Override
  protected void configure() {
    final Multibinder<ApiHandler> apiHandlerBinder = Multibinder.newSetBinder(binder(), ApiHandler.class);
    bindApiHandler(apiHandlerBinder, LoginHandler.class);

    final Multibinder<ApiWSHandler> apiWSHandlerBinder = Multibinder.newSetBinder(binder(), ApiWSHandler.class);
    bindApiWSHandler(apiWSHandlerBinder, HelloHandler.class);

    @SuppressWarnings("rawtypes")
    final MapBinder<Class, ApiErrorHandler> apiErrorHandlerBinder = MapBinder.newMapBinder(binder(), Class.class,
        ApiErrorHandler.class);
    bindApiErrorHandler(apiErrorHandlerBinder, ValidationException.class, ValidationErrorHandler.class);
    bindApiErrorHandler(apiErrorHandlerBinder, DecodeException.class, DecodeExceptionHandler.class);
    bindApiErrorHandler(apiErrorHandlerBinder, ResourceNotFoundException.class, ResourceNotFoundHandler.class);
  }

  private void bindApiHandler(final Multibinder<ApiHandler> apiHandlerBinder, final Class<? extends ApiHandler> clazz) {
    binder().bind(clazz).in(Singleton.class);
    apiHandlerBinder.addBinding().to(Key.get(clazz));
  }

  private void bindApiWSHandler(final Multibinder<ApiWSHandler> apiHandlerBinder, final Class<? extends ApiWSHandler> clazz) {
    binder().bind(clazz).in(Singleton.class);
    apiHandlerBinder.addBinding().to(Key.get(clazz));
  }

  @SuppressWarnings("rawtypes")
  private <T extends Throwable> void bindApiErrorHandler(final MapBinder<Class, ApiErrorHandler> apiErrorHandlerBinder, final Class<T> exceptionClass,
      final Class<? extends ApiErrorHandler> handlerClass) {
    binder().bind(handlerClass).in(Singleton.class);
    apiErrorHandlerBinder.addBinding(exceptionClass).to(Key.get(handlerClass));
  }

  @Provides
  @Singleton
  public AuthenticationHandler provideAuthenticationHandler(final JWTAuth jwtAuth) {
    return new AuthenticationHandler(jwtAuth);
  }

  @Provides
  @Singleton
  public WsAuthenticationHandler provideWsAuthenticationHandler(final AuthenticationService authenticationService) {
    return new WsAuthenticationHandler(authenticationService);
  }

  @Provides
  @Singleton
  public CloseConnectionHandler provideCloseConnectionHandler(final AuthenticationService authenticationService) {
    return new CloseConnectionHandler(authenticationService);
  }

}
