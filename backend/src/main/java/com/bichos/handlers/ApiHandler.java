package com.bichos.handlers;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public interface ApiHandler extends Handler<RoutingContext> {

  String getPath();

  HttpMethod getMethod();

  boolean hasAuthentication();

}
