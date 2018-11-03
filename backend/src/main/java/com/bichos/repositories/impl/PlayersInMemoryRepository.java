package com.bichos.repositories.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bichos.models.Player;
import com.bichos.repositories.PlayersRepository;

import io.vertx.core.Future;

public class PlayersInMemoryRepository implements PlayersRepository {

  private static final Map<String, Player> PLAYERS = new ConcurrentHashMap<>();

  public PlayersInMemoryRepository() {
    final Player player1 = new Player();
    player1.setId("1");
    player1.setPassword("DD2C00FC4DB949DC0CF2FF4742CB502B53EC91C73DE24C3AA50F26812691FD4C89531DEC369B81BC8764A61FA4A527332944D84272AEB012A35496E1ED723855");
    player1.setSalt("abcd");
    player1.setUsername("test");
    PLAYERS.put("test", player1);
  }

  @Override
  public Future<Player> findPlayer(final String username) {
    return Future.succeededFuture(PLAYERS.get(username));
  }

}
