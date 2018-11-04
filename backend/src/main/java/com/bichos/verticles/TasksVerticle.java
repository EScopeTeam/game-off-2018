package com.bichos.verticles;

import java.util.Set;

import com.bichos.binders.GuiceInitializer;
import com.bichos.tasks.WorkerTask;
import com.google.inject.Injector;
import com.google.inject.Key;

import io.vertx.core.AbstractVerticle;

public class TasksVerticle extends AbstractVerticle {

  private Injector injector;

  @Override
  public void start() {
    initializeGuice();

    runTasks();
  }

  private void initializeGuice() {
    injector = GuiceInitializer.initialize(vertx, config());
  }

  private void runTasks() {
    for (final WorkerTask task : getWorkerTasks()) {
      task.run(vertx, config());
    }
  }

  private Set<WorkerTask> getWorkerTasks() {
    return injector.getInstance(new Key<Set<WorkerTask>>() {
    });
  }

}
