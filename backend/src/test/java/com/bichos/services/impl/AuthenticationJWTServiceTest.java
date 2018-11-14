package com.bichos.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import com.bichos.exceptions.InvalidLoginException;
import com.bichos.models.Player;
import com.bichos.models.PlayerSession;
import com.bichos.repositories.PlayersRepository;
import com.bichos.repositories.PlayersSessionsRepository;
import com.bichos.utils.HandlersUtils;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jdbc.JDBCHashStrategy;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class AuthenticationJWTServiceTest {

  private static final String PLAYER_ID = "1";
  private static final String VALID_USERNAME = "test01";
  private static final String NOT_VALID_USERNAME = "test02";
  private static final String VALID_PASSWORD = "right";
  private static final String NOT_VALID_PASSWORD = "wrong";
  private static final String SALT = "abcd";
  private static final String TOKEN = "1234";
  private static final String SESSION_ID = "abcd-efgh";
  private static final Instant INSTANT_NOW = Instant.now();
  private static final ZoneId INSTANT_ZONEID = ZoneId.of("UTC");

  private AuthenticationJWTService service;

  private JWTAuth jwtAuth;

  private JDBCHashStrategy hashStrategy;

  private Clock clock;

  private PlayersRepository playersRepository;

  private PlayersSessionsRepository playersSessionsRepository;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void initialize() {
    jwtAuth = mock(JWTAuth.class);
    hashStrategy = JDBCHashStrategy.createSHA512(rule.vertx());

    playersRepository = mock(PlayersRepository.class);
    when(playersRepository.updateOnlineById(anyString(), anyBoolean())).thenReturn(Future.succeededFuture());

    clock = mock(Clock.class);
    when(clock.instant()).thenReturn(INSTANT_NOW);
    when(clock.getZone()).thenReturn(INSTANT_ZONEID);

    playersSessionsRepository = mock(PlayersSessionsRepository.class);
    when(playersSessionsRepository.insertSession(any())).thenReturn(Future.succeededFuture());
    when(playersSessionsRepository.removeSession(SESSION_ID)).thenReturn(Future.succeededFuture());
    final PlayerSession playerSession = new PlayerSession();
    playerSession.setPlayerId(PLAYER_ID);
    playerSession.setSessionId(SESSION_ID);
    playerSession.setStartDate(OffsetDateTime.now());
    when(playersSessionsRepository.findSession(SESSION_ID)).thenReturn(Future.succeededFuture(playerSession));

    service = new AuthenticationJWTService(jwtAuth, mock(JWTOptions.class), hashStrategy, clock, playersRepository, playersSessionsRepository);
  }

  @Test
  public void loginThrowsExceptionIfThereIsNoPlayerWithTheGivenUsername(final TestContext context) {
    when(playersRepository.findPlayerByUsername(NOT_VALID_USERNAME)).thenReturn(Future.succeededFuture());

    final Async async = context.async();
    service.login(NOT_VALID_USERNAME, VALID_PASSWORD).setHandler(result -> {
      if (result.succeeded()) {
        context.fail("Should fail.");
      } else {
        context.assertEquals(InvalidLoginException.class, result.cause().getClass());
        async.complete();
      }
    });
  }

  @Test
  public void loginThrowsExceptionIfThePlayerPasswordIsNotTheGivenPasswordHashed(final TestContext context) {
    final Player player = new Player();
    player.setSalt(SALT);
    player.setPassword(hashStrategy.computeHash(VALID_PASSWORD, SALT, -1));

    when(playersRepository.findPlayerByUsername(VALID_USERNAME)).thenReturn(Future.succeededFuture(player));

    final Async async = context.async();
    service.login(VALID_USERNAME, NOT_VALID_PASSWORD).setHandler(result -> {
      if (result.succeeded()) {
        context.fail("Should fail.");
      } else {
        context.assertEquals(InvalidLoginException.class, result.cause().getClass());
        async.complete();
      }
    });
  }

  @Test
  public void loginReturnsAJWTTokenWithTheIDOfThePlayerIfTheDataIsRight(final TestContext context) {
    final Player player = new Player();
    player.setUserId(PLAYER_ID);
    player.setSalt(SALT);
    player.setPassword(hashStrategy.computeHash(VALID_PASSWORD, SALT, -1));

    when(playersRepository.findPlayerByUsername(VALID_USERNAME)).thenReturn(Future.succeededFuture(player));
    when(jwtAuth.generateToken(any(), any())).thenReturn(TOKEN);

    final Async async = context.async();
    service.login(VALID_USERNAME, VALID_PASSWORD).setHandler(result -> {
      if (result.succeeded()) {
        context.assertEquals(TOKEN, result.result());

        final ArgumentCaptor<JsonObject> claimsCaptor = ArgumentCaptor.forClass(JsonObject.class);
        verify(jwtAuth, times(1)).generateToken(claimsCaptor.capture(), any());
        context.assertEquals(PLAYER_ID, claimsCaptor.getValue().getString("sub"));

        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Test
  public void authenticateTokenReturnsThePlayerIdIfTheTokenIsValid(final TestContext context) {
    final User user = mock(User.class);
    when(user.principal()).thenReturn(new JsonObject()
        .put("sub", PLAYER_ID));
    doAnswer(answer -> HandlersUtils.asyncResult(answer, 1, user)).when(jwtAuth).authenticate(any(), any());

    final Async async = context.async();
    service.authenticateToken(TOKEN).setHandler(result -> {
      if (result.succeeded()) {
        context.assertEquals(PLAYER_ID, result.result());

        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Test
  public void authenticateTokenFailsWhenTokenIsNotValid(final TestContext context) {
    final Exception exception = new RuntimeException("Error");
    doAnswer(answer -> HandlersUtils.asyncFailure(answer, 1, exception)).when(jwtAuth).authenticate(any(), any());

    final Async async = context.async();
    service.authenticateToken(TOKEN).setHandler(result -> {
      if (result.succeeded()) {
        context.fail("Should fail.");
      } else {
        context.assertEquals(exception, result.cause());

        async.complete();
      }
    });
  }

  @Test
  public void addWebsocketSessionShouldAddASessionWithNowAsStartDate(final TestContext context) {
    final Async async = context.async();
    service.addWebsocketSession(SESSION_ID, PLAYER_ID).setHandler(result -> {
      if (result.succeeded()) {
        final ArgumentCaptor<PlayerSession> sessionArgumentCaptor = ArgumentCaptor.forClass(PlayerSession.class);
        verify(playersSessionsRepository, times(1)).insertSession(sessionArgumentCaptor.capture());

        final PlayerSession session = sessionArgumentCaptor.getValue();
        context.assertEquals(PLAYER_ID, session.getPlayerId());
        context.assertEquals(SESSION_ID, session.getSessionId());
        context.assertEquals(OffsetDateTime.ofInstant(INSTANT_NOW, INSTANT_ZONEID), session.getStartDate());

        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Test
  public void addWebsocketSessionShouldMarkTheUserAsOnline(final TestContext context) {
    when(playersRepository.updateOnlineById(anyString(), anyBoolean())).thenReturn(Future.succeededFuture());

    final Async async = context.async();
    service.addWebsocketSession(SESSION_ID, PLAYER_ID).setHandler(result -> {
      if (result.succeeded()) {
        verify(playersRepository, times(1)).updateOnlineById(PLAYER_ID, true);

        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Test
  public void removeWebsocketSessionShouldRemoveTheGivenSession(final TestContext context) {
    when(playersSessionsRepository.countSessions(PLAYER_ID)).thenReturn(Future.succeededFuture(0L));

    final Async async = context.async();
    service.removeWebsocketSession(SESSION_ID).setHandler(result -> {
      if (result.succeeded()) {
        verify(playersSessionsRepository, times(1)).removeSession(SESSION_ID);

        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Test
  public void addWebsocketSessionShouldMarkTheUserAsOfflineIfItWasTheLastSession(final TestContext context) {
    when(playersSessionsRepository.countSessions(PLAYER_ID)).thenReturn(Future.succeededFuture(0L));

    final Async async = context.async();
    service.removeWebsocketSession(SESSION_ID).setHandler(result -> {
      if (result.succeeded()) {
        verify(playersRepository, times(1)).updateOnlineById(PLAYER_ID, false);

        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Test
  public void addWebsocketSessionShouldNOTMarkTheUserAsOfflineIfItWasntTheLastSession(final TestContext context) {
    when(playersSessionsRepository.countSessions(PLAYER_ID)).thenReturn(Future.succeededFuture(1L));

    final Async async = context.async();
    service.removeWebsocketSession(SESSION_ID).setHandler(result -> {
      if (result.succeeded()) {
        verify(playersRepository, times(0)).updateOnlineById(anyString(), anyBoolean());

        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

}
