package com.bichos.repositories.impl;

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

  private static final String COUNT_NUM_SESSION = "SELECT count(*) FROM players_sessions WHERE player_id = ?";
  private static final String REMOVE_SESSION = "DELETE FROM players_sessions WHERE session_id = ?";
  private static final String INSERT_SESSION = "INSERT INTO players_sessions (session_id, player_id, start_date) VALUES (?, ?, ?)";

  private static final DateTimeFormatter POSGRESS_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");

  private final SQLClient client;

  @Override
  public Future<Long> countSessions(final String playerId) {
    final Future<JsonArray> fCount = Future.future();
    client.querySingleWithParams(COUNT_NUM_SESSION, new JsonArray().add(Long.valueOf(playerId)), fCount.completer());
    return fCount.map(arr -> arr.getLong(0));
  }

  @Override
  public Future<Void> removeSession(final String sessionId) {
    final Future<UpdateResult> fResult = Future.future();
    client.updateWithParams(REMOVE_SESSION, new JsonArray().add(sessionId), fResult.completer());
    return fResult.mapEmpty();
  }

  @Override
  public Future<Void> insertSession(final PlayerSession session) {
    final Future<UpdateResult> fResult = Future.future();
    client.updateWithParams(INSERT_SESSION, new JsonArray()
        .add(session.getSessionId())
        .add(Long.valueOf(session.getPlayerId()))
        .add(session.getStartDate().format(POSGRESS_TIME_FORMATTER)), fResult.completer());
    return fResult.mapEmpty();
  }

}
