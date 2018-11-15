package com.bichos.handlers.bugs;

import com.bichos.handlers.ApiHandler;
import com.bichos.services.BugsGeneratorService;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugsGeneratorHandler implements ApiHandler {

  private final BugsGeneratorService bugsGeneratorService;

  @Override
  public void handle(RoutingContext context) {
    bugsGeneratorService.generate().setHandler(result -> {
      if (result.succeeded()) {
        context.response().end(result.result().hash());
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Override
  public String getPath() {
    return "/bugs/generate";
  }

  @Override
  public HttpMethod getMethod() {
    return HttpMethod.POST;
  }

  @Override
  public boolean hasAuthentication() {
    return false;
  }

}
