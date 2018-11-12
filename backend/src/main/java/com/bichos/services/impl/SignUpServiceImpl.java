package com.bichos.services.impl;

import com.bichos.models.Player;
import com.bichos.repositories.PlayersRepository;
import com.bichos.services.SignUpService;

import io.vertx.core.Future;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

  private final PlayersRepository playersRepository;

  @Override
  public Future<Void> signUp(Player player) {

    validatePlayerData(player);

    return null;
  }

  private void validatePlayerData(Player player) {

    // TODO check if player already exists

  }

}
