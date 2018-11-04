package com.bichos.tasks;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public interface WorkerTask {

  void run(Vertx vertx, JsonObject config);

}
