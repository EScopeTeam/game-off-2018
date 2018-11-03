package com.bichos.binders;

import com.bichos.config.AuthenticationConfig;
import com.bichos.repositories.PlayersRepository;
import com.bichos.services.AuthenticationService;
import com.bichos.services.impl.AuthenticationJWTService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import io.vertx.core.Vertx;
import io.vertx.ext.auth.jdbc.JDBCHashStrategy;
import io.vertx.ext.auth.jwt.JWTAuth;

public class ServicesModule extends AbstractModule {

	@Provides
	@Singleton
	public AuthenticationService provideAuthenticationService(final Vertx vertx, final JWTAuth jwtAuth, final AuthenticationConfig authConfig, final PlayersRepository playersRepository) {
		return new AuthenticationJWTService(jwtAuth, authConfig.getJWTOptions(), JDBCHashStrategy.createSHA512(vertx), playersRepository);
	}
	
}
