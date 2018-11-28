package com.bichos.repositories.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.bugs.BugImage;
import com.bichos.models.bugs.BugPattern;
import com.bichos.models.bugs.BugSelectedImage;
import com.bichos.models.bugs.BugSelectedPattern;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugPatternsSqlRepositoryTest {

  private static final long ID = 1L;
  private static final String NAME = "Pattern 1";
  private static final int GENERATION_CHANCE = 20;

  private static final String PART_ID = "2";
  private static final String BUG_ID = "3";

  private BugPatternsSqlRepository patternsRepository;

  private BugImagesSqlRepository imagesRepository;

  private SqlClientMock client;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    imagesRepository = mock(BugImagesSqlRepository.class);
    patternsRepository = new BugPatternsSqlRepository(client, imagesRepository);
  }

  @Test
  public void findAllByPartIdShouldReturnRightPatterns(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(GENERATION_CHANCE));

    client.setResultQueryWithParams(new ResultSet(Collections.emptyList(), rows, null));

    final List<BugImage> images = Arrays.asList(new BugImage());
    when(imagesRepository.findAllByPatternId(String.valueOf(ID))).thenReturn(Future.succeededFuture(images));

    final Async async = context.async();
    patternsRepository.findAllByPartId(PART_ID).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugPattern pattern = res.result().get(0);
        context.assertEquals(String.valueOf(ID), pattern.getBugPatternId());
        context.assertEquals(NAME, pattern.getName());
        context.assertEquals(GENERATION_CHANCE, pattern.getGenerationChance());
        context.assertEquals(images, pattern.getImages());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

  @Test
  public void findSelectedById(final TestContext context) {
    final JsonArray row = new JsonArray()
        .add(ID)
        .add(NAME)
        .add(GENERATION_CHANCE);

    client.setResultQuerySingleWithParams(row);

    final List<BugSelectedImage> images = Arrays.asList(new BugSelectedImage());
    when(imagesRepository.findAllSelectedByPatternId(BUG_ID, String.valueOf(ID))).thenReturn(Future.succeededFuture(images));

    final Async async = context.async();
    patternsRepository.findSelectedById(BUG_ID, PART_ID).setHandler(res -> {
      if (res.succeeded()) {
        final BugSelectedPattern pattern = res.result();
        context.assertEquals(String.valueOf(ID), pattern.getBugPatternId());
        context.assertEquals(NAME, pattern.getName());
        context.assertEquals(GENERATION_CHANCE, pattern.getGenerationChance());
        context.assertEquals(images, pattern.getImages());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

}
