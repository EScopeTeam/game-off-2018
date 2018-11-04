package com.bichos.services.impl;

import com.bichos.repositories.PlayersRepository;
import com.bichos.services.PlayersService;

import io.vertx.core.Future;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayersServiceImpl implements PlayersService {

  private final PlayersRepository playersRepository;

  @Override
  public Future<Void> markAsOnline(final String playerId) {
    return playersRepository.updateOnlineById(playerId, true);
  }

  @Override
  public Future<Void> markAsOffline(final String playerId) {
    return playersRepository.updateOnlineById(playerId, false);
  }

}
