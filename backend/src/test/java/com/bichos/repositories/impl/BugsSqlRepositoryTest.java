package com.bichos.repositories.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugSelectedPart;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugsSqlRepositoryTest {

  private static final long ID = 1L;
  private static final long RACE_ID = 2L;
  private static final long PLAYER_ID = 3L;
  private static final String NAME = "Bug 1";

  private BugsSqlRepository bugsRepository;

  private SqlClientMock client;

  private BugPartsSqlRepository partsRepository;

  private Clock clock;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    clock = mock(Clock.class);
    when(clock.instant()).thenReturn(Instant.now());
    when(clock.getZone()).thenReturn(ZoneId.systemDefault());

    partsRepository = mock(BugPartsSqlRepository.class);
    bugsRepository = new BugsSqlRepository(client, partsRepository, clock);
  }

  @Test
  public void findByIdShouldReturnTheRightBug(final TestContext context) {
    final JsonArray row = new JsonArray()
        .add(ID)
        .add(RACE_ID)
        .add(PLAYER_ID)
        .add(NAME);

    client.setResultQuerySingleWithParams(row);

    List<BugSelectedPart> parts = Arrays.asList(new BugSelectedPart());
    when(partsRepository.findAllSelectedByBugId(String.valueOf(ID))).thenReturn(Future.succeededFuture(parts));

    final Async async = context.async();
    bugsRepository.findById(String.valueOf(ID)).setHandler(res -> {
      if (res.succeeded()) {
        checkBug(context, res.result());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

  @Test
  public void findAllByPlayerIdShouldReturnTheRightBugs(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(RACE_ID)
        .add(PLAYER_ID)
        .add(NAME));

    client.setResultQueryWithParams(new ResultSet(Collections.emptyList(), rows, null));

    List<BugSelectedPart> parts = Arrays.asList(new BugSelectedPart());
    when(partsRepository.findAllSelectedByBugId(String.valueOf(ID))).thenReturn(Future.succeededFuture(parts));

    final Async async = context.async();
    bugsRepository.findAllByPlayerId(String.valueOf(PLAYER_ID)).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        checkBug(context, res.result().get(0));

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

  private void checkBug(final TestContext context, final Bug bug) {
    context.assertEquals(String.valueOf(ID), bug.getBugId());
    context.assertEquals(String.valueOf(RACE_ID), bug.getBugRaceId());
    context.assertEquals(String.valueOf(PLAYER_ID), bug.getPlayerId());
    context.assertEquals(NAME, bug.getName());
  }

}
