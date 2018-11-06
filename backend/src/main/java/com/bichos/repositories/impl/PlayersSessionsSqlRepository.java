package com.bichos.repositories.impl;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.bichos.models.PlayerSession;
import com.bichos.repositories.PlayersSessionsRepository;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.UpdateResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayersSessionsSqlRepository implements PlayersSessionsRepository {

  private static final String COUNT_NUM_SESSION_SQL = "SELECT count(*) FROM players_sessions WHERE player_id = ?";
  private static final String REMOVE_SESSION_SQL = "DELETE FROM players_sessions WHERE session_id = ?";
  private static final String INSERT_SESSION_SQL = "INSERT INTO players_sessions (session_id, player_id, start_date) VALUES (?, ?, ?)";
  private static final String FIND_ONE_BY_SESSION_ID_SQL = "SELECT session_id, player_id, start_date FROM players_sessions WHERE session_id = ?";

  private static final int INDEX_SESSION_ID = 0;
  private static final int INDEX_PLAYER_ID = 1;
  private static final int INDEX_START_DATE = 2;

  private static final DateTimeFormatter POSGRESS_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");

  private final SQLClient client;

  @Override
  public Future<Long> countSessions(final String playerId) {
    final Future<JsonArray> fCount = Future.future();
    client.querySingleWithParams(COUNT_NUM_SESSION_SQL, new JsonArray().add(Long.valueOf(playerId)), fCount.completer());
    return fCount.map(arr -> arr.getLong(0));
  }

  @Override
  public Future<Void> removeSession(final String sessionId) {
    final Future<UpdateResult> fResult = Future.future();
    client.updateWithParams(REMOVE_SESSION_SQL, new JsonArray().add(sessionId), fResult.completer());
    return fResult.mapEmpty();
  }

  @Override
  public Future<Void> insertSession(final PlayerSession session) {
    final Future<UpdateResult> fResult = Future.future();
    client.updateWithParams(INSERT_SESSION_SQL, new JsonArray()
        .add(session.getSessionId())
        .add(Long.valueOf(session.getPlayerId()))
        .add(session.getStartDate().format(POSGRESS_TIME_FORMATTER)), fResult.completer());
    return fResult.mapEmpty();
  }

  @Override
  public Future<PlayerSession> findSession(final String sessionId) {
    final Future<JsonArray> fResult = Future.future();
    client.querySingleWithParams(FIND_ONE_BY_SESSION_ID_SQL, new JsonArray().add(sessionId), fResult.completer());
    return fResult.map(this::mapRow);
  }

  private PlayerSession mapRow(final JsonArray row) {
    PlayerSession result;
    if (row == null) {
      result = null;
    } else {
      result = new PlayerSession();
      result.setSessionId(row.getString(INDEX_SESSION_ID));
      result.setPlayerId(String.valueOf(row.getLong(INDEX_PLAYER_ID)));
      result.setStartDate(OffsetDateTime.parse(row.getString(INDEX_START_DATE), POSGRESS_TIME_FORMATTER));
    }

    return result;
  }

}
