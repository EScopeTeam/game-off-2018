package com.bichos.handlers.players;

import com.bichos.handlers.ApiWSHandler;
import com.bichos.mappers.PlayerMapper;
import com.bichos.services.AuthenticationService;
import com.bichos.utils.HttpStatus;
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
    authenticationService.findPlayerById(playerId).setHandler(result -> {
      if (result.succeeded()) {
        if (result.result() == null) {
          message.fail(HttpStatus.NOT_FOUND.getStatusCode(), HttpStatus.NOT_FOUND.getStatusMessage());
        } else {
          message.reply(PlayerMapper.mapPlayerToResponse(result.result()));
        }
      } else {
        message.fail(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode(), result.cause().getMessage());
      }
    });
  }

  @Override
  public String getAddress() {
    return "hello";
  }

}
