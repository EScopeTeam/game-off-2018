package com.bichos.binders;

import javax.validation.Validator;

import com.bichos.config.AuthenticationConfig;
import com.bichos.repositories.PlayersRepository;
import com.bichos.services.AuthenticationService;
import com.bichos.services.PlayersService;
import com.bichos.services.ValidationService;
import com.bichos.services.impl.AuthenticationJWTService;
import com.bichos.services.impl.JavaxValidationService;
import com.bichos.services.impl.PlayersServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import io.vertx.core.Vertx;
import io.vertx.ext.auth.jdbc.JDBCHashStrategy;
import io.vertx.ext.auth.jwt.JWTAuth;

public class ServicesModule extends AbstractModule {

  @Provides
  @Singleton
  public AuthenticationService provideAuthenticationService(final Vertx vertx, final JWTAuth jwtAuth, final AuthenticationConfig authConfig,
      final PlayersRepository playersRepository) {
    return new AuthenticationJWTService(jwtAuth, authConfig.getJWTOptions(), JDBCHashStrategy.createSHA512(vertx), playersRepository);
  }

  @Provides
  @Singleton
  public ValidationService provideValidationService(final Validator validator) {
    return new JavaxValidationService(validator);
  }

  @Provides
  @Singleton
  public PlayersService providePlayersService(final PlayersRepository playersRepository) {
    return new PlayersServiceImpl(playersRepository);
  }

}
