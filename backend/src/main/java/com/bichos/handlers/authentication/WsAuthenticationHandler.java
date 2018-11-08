package com.bichos.handlers.authentication;

import com.bichos.services.AuthenticationService;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class WsAuthenticationHandler implements Handler<BridgeEvent> {

  private final AuthenticationService authenticationService;

  private static final int BEARER_PART_SIZE = 7;

  @Override
  public void handle(final BridgeEvent event) {
    cleanUserHeader(event);

    final String token = getToken(event.getRawMessage());
    authenticationService.authenticateToken(token).setHandler(result -> {
      if (result.succeeded()) {
        final String playerId = result.result();
        event.getRawMessage()
            .getJsonObject("headers", new JsonObject())
            .put("user", playerId)
            .put("session", event.socket().writeHandlerID());

        event.complete(true);
      } else {
        log.debug("Closing socket.");
        event.socket().close();

        event.complete(false);
      }
    });
  }

  private void cleanUserHeader(final BridgeEvent event) {
    event.getRawMessage().getJsonObject("headers", new JsonObject()).remove("user");
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
