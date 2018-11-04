package com.bichos.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import com.bichos.exceptions.InvalidLoginException;
import com.bichos.models.Player;
import com.bichos.repositories.PlayersRepository;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
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

  private AuthenticationJWTService service;

  private JWTAuth jwtAuth;

  private JDBCHashStrategy hashStrategy;

  private PlayersRepository playersRepository;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void initialize() {
    jwtAuth = mock(JWTAuth.class);
    hashStrategy = JDBCHashStrategy.createSHA512(rule.vertx());
    playersRepository = mock(PlayersRepository.class);

    service = new AuthenticationJWTService(jwtAuth, mock(JWTOptions.class), hashStrategy, playersRepository);
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
    player.setId(PLAYER_ID);
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

}
