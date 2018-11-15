package com.bichos.repositories.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.BugPart;
import com.bichos.models.bugs.BugPattern;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugPartsSqlRepository {

  private static final String FIND_ALL = "SELECT bug_part_id, name, required, position, generation_chance FROM bugs_parts";
  private static final String FIND_ALL_BY_RACE_SQL = FIND_ALL + " WHERE bug_race_id = ?";
  private static final String FIND_ALL_BY_PARENT_SQL = FIND_ALL + " WHERE parent_part_id = ?";

  private static final int INDEX_ID = 0;
  private static final int INDEX_NAME = 1;
  private static final int INDEX_REQUIRED = 2;
  private static final int INDEX_POSITION = 3;
  private static final int INDEX_GENERATION_CHANCE = 4;

  private final SQLClient client;

  private final BugPatternsSqlRepository patternsRepository;

  public Future<List<BugPart>> findAllByRaceId(final String bugRaceId) {
    final JsonArray params = new JsonArray()
        .add(Long.valueOf(bugRaceId));

    return findAll(FIND_ALL_BY_RACE_SQL, params);
  }

  private Future<List<BugPart>> findAll(final String sql, final JsonArray params) {
    final Future<ResultSet> fQuery = Future.future();
    client.queryWithParams(sql, params, fQuery.completer());

    return fQuery.compose(rows -> {
      return CompositeFuture
          .all(rows.getResults().stream().map(this::mapRow).collect(Collectors.toList()))
          .map(CompositeFuture::list);
    });
  }

  private Future<BugPart> mapRow(final JsonArray row) {
    final String bugPartId = String.valueOf(row.getLong(INDEX_ID));
    final Future<List<BugPart>> futureRelatedParts = findAllByParent(bugPartId);
    final Future<List<BugPattern>> futurePatterns = patternsRepository.findAllByPartId(bugPartId);

    return CompositeFuture.all(futureRelatedParts, futurePatterns)
        .map(v -> {
          final BugPart result = new BugPart();
          result.setBugPartId(row.getString(INDEX_ID));
          result.setName(row.getString(INDEX_NAME));
          result.setRequired(row.getBoolean(INDEX_REQUIRED));
          result.setPosition(row.getInteger(INDEX_POSITION));
          result.setGenerationChance(row.getInteger(INDEX_GENERATION_CHANCE));
          result.setRelatedParts(futureRelatedParts.result());
          result.setPatterns(futurePatterns.result());

          return result;
        });
  }

  private Future<List<BugPart>> findAllByParent(final String parentPartId) {
    final JsonArray params = new JsonArray()
        .add(Long.valueOf(parentPartId));

    return findAll(FIND_ALL_BY_PARENT_SQL, params);
  }

}
