package com.bichos.handlers;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.PermittedOptions;

public interface ApiWSHandler extends Handler<Message<JsonObject>> {

  String getAddress();

  static PermittedOptions createPermittedOptionsFromAddress(String address) {
    return new PermittedOptions().setAddress(address);
  }

}
