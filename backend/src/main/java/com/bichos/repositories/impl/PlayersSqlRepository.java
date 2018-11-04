package com.bichos.repositories.impl;

import com.bichos.models.Player;
import com.bichos.repositories.PlayersRepository;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayersSqlRepository implements PlayersRepository {

  private static final String FIND_PLAYER_BY_USERNAME_SQL = "SELECT player_id, username, password, salt, email FROM players WHERE username = ?";
  private static final int INDEX_ID = 0;
  private static final int INDEX_USERNAME = 1;
  private static final int INDEX_PASSWORD = 2;
  private static final int INDEX_SALT = 3;
  private static final int INDEX_EMAIL = 4;

  private final SQLClient client;

  @Override
  public Future<Player> findPlayerByUsername(final String username) {
    final Future<JsonArray> fQuery = Future.future();
    final JsonArray params = new JsonArray().add(username);
    client.querySingleWithParams(FIND_PLAYER_BY_USERNAME_SQL, params, fQuery.completer());

    return fQuery.map(this::mapRow);
  }

  private Player mapRow(final JsonArray row) {
    Player result;

    if (row == null) {
      result = null;
    } else {
      result = new Player();
      result.setId(String.valueOf(row.getLong(INDEX_ID)));
      result.setUsername(row.getString(INDEX_USERNAME));
      result.setPassword(row.getString(INDEX_PASSWORD));
      result.setSalt(row.getString(INDEX_SALT));
      result.setEmail(row.getString(INDEX_EMAIL));
    }

    return result;
  }

}
