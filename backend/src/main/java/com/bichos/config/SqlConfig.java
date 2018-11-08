package com.bichos.config;

import io.vertx.core.json.JsonObject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SqlConfig {

  private static final String KEY_DB = "db";
  private static final String KEY_HOST = "host";
  private static final String HOST_DEFAULT_VALUE = "localhost";
  private static final String KEY_PORT = "port";
  private static final int PORT_DEFAULT_VALUE = 5432;
  private static final String KEY_USERNAME = "username";
  private static final String KEY_PASSWORD = "password";
  private static final String KEY_DATABASE = "database";
  private static final String DATABASE_DEFAULT_VALUE = "bichos";

  private final JsonObject config;

  public JsonObject getSQLClientConfig() {
    final JsonObject dbConfig = config.getJsonObject(KEY_DB, new JsonObject());

    return new JsonObject()
        .put("host", dbConfig.getString(KEY_HOST, HOST_DEFAULT_VALUE))
        .put("port", dbConfig.getInteger(KEY_PORT, PORT_DEFAULT_VALUE))
        .put("username", dbConfig.getString(KEY_USERNAME))
        .put("password", dbConfig.getString(KEY_PASSWORD))
        .put("database", dbConfig.getString(KEY_DATABASE, DATABASE_DEFAULT_VALUE));
  }

}
