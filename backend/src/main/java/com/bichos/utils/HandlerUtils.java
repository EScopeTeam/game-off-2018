package com.bichos.utils;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.RequestParameters;

public final class HandlerUtils {

  private static final String HEADER_CONTENT_TYPE_JSON_VALUE = "application/json";
  private static final String KEY_PARSED_PARAMS = "parsedParameters";

  private HandlerUtils() {
  }

  public static <T> void jsonResponse(final RoutingContext context, final T objectResponse) {
    context
        .response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HEADER_CONTENT_TYPE_JSON_VALUE)
        .end(JsonObject.mapFrom(objectResponse).encode());
  }

  public static <T> T jsonRequest(final RoutingContext context, final Class<T> clazz) {
    final RequestParameters params = context.get(KEY_PARSED_PARAMS);

    return params.body().getJsonObject().mapTo(clazz);
  }

}
