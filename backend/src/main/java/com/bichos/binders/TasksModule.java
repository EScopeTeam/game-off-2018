package com.bichos.binders;

import com.bichos.tasks.WorkerTask;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class TasksModule extends AbstractModule {

  @Override
  protected void configure() {
    final Multibinder<WorkerTask> workerTasksBinder = Multibinder.newSetBinder(binder(), WorkerTask.class);
  }

}
