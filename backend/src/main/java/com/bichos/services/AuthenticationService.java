package com.bichos.services;

import io.vertx.core.Future;

public interface AuthenticationService {

  Future<String> login(String username, String password);

  Future<String> authenticateToken(String token);

  Future<Void> addWebsocketSession(String sessionId, String playerId);

  Future<Void> removeWebsocketSession(String sessionId);

}
