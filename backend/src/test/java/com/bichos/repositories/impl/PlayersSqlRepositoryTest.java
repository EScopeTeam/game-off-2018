package com.bichos.repositories.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.Player;
import com.bichos.utils.SqlClientMock;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class PlayersSqlRepositoryTest {

  private static final int PLAYER_ID = 12;
  private static final String PLAYER_USERNAME = "test01";
  private static final String PLAYER_PASSWORD = "superSecretPasswordHASHED";
  private static final String PLAYER_SALT = "abcd";
  private static final String PLAYER_EMAIL = "test@test.com";

  private PlayersSqlRepository playersRepository;

  private SqlClientMock client;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    playersRepository = new PlayersSqlRepository(client);
  }

  @Test
  public void checkFindPlayerByUsernameReturnsAPlayerIfThereIsOne(final TestContext context) {
    client.setResultQuerySingleWithParams(new JsonArray()
        .add(PLAYER_ID)
        .add(PLAYER_USERNAME)
        .add(PLAYER_PASSWORD)
        .add(PLAYER_SALT)
        .add(PLAYER_EMAIL));

    final Async async = context.async();
    playersRepository.findPlayerByUsername(PLAYER_USERNAME).setHandler(playerResult -> {
      if (playerResult.succeeded()) {
        final Player player = playerResult.result();

        context.assertNotNull(player);
        context.assertEquals(String.valueOf(PLAYER_ID), player.getId());
        context.assertEquals(PLAYER_USERNAME, player.getUsername());
        context.assertEquals(PLAYER_PASSWORD, player.getPassword());
        context.assertEquals(PLAYER_SALT, player.getSalt());
        context.assertEquals(PLAYER_EMAIL, player.getEmail());
        async.complete();
      } else {
        context.fail(playerResult.cause());
      }
    });
  }

  @Test
  public void checkFindPlayerByUsernameReturnsNullIfThePlayerDoesnExist(final TestContext context) {
    client.setResultQuerySingleWithParams(null);

    final Async async = context.async();
    playersRepository.findPlayerByUsername(PLAYER_USERNAME).setHandler(playerResult -> {
      if (playerResult.succeeded()) {
        final Player player = playerResult.result();

        context.assertNull(player);
        async.complete();
      } else {
        context.fail(playerResult.cause());
      }
    });
  }

}
