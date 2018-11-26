package com.bichos.repositories.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

  private static final DateTimeFormatter POSTGRE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");

  private static final int PLAYER_ID = 12;
  private static final String PLAYER_USERNAME = "test01";
  private static final String PLAYER_PASSWORD = "superSecretPasswordHASHED";
  private static final String PLAYER_SALT = "abcd";
  private static final String PLAYER_EMAIL = "test@test.com";
  private static final boolean PLAYER_ONLINE = false;
  private static final boolean PLAYER_ACTIVE = true;
  private static final String CREATION_DATE = OffsetDateTime.now().format(POSTGRE_TIME_FORMATTER);
  private static final String UPDATE_DATE = OffsetDateTime.now().format(POSTGRE_TIME_FORMATTER);
  private static final double COINS = 1;
  private static final long EXP_POINTS = 10;

  private static final boolean START_ACTIVE_ACCOUNT = true;
  private static final double START_PLAYER_COINS = 0.0;
  private static final int START_EXPERIENCE_POINTS = 0;
  private static final boolean START_PLAYER_ONLINE = false;

  private PlayersSqlRepository playersRepository;

  private SqlClientMock client;

  private Clock clock;

  @Before
  public void initialize() {
    client = new SqlClientMock();

    clock = mock(Clock.class);
    when(clock.instant()).thenReturn(Instant.now());
    when(clock.getZone()).thenReturn(ZoneId.systemDefault());

    playersRepository = new PlayersSqlRepository(client, clock);
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
        context.assertEquals(String.valueOf(PLAYER_ID), player.getUserId());
        context.assertEquals(PLAYER_USERNAME, player.getUsername());
        context.assertEquals(PLAYER_PASSWORD, player.getPassword());
        context.assertEquals(PLAYER_SALT, player.getSalt());
        context.assertEquals(PLAYER_EMAIL, player.getEmail());
        context.assertEquals(PLAYER_ONLINE, player.isOnline());
        context.assertEquals(PLAYER_ACTIVE, player.getActive());
        context.assertEquals(OffsetDateTime.parse(CREATION_DATE, POSTGRE_TIME_FORMATTER), player.getCreationTime());
        context.assertEquals(OffsetDateTime.parse(UPDATE_DATE, POSTGRE_TIME_FORMATTER), player.getUpdateTime());
        context.assertEquals(BigDecimal.valueOf(COINS), player.getCoins());
        context.assertEquals(BigInteger.valueOf(EXP_POINTS), player.getExperiencePoints());

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

  @Test
  public void checkInsertPlayer(final TestContext context) {
    final Player player = new Player();
    player.setEmail(PLAYER_EMAIL);
    player.setOnline(PLAYER_ONLINE);
    player.setPassword(PLAYER_PASSWORD);
    player.setSalt(PLAYER_SALT);
    player.setUserId(String.valueOf(PLAYER_ID));
    player.setUsername(PLAYER_USERNAME);

    final Async async = context.async();
    playersRepository.insertPlayer(player).setHandler(result -> {
      final JsonArray expectedResult = new JsonArray()
          .add(PLAYER_EMAIL)
          .add(PLAYER_PASSWORD)
          .add(PLAYER_SALT)
          .add(START_ACTIVE_ACCOUNT)
          .add(PLAYER_USERNAME)
          .add(OffsetDateTime.now(clock).format(POSTGRE_TIME_FORMATTER))
          .add(OffsetDateTime.now(clock).format(POSTGRE_TIME_FORMATTER))
          .add(START_PLAYER_COINS)
          .add(START_EXPERIENCE_POINTS)
          .add(START_PLAYER_ONLINE);

      assertThat(expectedResult, is(client.getParamsUpdateWithParams()));
      async.complete();
    });
  }

  @Test
  public void checkExistsPlayerByUserNameOrEmailIfThereIsOne(final TestContext context) {
    client.setResultQuerySingleWithParams(new JsonArray().add(1));

    final Async async = context.async();
    playersRepository.existsPlayerbyUsernameOrEmail(PLAYER_USERNAME, PLAYER_EMAIL).setHandler(playerResult -> {
      if (playerResult.succeeded()) {
        if (playerResult.result()) {
          async.complete();
        } else {
          context.fail(playerResult.cause());
        }
      }
    });
  }

  @Test
  public void checkExistsPlayerByUserNameOrEmailIfThereIsNoOne(final TestContext context) {
    client.setResultQuerySingleWithParams(new JsonArray().add(0));

    final Async async = context.async();
    playersRepository.existsPlayerbyUsernameOrEmail("pepe", "pepe@es.es").setHandler(playerResult -> {
      if (playerResult.succeeded()) {
        if (playerResult.result()) {
          context.fail(playerResult.cause());
        } else {
          async.complete();
        }
      }
    });
  }

  @Test
  public void checkFindPlayerByPlayerIdReturnsPlayerdIfThereIsOne(final TestContext context) {
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
    playersRepository.findPlayerById(String.valueOf(PLAYER_ID)).setHandler(playerResult -> {
      if (playerResult.succeeded()) {
        final Player player = playerResult.result();

        context.assertNotNull(player);
        context.assertEquals(String.valueOf(PLAYER_ID), player.getUserId());
        context.assertEquals(PLAYER_USERNAME, player.getUsername());
        context.assertEquals(PLAYER_PASSWORD, player.getPassword());
        context.assertEquals(PLAYER_SALT, player.getSalt());
        context.assertEquals(PLAYER_EMAIL, player.getEmail());
        context.assertEquals(PLAYER_ONLINE, player.isOnline());
        context.assertEquals(PLAYER_ACTIVE, player.getActive());
        context.assertEquals(OffsetDateTime.parse(CREATION_DATE, POSTGRE_TIME_FORMATTER), player.getCreationTime());
        context.assertEquals(OffsetDateTime.parse(UPDATE_DATE, POSTGRE_TIME_FORMATTER), player.getUpdateTime());
        context.assertEquals(BigDecimal.valueOf(COINS), player.getCoins());
        context.assertEquals(BigInteger.valueOf(EXP_POINTS), player.getExperiencePoints());

        async.complete();
      } else {
        context.fail(playerResult.cause());
      }
    });
  }

  @Test
  public void checkFindPlayerByPlayerIdReturnsNullIfThereIsNone(final TestContext context) {
    client.setResultQuerySingleWithParams(null);

    final Async async = context.async();
    playersRepository.findPlayerById(String.valueOf(PLAYER_ID)).setHandler(playerResult -> {
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
