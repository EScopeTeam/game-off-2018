package com.bichos;

import java.util.stream.Stream;

import com.bichos.verticles.ApiVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Main {

  @SuppressWarnings("unchecked")
  private static final Class<? extends AbstractVerticle>[] VERTICLES = new Class[] {
      ApiVerticle.class
  };

  private Main() {
  }

  public static void main(final String[] args) {
    final Vertx vertx = Vertx.vertx();
    Stream.of(VERTICLES).forEach(clazz -> {
      vertx.deployVerticle(clazz.getName(), new DeploymentOptions(), result -> {
        if (result.succeeded()) {
          log.info("Verticle " + clazz.getSimpleName() + " deployed.");
        } else {
          log.error("Error deploying verticle " + clazz.getSimpleName() + ".", result.cause());
        }
      });
    });
  }

}
