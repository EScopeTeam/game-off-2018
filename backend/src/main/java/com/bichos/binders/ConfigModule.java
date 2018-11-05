package com.bichos.binders;

import java.time.Clock;

import javax.validation.Validation;
import javax.validation.Validator;

import com.bichos.config.AuthenticationConfig;
import com.bichos.config.SqlConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigModule extends AbstractModule {

  private final Vertx vertx;

  private final JsonObject config;

  @Override
  protected void configure() {
    bind(Vertx.class).toInstance(vertx);
    bind(JsonObject.class).annotatedWith(Names.named("config")).toInstance(config);
  }

  @Provides
  @Singleton
  public AuthenticationConfig provideAuthenticationConfig() {
    return new AuthenticationConfig(config);
  }

  @Provides
  @Singleton
  public JWTAuth provideJWTAuth(final AuthenticationConfig authenticationConfig) {
    return JWTAuth.create(vertx, authenticationConfig.getJWTAuthOptions());
  }

  @Provides
  @Singleton
  public SqlConfig provideSqlConfig() {
    return new SqlConfig(config);
  }

  @Provides
  @Singleton
  public SQLClient provideSqlClient(final SqlConfig sqlConfig) {
    return PostgreSQLClient.createShared(vertx, sqlConfig.getSQLClientConfig());
  }

  @Provides
  @Singleton
  public Validator provideValidator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Provides
  @Singleton
  public Clock provideClock() {
    return Clock.systemUTC();
  }

}
