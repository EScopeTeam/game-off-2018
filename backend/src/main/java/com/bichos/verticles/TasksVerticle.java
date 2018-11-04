package com.bichos.verticles;

import com.bichos.binders.GuiceInitializer;
import com.google.inject.Injector;

import io.vertx.core.AbstractVerticle;

public class TasksVerticle extends AbstractVerticle {

  private Injector injector;

  @Override
  public void start() {
    initializeGuice();

  }

  private void initializeGuice() {
    injector = GuiceInitializer.initialize(vertx, config());
  }

}
