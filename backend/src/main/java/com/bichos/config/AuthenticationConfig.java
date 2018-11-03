package com.bichos.config;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;

public class AuthenticationConfig {
	
	private static final String KEY_JWT = "jwt_auth";
	private static final String KEY_KEYSTORE_PATH = "keystore_path";
	private static final String KEYSTORE_PATH_DEFAULT_VALUE = "keystore.jceks";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_PASSWORD_DEFAULT = "pvb72bqtMhwW4KNJ";
	private static final String KEY_EXPIRES_IN_MINUTES = "expires_in_minutes";
	private static final int EXPIRES_IN_MINUTES_DEFAULT_VALUE = 60 * 24 * 7;
	private static final String KEY_ISSUER = "issuer";
	private static final String ISSUER_DEFAULT_VALUE = "BICHOS";
	private static final String KEY_ALGORITHM = "algorithm";
	private static final String ALGORITHM_DEFAULT_VALUE = "HS512";
	
	private final JsonObject config;
	
	public AuthenticationConfig(final JsonObject config) {		
		this.config = config;
	}
	
	public JWTAuthOptions getJWTAuthOptions() {
		JsonObject jwtConfig = config.getJsonObject(KEY_JWT, new JsonObject());
		
		return new JWTAuthOptions()
				.setKeyStore(new KeyStoreOptions()
				.setPath(jwtConfig.getString(KEY_KEYSTORE_PATH, KEYSTORE_PATH_DEFAULT_VALUE))
				.setPassword(jwtConfig.getString(KEY_PASSWORD, KEY_PASSWORD_DEFAULT)))
				.setJWTOptions(getJWTOptions());
	}
	
	public JWTOptions getJWTOptions() {
		JsonObject jwtConfig = config.getJsonObject(KEY_JWT, new JsonObject());
		
		return new JWTOptions()
				.setAlgorithm(jwtConfig.getString(KEY_ALGORITHM, ALGORITHM_DEFAULT_VALUE))
				.setExpiresInMinutes(jwtConfig.getInteger(KEY_EXPIRES_IN_MINUTES, EXPIRES_IN_MINUTES_DEFAULT_VALUE))
				.setIssuer(jwtConfig.getString(KEY_ISSUER, ISSUER_DEFAULT_VALUE));
	}
	
}
