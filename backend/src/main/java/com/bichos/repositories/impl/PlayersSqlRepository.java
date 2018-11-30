package com.bichos.repositories.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.bichos.models.Player;
import com.bichos.repositories.PlayersRepository;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.UpdateResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayersSqlRepository implements PlayersRepository {

  private static final int INDEX_ID = 0;
  private static final int INDEX_USERNAME = 1;
  private static final int INDEX_PASSWORD = 2;
  private static final int INDEX_SALT = 3;
  private static final int INDEX_EMAIL = 4;
  private static final int INDEX_ONLINE = 5;
  private static final int INDEX_ACTIVE = 6;
  private static final int INDEX_CREATION_DATE = 7;
  private static final int INDEX_UPDATE_DATE = 8;
  private static final int INDEX_COINS = 9;
  private static final int INDEX_EXPERIENCE_POINTS = 10;

  private static final String ATTRIBUTES = "player_id, username, password, salt, email, online, active, creation_time, update_time, coins, experience_points";

  private static final String FIND_PLAYER_BY_USERNAME_SQL = "SELECT " + ATTRIBUTES + " FROM players WHERE username = ?";

  private static final String UPDATE_ONLINE_BY_ID_SQL = "UPDATE players SET online = ? WHERE player_id = ?";

  private static final String INSERT_PLAYER_SQL = "INSERT INTO players (email, password, salt, active, username, creation_time, update_time, "
      + "coins, experience_points, online) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String COUNT_PLAYER_BY_UNAME_OR_EMAIL_SQL = "SELECT count(*) FROM players WHERE username = ? OR email = ?";

  private static final String FIND_PLAYER_BY_PLAYER_ID_SQL = "SELECT " + ATTRIBUTES + " FROM players WHERE player_id = ?";

  private static final DateTimeFormatter POSTGRE_TIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  private static final boolean START_ACTIVE_ACCOUNT = true;
  private static final double START_PLAYER_COINS = 0.0;
  private static final int START_EXPERIENCE_POINTS = 0;
  private static final boolean START_PLAYER_ONLINE = false;

  private final SQLClient client;

  private final Clock clock;

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
      result.setUserId(String.valueOf(row.getLong(INDEX_ID)));
      result.setEmail(row.getString(INDEX_EMAIL));
      result.setPassword(row.getString(INDEX_PASSWORD));
      result.setSalt(row.getString(INDEX_SALT));
      result.setActive(row.getBoolean(INDEX_ACTIVE));
      result.setCreationTime(OffsetDateTime.parse(row.getString(INDEX_CREATION_DATE), POSTGRE_TIME_FORMATTER));
      result.setUpdateTime(OffsetDateTime.parse(row.getString(INDEX_UPDATE_DATE), POSTGRE_TIME_FORMATTER));
      result.setUsername(row.getString(INDEX_USERNAME));
      result.setCoins(BigDecimal.valueOf(row.getDouble(INDEX_COINS)));
      result.setExperiencePoints(BigInteger.valueOf(row.getLong(INDEX_EXPERIENCE_POINTS)));
      result.setOnline(row.getBoolean(INDEX_ONLINE));
    }

    return result;
  }

  @Override
  public Future<Void> updateOnlineById(final String playerId, final boolean online) {
    final JsonArray params = new JsonArray().add(online).add(playerId);

    final Future<UpdateResult> fUpdate = Future.succeededFuture();
    client.updateWithParams(UPDATE_ONLINE_BY_ID_SQL, params, fUpdate.completer());

    return fUpdate.mapEmpty();
  }

  @Override
  public Future<Void> insertPlayer(final Player player) {
    final Future<UpdateResult> fResult = Future.future();
    final JsonArray params = new JsonArray()
        .add(player.getEmail())
        .add(player.getPassword())
        .add(player.getSalt())
        .add(START_ACTIVE_ACCOUNT)
        .add(player.getUsername())
        .add(OffsetDateTime.now(clock).format(POSTGRE_TIME_FORMATTER))
        .add(OffsetDateTime.now(clock).format(POSTGRE_TIME_FORMATTER))
        .add(START_PLAYER_COINS)
        .add(START_EXPERIENCE_POINTS)
        .add(START_PLAYER_ONLINE);
    client.updateWithParams(INSERT_PLAYER_SQL, params, fResult.completer());

    return fResult.mapEmpty();
  }

  @Override
  public Future<Boolean> existsPlayerbyUsernameOrEmail(final String username, final String email) {
    final Future<JsonArray> fCount = Future.future();
    final JsonArray params = new JsonArray().add(username).add(email);
    client.querySingleWithParams(COUNT_PLAYER_BY_UNAME_OR_EMAIL_SQL, params, fCount.completer());
    return fCount.map(arr -> arr.getLong(0) == 1);
  }

  @Override
  public Future<Player> findPlayerById(final String playerId) {
    final Future<JsonArray> fQuery = Future.future();
    final JsonArray params = new JsonArray().add(playerId);
    client.querySingleWithParams(FIND_PLAYER_BY_PLAYER_ID_SQL, params, fQuery.completer());

    return fQuery.map(this::mapRow);
  }

}
