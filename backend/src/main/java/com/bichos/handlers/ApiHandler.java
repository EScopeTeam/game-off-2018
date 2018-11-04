package com.bichos.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public interface ApiHandler extends Handler<RoutingContext> {

  String getOperationId();

}
