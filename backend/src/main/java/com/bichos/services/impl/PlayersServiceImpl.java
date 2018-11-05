package com.bichos.services.impl;

import com.bichos.repositories.PlayersRepository;
import com.bichos.services.PlayersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayersServiceImpl implements PlayersService {

  private final PlayersRepository playersRepository;

}
