package com.bichos.services;

import com.bichos.models.Player;

import io.vertx.core.Future;

public interface SignUpService {

  Future<Void> signUp(Player player);

}
