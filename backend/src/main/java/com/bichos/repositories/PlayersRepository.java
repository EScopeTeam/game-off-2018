package com.bichos.repositories;

import com.bichos.models.Player;

import io.vertx.core.Future;

public interface PlayersRepository {

  Future<Player> findPlayerByUsername(String username);

  Future<Void> updateOnlineById(String playerId, boolean online);

  Future<Void> insertPlayer(Player player);

  Future<Boolean> existsPlayerbyUsernameOrEmail(String username, String email);

}
