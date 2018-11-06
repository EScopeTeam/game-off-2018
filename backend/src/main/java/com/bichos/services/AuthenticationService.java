package com.bichos.services;

import io.vertx.core.Future;

public interface AuthenticationService {

  Future<String> login(String username, String password);

  Future<Void> loginWebsocket(String sessionId, String token);

  Future<Void> logoutWebsocket(String sessionId);

}
