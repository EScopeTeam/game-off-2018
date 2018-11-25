package com.bichos.repositories.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.BugColorPalette;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugColorsPalettesSqlRepository {

  private static final String FIND_ALL_BY_RACE_SQL = "SELECT bug_color_palette_id, name, generation_chance FROM bugs_color_palettes WHERE bug_race_id = ?";

  private static final int INDEX_ID = 0;
  private static final int INDEX_NAME = 1;
  private static final int INDEX_GENERATION_CHANCE = 2;

  private final SQLClient client;

  private final BugColorsSqlRepository colorsRepository;

  public Future<List<BugColorPalette>> findAllByRaceId(final String bugRaceId) {
    final Future<ResultSet> fQuery = Future.future();
    final JsonArray params = new JsonArray()
        .add(Long.valueOf(bugRaceId));
    client.queryWithParams(FIND_ALL_BY_RACE_SQL, params, fQuery.completer());

    return fQuery.compose(rows -> {
      return CompositeFuture
          .all(rows.getResults().stream().map(this::mapRow).collect(Collectors.toList()))
          .map(CompositeFuture::list);
    });
  }

  private Future<BugColorPalette> mapRow(final JsonArray row) {
    final String bugColorPaletteId = String.valueOf(row.getLong(INDEX_ID));
    return colorsRepository.findAllByPaletteId(bugColorPaletteId).map(colors -> {
      final BugColorPalette result = new BugColorPalette();
      result.setBugColorPaletteId(bugColorPaletteId);
      result.setName(row.getString(INDEX_NAME));
      result.setGenerationChance(row.getInteger(INDEX_GENERATION_CHANCE));
      result.setColors(colors);

      return result;
    });
  }

}
