package com.bichos.repositories.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.bugs.BugPart;
import com.bichos.models.bugs.BugPattern;
import com.bichos.models.bugs.BugSelectedPart;
import com.bichos.models.bugs.BugSelectedPattern;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugPartsSqlRepositoryTest {

  private static final long ID = 0L;
  private static final String NAME = "Part 1";
  private static final boolean REQUIRED = true;
  private static final int POSITION = 3;
  private static final int GENERATION_CHANCE = 10;
  private static final long PATTERN_ID = 5L;

  private static final String RACE_ID = "1";
  private static final String BUG_ID = "2";

  private BugPartsSqlRepository partsRepository;

  private BugPatternsSqlRepository patternsRepository;

  private SqlClientMock client;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    patternsRepository = mock(BugPatternsSqlRepository.class);
    partsRepository = new BugPartsSqlRepository(client, patternsRepository);
  }

  @Test
  public void findAllByRaceIdShouldReturnRightParts(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(REQUIRED)
        .add(POSITION)
        .add(GENERATION_CHANCE));

    client.setFuncQueryWithParams(
        (sql, params) -> new ResultSet(Collections.emptyList(), sql.contains("parent_part_id = ?") ? Collections.emptyList() : rows, null));

    final List<BugPattern> patterns = Arrays.asList(new BugPattern());
    when(patternsRepository.findAllByPartId(String.valueOf(ID))).thenReturn(Future.succeededFuture(patterns));

    final Async async = context.async();
    partsRepository.findAllByRaceId(RACE_ID).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugPart part = res.result().get(0);
        context.assertEquals(String.valueOf(ID), part.getBugPartId());
        context.assertEquals(NAME, part.getName());
        context.assertEquals(REQUIRED, part.isRequired());
        context.assertEquals(POSITION, part.getPosition());
        context.assertEquals(GENERATION_CHANCE, part.getGenerationChance());
        context.assertEquals(patterns, part.getPatterns());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

  @Test
  public void findAllSelectedByBugIdShouldReturnRightParts(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(REQUIRED)
        .add(POSITION)
        .add(GENERATION_CHANCE)
        .add(PATTERN_ID));

    client.setFuncQueryWithParams(
        (sql, params) -> new ResultSet(Collections.emptyList(), sql.contains("parent_part_id = ?") ? Collections.emptyList() : rows, null));

    final BugSelectedPattern pattern = new BugSelectedPattern();
    when(patternsRepository.findSelectedById(BUG_ID, String.valueOf(PATTERN_ID))).thenReturn(Future.succeededFuture(pattern));

    final Async async = context.async();
    partsRepository.findAllSelectedByBugId(BUG_ID).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugSelectedPart part = res.result().get(0);
        context.assertEquals(String.valueOf(ID), part.getBugPartId());
        context.assertEquals(NAME, part.getName());
        context.assertEquals(REQUIRED, part.isRequired());
        context.assertEquals(POSITION, part.getPosition());
        context.assertEquals(GENERATION_CHANCE, part.getGenerationChance());
        context.assertEquals(pattern, part.getPattern());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

}
