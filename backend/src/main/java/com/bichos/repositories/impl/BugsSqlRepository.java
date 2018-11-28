package com.bichos.repositories.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugSelectedPart;
import com.bichos.repositories.BugRepository;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugsSqlRepository implements BugRepository {

  private static final String FIND_BY_ID = "SELECT bug_id, bug_race_id, player_id, name FROM bugs WHERE bug_id = ?";
  private static final String FIND_ALL_BY_PLAYER_ID = "SELECT bug_id, bug_race_id, player_id, name FROM bugs WHERE player_id = ?";

  private static final int INDEX_ID = 0;
  private static final int INDEX_RACE_ID = 1;
  private static final int INDEX_PLAYER_ID = 2;
  private static final int INDEX_NAME = 3;

  private final SQLClient client;

  private final BugPartsSqlRepository partsRepository;

  @Override
  public Future<Bug> findById(final String bugId) {
    final Future<JsonArray> fResult = Future.future();
    final JsonArray params = new JsonArray()
        .add(bugId);
    client.querySingleWithParams(FIND_BY_ID, params, fResult.completer());

    return fResult.compose(this::mapRow);
  }

  private Future<Bug> mapRow(final JsonArray row) {
    final String bugId = String.valueOf(row.getLong(INDEX_ID));
    final Future<List<BugSelectedPart>> fParts = partsRepository.findAllSelectedByBugId(bugId);

    return fParts.map(parts -> {
      final Bug result = new Bug();
      result.setBugId(bugId);
      result.setBugRaceId(String.valueOf(row.getLong(INDEX_RACE_ID)));
      result.setPlayerId(String.valueOf(row.getLong(INDEX_PLAYER_ID)));
      result.setName(row.getString(INDEX_NAME));

      return result;
    });
  }

  @Override
  public Future<List<Bug>> findAllByPlayerId(final String playerId) {
    final Future<ResultSet> fResult = Future.future();
    final JsonArray params = new JsonArray()
        .add(playerId);
    client.queryWithParams(FIND_ALL_BY_PLAYER_ID, params, fResult.completer());

    return fResult.compose(this::mapRows);
  }

  private Future<List<Bug>> mapRows(final ResultSet rows) {
    return CompositeFuture
        .all(rows.getResults().stream().map(this::mapRow).collect(Collectors.toList()))
        .map(CompositeFuture::list);
  }

}
