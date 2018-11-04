package com.bichos.repositories;

import com.bichos.models.Player;

import io.vertx.core.Future;

public interface PlayersRepository {

  Future<Player> findPlayerByUsername(String username);

}
