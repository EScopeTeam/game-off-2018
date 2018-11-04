package com.bichos.handlers.websockets;

import com.bichos.services.PlayersService;
import com.bichos.utils.UserUtils;

import io.vertx.core.Handler;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OpenConnectionHandler implements Handler<BridgeEvent> {

  private final PlayersService playersService;

  @Override
  public void handle(final BridgeEvent event) {
    final User user = event.socket().webUser();
    final String userId = UserUtils.getUserId(user);

    log.debug("User " + userId + " connected.");

    playersService
        .markAsOnline(userId)
        .setHandler(v -> event.complete());
  }

}
