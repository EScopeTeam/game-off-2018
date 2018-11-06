package com.bichos.handlers.websockets;

import com.bichos.services.AuthenticationService;

import io.vertx.core.Handler;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CloseConnectionHandler implements Handler<BridgeEvent> {

  private final AuthenticationService authenticationService;

  @Override
  public void handle(final BridgeEvent event) {
    final String sessionId = event.socket().writeHandlerID();

    log.debug("Session " + sessionId + " disconnected.");

    authenticationService
        .logoutWebsocket(sessionId)
        .setHandler(v -> event.complete(true));
  }

}
