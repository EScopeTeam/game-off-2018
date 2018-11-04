package com.bichos.utils;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public final class HandlerUtils {

  private static final String HEADER_CONTENT_TYPE_JSON_VALUE = "application/json; charset=utf-8";

  private HandlerUtils() {
  }

  public static <T> void jsonResponse(final RoutingContext context, final T objectResponse) {
    context
        .response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HEADER_CONTENT_TYPE_JSON_VALUE)
        .end(JsonObject.mapFrom(objectResponse).encode());
  }

  public static <T> T jsonRequest(final RoutingContext context, final Class<T> clazz) {
    return context.getBodyAsJson().mapTo(clazz);
  }

}
