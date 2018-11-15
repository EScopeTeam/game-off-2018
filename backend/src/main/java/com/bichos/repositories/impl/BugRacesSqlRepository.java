package com.bichos.repositories.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.BugColorPalette;
import com.bichos.models.bugs.BugPart;
import com.bichos.models.bugs.BugRace;
import com.bichos.repositories.BugRacesRepository;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugRacesSqlRepository implements BugRacesRepository {

  private static final String FIND_ALL_SQL = "SELECT bug_race_id, name, generation_chance FROM bugs_races";

  private static final int INDEX_ID = 0;
  private static final int INDEX_NAME = 1;
  private static final int INDEX_GENERATION_CHANCE = 2;

  private final SQLClient client;

  private final BugPartsSqlRepository partsRepository;

  private final BugColorsPalettesSqlRepository colorsPalettesRepository;

  @Override
  public Future<List<BugRace>> findAll() {
    final Future<ResultSet> fQuery = Future.future();
    client.query(FIND_ALL_SQL, fQuery.completer());

    return fQuery.compose(rows -> {
      return CompositeFuture
          .all(rows.getResults().stream().map(this::mapRow).collect(Collectors.toList()))
          .map(CompositeFuture::list);
    });
  }

  private Future<BugRace> mapRow(final JsonArray row) {
    final String bugRaceId = String.valueOf(row.getLong(INDEX_ID));
    final Future<List<BugPart>> parts = partsRepository.findAllByRaceId(bugRaceId);
    final Future<List<BugColorPalette>> colorPalettes = colorsPalettesRepository.findAllByRaceId(bugRaceId);

    return CompositeFuture.all(parts, colorPalettes).map(v -> {
      final BugRace result = new BugRace();
      result.setBugRaceId(bugRaceId);
      result.setName(row.getString(INDEX_NAME));
      result.setGenerationChance(row.getInteger(INDEX_GENERATION_CHANCE));
      result.setParts(parts.result());
      result.setColorPalettes(colorPalettes.result());

      return result;
    });
  }

}
