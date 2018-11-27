package com.bichos.repositories.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.bugs.BugColorPalette;
import com.bichos.models.bugs.BugPart;
import com.bichos.models.bugs.BugRace;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugRacesSqlRepositoryTest {

  private static final long ID = 1L;
  private static final String NAME = "Race 1";
  private static final int GENERATION_CHANCE = 20;

  private BugRacesSqlRepository racesRepository;

  private SqlClientMock client;

  private BugPartsSqlRepository partsRepository;

  private BugColorsPalettesSqlRepository colorsPalettesRepository;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    partsRepository = mock(BugPartsSqlRepository.class);
    colorsPalettesRepository = mock(BugColorsPalettesSqlRepository.class);
    racesRepository = new BugRacesSqlRepository(client, partsRepository, colorsPalettesRepository);
  }

  @Test
  public void findAllShouldReturnRightRaces(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(GENERATION_CHANCE));

    client.setResultQuery(new ResultSet(Collections.emptyList(), rows, null));

    final List<BugColorPalette> palettes = Arrays.asList(new BugColorPalette());
    when(colorsPalettesRepository.findAllByRaceId(String.valueOf(ID))).thenReturn(Future.succeededFuture(palettes));

    final List<BugPart> parts = Arrays.asList(new BugPart());
    when(partsRepository.findAllByRaceId(String.valueOf(ID))).thenReturn(Future.succeededFuture(parts));

    final Async async = context.async();
    racesRepository.findAll().setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugRace race = res.result().get(0);
        context.assertEquals(String.valueOf(ID), race.getBugRaceId());
        context.assertEquals(NAME, race.getName());
        context.assertEquals(GENERATION_CHANCE, race.getGenerationChance());
        context.assertEquals(palettes, race.getColorPalettes());
        context.assertEquals(parts, race.getParts());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

}
