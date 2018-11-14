package com.bichos.repositories.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

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
  private static final boolean PLAYER_ONLINE = false;
  private static final boolean PLAYER_ACTIVE = true;
  private static final OffsetDateTime CREATION_DATE = OffsetDateTime.now();
  private static final OffsetDateTime UPDATE_DATE = OffsetDateTime.now();
  private static final BigDecimal COINS = BigDecimal.valueOf(1);
  private static final BigInteger EXP_POINTS = BigInteger.valueOf(10);

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
        .add(PLAYER_EMAIL)
        .add(PLAYER_PASSWORD)
        .add(PLAYER_SALT)
        .add(PLAYER_ACTIVE)
        .add(PLAYER_USERNAME)
        .add(CREATION_DATE)
        .add(UPDATE_DATE)
        .add(COINS)
        .add(EXP_POINTS)
        .add(PLAYER_ONLINE));

    final Async async = context.async();
    playersRepository.findPlayerByUsername(PLAYER_USERNAME).setHandler(playerResult -> {
      if (playerResult.succeeded()) {
        final Player player = playerResult.result();

        context.assertNotNull(player);
        context.assertEquals(String.valueOf(PLAYER_ID), player.getId());
        context.assertEquals(PLAYER_USERNAME, player.getUsername());
        context.assertEquals(PLAYER_PASSWORD, player.getUserAccount().getPassword());
        context.assertEquals(PLAYER_SALT, player.getUserAccount().getSalt());
        context.assertEquals(PLAYER_EMAIL, player.getUserAccount().getEmail());
        context.assertEquals(PLAYER_ONLINE, player.isOnline());
        context.assertEquals(PLAYER_ACTIVE, player.getUserAccount().getActive());
        context.assertEquals(CREATION_DATE, player.getUserAccount().getCreationTime());
        context.assertEquals(UPDATE_DATE, player.getUserAccount().getUpdateTime());
        context.assertEquals(COINS, player.getCoins());
        context.assertEquals(EXP_POINTS, player.getExperiencePoints());

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
