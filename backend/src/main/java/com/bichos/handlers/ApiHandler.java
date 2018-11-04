package com.bichos.handlers;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public interface ApiHandler extends Handler<RoutingContext> {

  int DEFAULT_ORDER = 5;
  int BODY_ORDER = 0;
  int AUTHENTICATION_ORDER = 2;
  int NOT_FOUND_ORDER = 50;

  String getPath();

  HttpMethod getMethod();

  default int getOrder() {
    return DEFAULT_ORDER;
  }

}
