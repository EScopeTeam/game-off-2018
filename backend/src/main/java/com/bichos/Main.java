package com.bichos;

import java.util.stream.Stream;

import com.bichos.verticles.ApiVerticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Main {

  private static final String ENV_PROPERTY_PROFILE = "profile";

  @SuppressWarnings("unchecked")
  private static final Class<? extends AbstractVerticle>[] VERTICLES = new Class[] {
      ApiVerticle.class
  };

  private Main() {
  }

  public static void main(final String[] args) {
    final Vertx vertx = Vertx.vertx();

    loadConfig(vertx, configResult -> {
      if (configResult.succeeded()) {
        Stream.of(VERTICLES).forEach(clazz -> {
          final DeploymentOptions options = new DeploymentOptions()
              .setConfig(configResult.result());
          vertx.deployVerticle(clazz.getName(), options, result -> {
            if (result.succeeded()) {
              log.info("Verticle " + clazz.getSimpleName() + " deployed.");
            } else {
              log.error("Error deploying verticle " + clazz.getSimpleName() + ".", result.cause());
            }
          });
        });
      } else {
        log.error("Error loading config.", configResult.cause());
      }
    });
  }

  private static void loadConfig(final Vertx vertx, final Handler<AsyncResult<JsonObject>> handler) {
    final ConfigStoreOptions generalStore = new ConfigStoreOptions()
        .setType("file")
        .setConfig(new JsonObject().put("path", getConfigFilePrefix() + "application.json"));
    final ConfigStoreOptions sysPropsStore = new ConfigStoreOptions().setType("sys");

    final ConfigRetrieverOptions options = new ConfigRetrieverOptions()
        .addStore(generalStore)
        .addStore(sysPropsStore);

    final ConfigRetriever retriever = ConfigRetriever.create(vertx, options);
    retriever.getConfig(handler);
  }

  private static String getConfigFilePrefix() {
    final String profile = System.getProperty(ENV_PROPERTY_PROFILE);

    return profile == null ? "" : profile + "-";
  }

}
