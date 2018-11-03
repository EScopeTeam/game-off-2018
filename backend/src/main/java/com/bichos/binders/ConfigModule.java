package com.bichos.binders;

import com.bichos.config.AuthenticationConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
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
	
}
