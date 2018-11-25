package com.bichos.repositories.impl;

import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.bichos.models.bugs.BugColor;
import com.bichos.models.bugs.BugColorPalette;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugColorsPalettesSqlRepositoryTest {

  private static final String RACE_ID = "2";
  private static final long ID = 1L;
  private static final String NAME = "Color Palette 1";
  private static final int GENERATION_CHANCE = 20;

  private BugColorsPalettesSqlRepository colorsPaletteRepository;

  private BugColorsSqlRepository colorsRepository;

  private SqlClientMock client;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    colorsRepository = mock(BugColorsSqlRepository.class);
    colorsPaletteRepository = new BugColorsPalettesSqlRepository(client, colorsRepository);
  }

  @Test
  public void findAllByRaceIdShouldReturnRightPalettes(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(GENERATION_CHANCE));

    client.setResultQueryWithParams(new ResultSet(Collections.emptyList(), rows, null));

    final List<BugColor> colors = Arrays.asList(new BugColor());
    Mockito.when(colorsRepository.findAllByPaletteId(String.valueOf(ID)))
        .thenReturn(Future.succeededFuture(colors));

    final Async async = context.async();
    colorsPaletteRepository.findAllByRaceId(RACE_ID).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugColorPalette palette = res.result().get(0);
        context.assertEquals(String.valueOf(ID), palette.getBugColorPaletteId());
        context.assertEquals(NAME, palette.getName());
        context.assertEquals(GENERATION_CHANCE, palette.getGenerationChance());
        context.assertEquals(colors, palette.getColors());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

}
