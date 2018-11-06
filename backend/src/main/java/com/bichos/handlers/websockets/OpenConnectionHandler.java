package com.bichos.handlers.websockets;

import com.bichos.services.AuthenticationService;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OpenConnectionHandler implements Handler<BridgeEvent> {

  private static final int BEARER_PART_SIZE = 7;

  private final AuthenticationService authenticationService;

  @Override
  public void handle(final BridgeEvent event) {
    final String token = getToken(event.getRawMessage());
    authenticationService.loginWebsocket(event.socket().writeHandlerID(), token).setHandler(result -> {
      if (result.succeeded()) {
        event.complete(true);
      } else {
        log.debug("Closing socket.");
        event.socket().close();
      }
    });
  }

  private String getToken(final JsonObject rawMessage) {
    final String token = rawMessage.getJsonObject("headers", new JsonObject()).getString("Authorization");

    String result;
    if (token == null || token.length() < BEARER_PART_SIZE) {
      result = token;
    } else {
      result = token.substring(BEARER_PART_SIZE);
    }

    return result;
  }

}
