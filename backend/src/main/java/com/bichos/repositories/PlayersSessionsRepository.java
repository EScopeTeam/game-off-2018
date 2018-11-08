package com.bichos.repositories;

import com.bichos.models.PlayerSession;

import io.vertx.core.Future;

public interface PlayersSessionsRepository {

  Future<Long> countSessions(String playerId);

  Future<Void> removeSession(String sessionId);

  Future<Void> insertSession(PlayerSession session);

  Future<PlayerSession> findSession(String sessionId);

}
