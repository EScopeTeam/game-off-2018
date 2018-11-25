package com.bichos.repositories.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.bugs.BugColor;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugColorsSqlRepositoryTest {

  private static final long ID = 1;
  private static final String NAME = "Red";
  private static final String CODE = "#FF0000";
  private static final int GENERATION_CHANCE = 10;

  private static final String PALETTE_ID = "2";

  private BugColorsSqlRepository colorsRepository;

  private SqlClientMock client;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    colorsRepository = new BugColorsSqlRepository(client);
  }

  @Test
  public void findAllByPaletteIdShouldReturnRightColors(final TestContext context) {
    final List<JsonArray> rows = Arrays.asList(new JsonArray()
        .add(ID)
        .add(NAME)
        .add(CODE)
        .add(GENERATION_CHANCE));

    client.setResultQueryWithParams(new ResultSet(Collections.emptyList(), rows, null));

    final Async async = context.async();
    colorsRepository.findAllByPaletteId(PALETTE_ID).setHandler(res -> {
      if (res.succeeded()) {
        context.assertEquals(1, res.result().size());

        final BugColor color = res.result().get(0);
        context.assertEquals(String.valueOf(ID), color.getBugColorId());
        context.assertEquals(NAME, color.getName());
        context.assertEquals(CODE, color.getRgbCode());
        context.assertEquals(GENERATION_CHANCE, color.getGenerationChance());

        async.complete();
      } else {
        context.fail(res.cause());
      }
    });
  }

}
