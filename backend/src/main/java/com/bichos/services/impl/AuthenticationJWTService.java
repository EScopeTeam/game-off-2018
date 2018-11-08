package com.bichos.services.impl;

import java.time.Clock;
import java.time.OffsetDateTime;

import com.bichos.exceptions.InvalidLoginException;
import com.bichos.models.Player;
import com.bichos.models.PlayerSession;
import com.bichos.repositories.PlayersRepository;
import com.bichos.repositories.PlayersSessionsRepository;
import com.bichos.services.AuthenticationService;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jdbc.JDBCHashStrategy;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.jwt.JWTOptions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationJWTService implements AuthenticationService {

  private static final int HASH_VERSION = -1;

  private final JWTAuth jwtAuth;

  private final JWTOptions jwtOptions;

  private final JDBCHashStrategy hashStrategy;

  private final Clock clock;

  private final PlayersRepository playersRepository;

  private final PlayersSessionsRepository playersSessionsRepository;

  @Override
  public Future<String> login(final String username, final String password) {
    final Future<Player> playerFuture = playersRepository.findPlayerByUsername(username);

    return playerFuture.map(player -> checkPasswordAndGenerateToken(player, password));
  }

  private String checkPasswordAndGenerateToken(final Player player, final String password) {
    if (player == null || !isValidPassword(player, password)) {
      throw new InvalidLoginException();
    }

    return jwtAuth.generateToken(new JsonObject().put("sub", player.getId()), jwtOptions);
  }

  private boolean isValidPassword(final Player player, final String password) {
    return player.getPassword().equals(hashPassword(password, player.getSalt()));
  }

  private String hashPassword(final String password, final String salt) {
    return hashStrategy.computeHash(password, salt, HASH_VERSION);
  }

  @Override
  public Future<String> authenticateToken(final String token) {
    final Future<User> fAuthenticate = Future.future();
    jwtAuth.authenticate(getAuthInfo(token), fAuthenticate.completer());

    return fAuthenticate.map(user -> user.principal().getString("sub"));
  }

  private JsonObject getAuthInfo(final String token) {
    final JsonArray permissions = new JsonArray();
    if (jwtOptions.getPermissions() != null) {
      for (final String permission : jwtOptions.getPermissions()) {
        permissions.add(permission);
      }
    }

    return new JsonObject()
        .put("jwt", token)
        .put("options", new JsonObject()
            .put("audience", permissions)
            .put("issuer", jwtOptions.getIssuer())
            .put("ignoreExpiration", false));
  }

  @Override
  public Future<Void> addWebsocketSession(final String sessionId, final String playerId) {
    final PlayerSession playerSession = new PlayerSession();
    playerSession.setSessionId(sessionId);
    playerSession.setPlayerId(playerId);
    playerSession.setStartDate(OffsetDateTime.now(clock));
    return playersSessionsRepository
        .insertSession(playerSession)
        .compose(v -> {
          return playersRepository.updateOnlineById(playerId, true);
        });
  }

  @Override
  public Future<Void> removeWebsocketSession(final String sessionId) {
    return findPlayerIdOfSessionId(sessionId)
        .compose(playerId -> {
          return playersSessionsRepository.removeSession(sessionId)
              .compose(v -> {
                return playersSessionsRepository.countSessions(playerId);
              }).compose(countSessions -> {
                if (countSessions == 0) {
                  return playersRepository.updateOnlineById(playerId, false);
                } else {
                  return Future.succeededFuture();
                }
              });
        });
  }

  private Future<String> findPlayerIdOfSessionId(final String sessionId) {
    return playersSessionsRepository.findSession(sessionId).map(PlayerSession::getPlayerId);
  }

}
