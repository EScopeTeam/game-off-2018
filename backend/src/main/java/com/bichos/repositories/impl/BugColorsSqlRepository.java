package com.bichos.repositories.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.BugColor;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugColorsSqlRepository {

  private static final String FIND_ALL_BY_PALETTE_SQL = "SELECT bug_color_id, name, rgb_code, generation_chance FROM bugs_colors "
      + "WHERE bug_color_palette_id = ?";

  private static final int INDEX_ID = 0;
  private static final int INDEX_NAME = 1;
  private static final int INDEX_RGB_CODE = 2;
  private static final int INDEX_GENERATION_CHANCE = 3;

  private final SQLClient client;

  public Future<List<BugColor>> findAllByPaletteId(final String bugColorPaletteId) {
    final Future<ResultSet> fQuery = Future.future();
    final JsonArray params = new JsonArray()
        .add(Long.valueOf(bugColorPaletteId));
    client.queryWithParams(FIND_ALL_BY_PALETTE_SQL, params, fQuery.completer());

    return fQuery.map(rows -> {
      return rows.getResults().stream().map(this::mapRow).collect(Collectors.toList());
    });
  }

  private BugColor mapRow(final JsonArray row) {
    final BugColor result = new BugColor();
    result.setBugColorId(String.valueOf(row.getLong(INDEX_ID)));
    result.setName(row.getString(INDEX_NAME));
    result.setRgbCode(row.getString(INDEX_RGB_CODE));
    result.setGenerationChance(row.getInteger(INDEX_GENERATION_CHANCE));

    return result;
  }

}
