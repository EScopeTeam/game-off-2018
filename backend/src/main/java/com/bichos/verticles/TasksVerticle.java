package com.bichos.verticles;

import java.util.Set;

import com.bichos.binders.GuiceHelper;
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
    injector = GuiceHelper.initialize(vertx, config());
  }

  private void runTasks() {
    for (final WorkerTask task : getWorkerTasks()) {
      task.run();
    }
  }

  private Set<WorkerTask> getWorkerTasks() {
    return injector.getInstance(new Key<Set<WorkerTask>>() {
    });
  }

}
