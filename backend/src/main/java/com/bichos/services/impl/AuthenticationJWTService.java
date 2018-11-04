package com.bichos.services.impl;

import com.bichos.exceptions.InvalidLoginException;
import com.bichos.models.Player;
import com.bichos.repositories.PlayersRepository;
import com.bichos.services.AuthenticationService;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
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

  private final PlayersRepository playersRepository;

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

}
