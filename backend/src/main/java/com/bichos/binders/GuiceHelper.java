package com.bichos.binders;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public final class GuiceHelper {

  private GuiceHelper() {
  }

  public static Injector initialize(final Vertx vertx, final JsonObject config) {
    return Guice.createInjector(
        new ConfigModule(vertx, config),
        new RepositoriesModule(),
        new ServicesModule(),
        new HandlersModule(),
        new TasksModule());
  }

}
