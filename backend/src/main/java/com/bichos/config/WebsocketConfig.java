package com.bichos.config;

import java.util.Set;

import com.bichos.handlers.ApiWSHandler;
import com.bichos.handlers.authentication.WsAuthenticationHandler;
import com.bichos.handlers.websockets.CloseConnectionHandler;
import com.google.inject.Inject;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class WebsocketConfig {

  private static final String AUTHORIZATION_ADDRESS = "authorization";

  private final Vertx vertx;

  private final Set<ApiWSHandler> handlers;

  private final WsAuthenticationHandler wsAuthentactionHandler;

  private final CloseConnectionHandler closeConnectionHandler;

  public SockJSHandler getSockJSHandler() {
    final BridgeOptions options = new BridgeOptions();

    for (final ApiWSHandler handler : handlers) {
      final String address = handler.getAddress();
      options.addInboundPermitted(ApiWSHandler.createPermittedOptionsFromAddress(address));
      vertx.eventBus().<JsonObject>consumer(address).handler(handler);
    }

    options.addInboundPermitted(ApiWSHandler.createPermittedOptionsFromAddress(AUTHORIZATION_ADDRESS));

    final SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
    sockJSHandler.bridge(options, event -> {
      if (event.type() == BridgeEventType.SOCKET_CLOSED) {
        closeConnectionHandler.handle(event);
      } else if (event.type() == BridgeEventType.SEND) {
        wsAuthentactionHandler.handle(event);
      } else {
        event.complete(true);
      }
    });

    return sockJSHandler;
  }

}
