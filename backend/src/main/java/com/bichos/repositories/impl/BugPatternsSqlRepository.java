package com.bichos.repositories.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.BugPattern;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugPatternsSqlRepository {

  private static final String FIND_ALL_BY_PART_SQL = "SELECT bug_pattern_id, name, generation_chance FROM bugs_patterns WHERE bug_part_id = ?";

  private static final int INDEX_ID = 0;
  private static final int INDEX_NAME = 1;
  private static final int INDEX_GENERATION_CHANCE = 2;

  private final SQLClient client;

  private final BugImagesSqlRepository imagesRepository;

  public Future<List<BugPattern>> findAllByPartId(final String bugPartId) {
    final Future<ResultSet> fQuery = Future.future();
    final JsonArray params = new JsonArray()
        .add(bugPartId);
    client.queryWithParams(FIND_ALL_BY_PART_SQL, params, fQuery.completer());

    return fQuery.compose(rows -> {
      return CompositeFuture
          .all(rows.getResults().stream().map(this::mapRow).collect(Collectors.toList()))
          .map(CompositeFuture::list);
    });
  }

  private Future<BugPattern> mapRow(final JsonArray row) {
    final String bugPatternId = String.valueOf(row.getLong(INDEX_ID));
    return imagesRepository.findAllByPatternId(bugPatternId).map(images -> {
      final BugPattern result = new BugPattern();
      result.setBugPatternId(bugPatternId);
      result.setName(row.getString(INDEX_NAME));
      result.setGenerationChance(row.getInteger(INDEX_GENERATION_CHANCE));
      result.setImages(images);

      return result;
    });
  }

}
