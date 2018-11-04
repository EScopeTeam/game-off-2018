package com.bichos;

import java.util.stream.Stream;

import com.bichos.utils.VerticleDeployment;
import com.bichos.verticles.ApiVerticle;
import com.bichos.verticles.TasksVerticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Main {

  private static final String ENV_PROPERTY_PROFILE = "profile";

  private static final String TASKS_WORKER_POOL_NAME = "taks-pool";
  private static final int TASKS_WORKER_SIZE = 5;

  private static final VerticleDeployment[] VERTICLES = new VerticleDeployment[] {
      VerticleDeployment.of(ApiVerticle.class, new DeploymentOptions()),
      VerticleDeployment.of(TasksVerticle.class, new DeploymentOptions()
          .setWorkerPoolName(TASKS_WORKER_POOL_NAME)
          .setWorkerPoolSize(TASKS_WORKER_SIZE)
          .setWorker(true))
  };

  private Main() {
  }

  public static void main(final String[] args) {
    System.setProperty("vertx.logger-delegate-factory-class-name", SLF4JLogDelegateFactory.class.getName());

    final Vertx vertx = Vertx.vertx();

    loadConfig(vertx, configResult -> {
      if (configResult.succeeded()) {
        Stream.of(VERTICLES).forEach(deployment -> {
          final DeploymentOptions options = deployment.getOptions()
              .setConfig(configResult.result());
          final Class<? extends AbstractVerticle> verticle = deployment.getVerticle();

          vertx.deployVerticle(verticle.getName(), options, result -> {
            if (result.succeeded()) {
              log.info("Verticle " + verticle.getSimpleName() + " deployed.");
            } else {
              log.error("Error deploying verticle " + verticle.getSimpleName() + ".", result.cause());
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
