package com.bichos;

import com.bichos.verticles.ApiVerticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Main {

  private Main() {
  }

  public static void main(final String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(ApiVerticle.class.getName(), new DeploymentOptions(), result -> {
      if (result.succeeded()) {
        log.info("Verticle " + ApiVerticle.class.getName() + " deployed.");
      } else {
        log.error("Error deploying verticle " + ApiVerticle.class.getName() + ".", result.cause());
      }
    });
  }

}
