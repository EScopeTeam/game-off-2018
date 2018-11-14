package com.bichos.repositories.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.bichos.models.Player;
import com.bichos.models.UserAccount;
import com.bichos.repositories.PlayersRepository;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.UpdateResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayersSqlRepository implements PlayersRepository {

  private static final int INDEX_ID = 0;
  private static final int INDEX_EMAIL = 1;
  private static final int INDEX_PASSWORD = 2;
  private static final int INDEX_SALT = 3;
  private static final int INDEX_ACTIVE = 4;
  private static final int INDEX_USERNAME = 5;
  private static final int INDEX_CREATION_DATE = 6;
  private static final int INDEX_UPDATE_DATE = 7;
  private static final int INDEX_COINS = 8;
  private static final int INDEX_EXPERIENCE_POINTS = 9;
  private static final int INDEX_ONLINE = 10;

  private static final String FIND_PLAYER_BY_USERNAME_SQL =
      "SELECT player_id, email, password, salt, active, creation_time, update_time, username, coins, experiencePoints, online FROM players WHERE username = ?";

  private static final String UPDATE_ONLINE_BY_ID_SQL = "UPDATE players SET online = ? WHERE player_id = ?";

  private static final String INSERT_PLAYER_SQL =
      "INSERT INTO players (email, password, salt, active, creation_time, update_time, username, coins, experiencePoints, online) "
          + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String COUNT_PLAYER_BY_USERNAME_OR_EMAIL_SQL = "SELECT count(*) FROM players WHERE username = ? OR email = ?";

  private static final DateTimeFormatter POSTGRE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");

  private static final boolean START_ACTIVE_ACCOUNT = true;
  private static final BigDecimal START_PLAYER_COINS = BigDecimal.ZERO;
  private static final BigDecimal START_EXPERIENCE_POINTS = BigDecimal.ZERO;

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
      final UserAccount userAccount = new UserAccount();
      userAccount.setEmail(row.getString(INDEX_EMAIL));
      userAccount.setPassword(row.getString(INDEX_PASSWORD));
      userAccount.setSalt(row.getString(INDEX_SALT));
      userAccount.setActive(row.getBoolean(INDEX_ACTIVE));
      userAccount.setCreationTime(OffsetDateTime.ofInstant(Instant.ofEpochMilli(row.getLong(INDEX_CREATION_DATE)), ZoneId.systemDefault()));
      userAccount.setUpdateTime(OffsetDateTime.ofInstant(Instant.ofEpochMilli(row.getLong(INDEX_UPDATE_DATE)), ZoneId.systemDefault()));

      result.setId(String.valueOf(row.getLong(INDEX_ID)));
      result.setUserAccount(userAccount);
      result.setUsername(row.getString(INDEX_USERNAME));
      result.setCoins(BigDecimal.valueOf(row.getFloat(INDEX_COINS)));
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
    client.updateWithParams(INSERT_PLAYER_SQL, new JsonArray()
        .add(player.getUserAccount().getEmail()).add(player.getUserAccount().getPassword())
        .add(player.getUserAccount().getSalt()).add(START_ACTIVE_ACCOUNT)
        .add(OffsetDateTime.now().format(POSTGRE_TIME_FORMATTER))
        .add(OffsetDateTime.now().format(POSTGRE_TIME_FORMATTER))
        .add(player.getUsername()).add(START_PLAYER_COINS).add(START_EXPERIENCE_POINTS)
        .add(player.isOnline()), fResult.completer());

    return fResult.mapEmpty();
  }

  @Override
  public Future<Boolean> existsPlayerbyUsernameOrEmail(final String username, final String email) {
    final Future<JsonArray> fCount = Future.future();
    final JsonArray params = new JsonArray().add(username).add(email);
    client.querySingleWithParams(COUNT_PLAYER_BY_USERNAME_OR_EMAIL_SQL, params, fCount.completer());
    return fCount.map(arr -> arr.getLong(0) == 1);
  }

}
