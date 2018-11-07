package com.bichos.handlers.players;

import com.bichos.handlers.ApiWSHandler;
import com.bichos.services.AuthenticationService;
import com.google.inject.Inject;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HelloHandler implements ApiWSHandler {

  private final AuthenticationService authenticationService;

  @Override
  public void handle(final Message<JsonObject> message) {
    final String sessionId = message.headers().get("session");
    final String playerId = message.headers().get("user");

    authenticationService.addWebsocketSession(sessionId, playerId);
  }

  @Override
  public String getAddress() {
    return "hello";
  }

}
