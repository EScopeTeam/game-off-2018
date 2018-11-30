package com.bichos.repositories.impl;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugSelectedImage;
import com.bichos.models.bugs.BugSelectedPart;
import com.bichos.models.bugs.BugStats;
import com.bichos.repositories.BugRepository;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.UpdateResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugsSqlRepository implements BugRepository {

  private static final String FIND_BY_ID = "SELECT bug_id, bug_race_id, player_id, name FROM bugs WHERE bug_id = ?";
  private static final String FIND_ALL_BY_PLAYER_ID = "SELECT bug_id, bug_race_id, player_id, name FROM bugs WHERE player_id = ?";
  private static final String INSERT_SELECTED_IMAGE = "INSERT INTO bugs_selected_images (bug_id, bug_image_id, bug_color_id) VALUES (?,?,?)";
  private static final String INSERT_SELECTED_PART = "INSERT INTO bugs_selected_parts (bug_id, bug_part_id, bug_pattern_id) VALUES (?,?,?)";
  private static final String INSERT_BUG =
      "INSERT INTO bugs (bug_race_id, player_id, name, update_time, current_food_level, max_food_level, current_happiness_level, max_happiness_level,"
          + " status) VALUES (?,?,?,?,?,?,?,?,?)";

  private static final DateTimeFormatter POSTGRE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");

  private static final int INDEX_ID = 0;
  private static final int INDEX_RACE_ID = 1;
  private static final int INDEX_PLAYER_ID = 2;
  private static final int INDEX_NAME = 3;

  private final SQLClient client;

  private final BugPartsSqlRepository partsRepository;

  private final Clock clock;

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

  @Override
  public Future<String> insertBug(final BugStats bugStats) {
    final Future<UpdateResult> fResult = Future.future();
    client.updateWithParams(INSERT_SELECTED_PART, new JsonArray()
        .add(bugStats.getBugRaceId()).add(bugStats.getPlayerId()).add(bugStats.getName())
        .add(OffsetDateTime.now(clock).format(POSTGRE_TIME_FORMATTER)).add(bugStats.getCurrentFoodLevel())
        .add(bugStats.getMaxFoodLevel()).add(bugStats.getCurrentHappinessLevel())
        .add(bugStats.getMaxHappinessLevel()).add(bugStats.getStatus().toString()), fResult.completer());

    return fResult.map(result -> result.getKeys().getString(INDEX_ID));
  }

  @Override
  public Future<Void> insertSelectedImage(final String bugId, final BugSelectedImage bugSelectedImage) {
    final Future<UpdateResult> fResult = Future.future();
    client.updateWithParams(INSERT_SELECTED_PART, new JsonArray()
        .add(bugId).add(bugSelectedImage.getBugImageId()).add(bugSelectedImage.getBugColorId()), fResult.completer());

    return fResult.mapEmpty();
  }

  @Override
  public Future<Void> insertSelectedPart(final String bugId, final BugSelectedPart bugSelectedPart) {
    final Future<UpdateResult> fResult = Future.future();
    client.updateWithParams(INSERT_SELECTED_PART, new JsonArray()
        .add(bugId).add(bugSelectedPart.getBugPartId()).add(bugSelectedPart.getPattern().getBugPatternId()), fResult.completer());

    return fResult.mapEmpty();
  }

}
