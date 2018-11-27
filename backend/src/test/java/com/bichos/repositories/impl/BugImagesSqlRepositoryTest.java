package com.bichos.repositories.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.bugs.BugImage;
import com.bichos.models.bugs.BugSelectedImage;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugImagesSqlRepositoryTest {

  private static final long ID = 1L;
  private static final String NAME = "image1";
  private static final String URL = "http://goog.gl/img1.png";
  private static final boolean ALLOW_COLOR = true;
  private static final int POSITION = 0;
  private static final long COLOR_ID = 3L;
  private static final String COLOR_CODE = "#FF0000";

  private static final String PATTERN_ID = "2";
  private static final String BUG_ID = "3";

  private BugImagesSqlRepository imagesRepository;

  private SqlClientMock client;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    imagesRepository = new BugImagesSqlRepository(client);
  }

  @Test
  public void findAllByPatternIdShouldReturnRightImages(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(URL)
        .add(ALLOW_COLOR)
        .add(POSITION));

    client.setResultQueryWithParams(new ResultSet(Collections.emptyList(), rows, null));

    final Async async = context.async();
    imagesRepository.findAllByPatternId(PATTERN_ID).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugImage image = res.result().get(0);
        context.assertEquals(String.valueOf(ID), image.getBugImageId());
        context.assertEquals(NAME, image.getName());
        context.assertEquals(URL, image.getImageUrl());
        context.assertEquals(ALLOW_COLOR, image.isAllowColor());
        context.assertEquals(POSITION, image.getPosition());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

  @Test
  public void findAllSelectedByPatternIdShouldReturnRightImages(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(URL)
        .add(ALLOW_COLOR)
        .add(POSITION)
        .add(COLOR_ID)
        .add(COLOR_CODE));

    client.setResultQueryWithParams(new ResultSet(Collections.emptyList(), rows, null));

    final Async async = context.async();
    imagesRepository.findAllSelectedByPatternId(BUG_ID, PATTERN_ID).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugSelectedImage image = res.result().get(0);
        context.assertEquals(String.valueOf(ID), image.getBugImageId());
        context.assertEquals(NAME, image.getName());
        context.assertEquals(URL, image.getImageUrl());
        context.assertEquals(ALLOW_COLOR, image.isAllowColor());
        context.assertEquals(POSITION, image.getPosition());
        context.assertEquals(String.valueOf(COLOR_ID), image.getBugColorId());
        context.assertEquals(COLOR_CODE, image.getBugColorRgbCode());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

}
