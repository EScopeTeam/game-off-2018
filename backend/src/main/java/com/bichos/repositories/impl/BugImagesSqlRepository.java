package com.bichos.repositories.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bichos.models.bugs.BugImage;
import com.bichos.models.bugs.BugSelectedImage;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugImagesSqlRepository {

  private static final String FIND_ALL_BY_PATTERN_SQL = "SELECT bug_image_id, name, image_url, allow_color, position FROM bugs_images WHERE bug_pattern_id = ?";
  private static final String FIND_ALL_SELECTED_BY_PATTERN_SQL = "SELECT i.bug_image_id, i.name, i.image_url, i.allow_color, i.position, "
      + "c.bug_color_id, c.rgb_code FROM bugs_selected_images is LEFT JOIN bugs_images i ON i.bug_image_id = si.bug_image_id "
      + "LEFT JOIN bugs_colors c ON c.bug_color_id = si.bug_color_id WHERE si.bug_id = ? AND i.bug_pattern_id = ?";

  private static final int INDEX_ID = 0;
  private static final int INDEX_NAME = 1;
  private static final int INDEX_IMAGE_URL = 2;
  private static final int INDEX_ALLOW_COLOR = 3;
  private static final int INDEX_POSITION = 4;
  private static final int INDEX_COLOR_ID = 5;
  private static final int INDEX_COLOR_CODE = 6;

  private final SQLClient client;

  public Future<List<BugImage>> findAllByPatternId(final String bugPatternId) {
    final Future<ResultSet> fQuery = Future.future();
    final JsonArray params = new JsonArray()
        .add(Long.valueOf(bugPatternId));
    client.queryWithParams(FIND_ALL_BY_PATTERN_SQL, params, fQuery.completer());

    return fQuery.map(rows -> {
      return rows.getResults().stream().map(this::mapRow).collect(Collectors.toList());
    });
  }

  private BugImage mapRow(final JsonArray row) {
    final BugImage result = new BugImage();
    result.setBugImageId(String.valueOf(row.getLong(INDEX_ID)));
    result.setName(row.getString(INDEX_NAME));
    result.setImageUrl(row.getString(INDEX_IMAGE_URL));
    result.setAllowColor(row.getBoolean(INDEX_ALLOW_COLOR));
    result.setPosition(row.getInteger(INDEX_POSITION));

    return result;
  }

  public Future<List<BugSelectedImage>> findAllSelectedByPatternId(final String bugId, final String bugPatternId) {
    final Future<ResultSet> fQuery = Future.future();
    final JsonArray params = new JsonArray()
        .add(Long.valueOf(bugId))
        .add(Long.valueOf(bugPatternId));
    client.queryWithParams(FIND_ALL_SELECTED_BY_PATTERN_SQL, params, fQuery.completer());

    return fQuery.map(rows -> {
      return rows.getResults().stream().map(this::mapSelectedRow).collect(Collectors.toList());
    });
  }

  private BugSelectedImage mapSelectedRow(final JsonArray row) {
    final BugSelectedImage result = new BugSelectedImage();
    result.setBugImageId(String.valueOf(row.getLong(INDEX_ID)));
    result.setName(row.getString(INDEX_NAME));
    result.setImageUrl(row.getString(INDEX_IMAGE_URL));
    result.setAllowColor(row.getBoolean(INDEX_ALLOW_COLOR));
    result.setPosition(row.getInteger(INDEX_POSITION));
    result.setBugColorId(String.valueOf(row.getLong(INDEX_COLOR_ID)));
    result.setBugColorRgbCode(row.getString(INDEX_COLOR_CODE));

    return result;
  }

}
