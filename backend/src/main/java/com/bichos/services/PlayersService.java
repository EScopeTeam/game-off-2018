package com.bichos.services;

import io.vertx.core.Future;

public interface PlayersService {

  Future<Void> markAsOnline(String playerId);

  Future<Void> markAsOffline(String playerId);

}
