package com.bichos.mappers;

import com.bichos.models.ApiSignUpRequest;
import com.bichos.models.Player;

public final class SignUpMappers {

  private static final String DEFAULT_SALT = "";

  private SignUpMappers() {

  }

  public static Player mapSignUpToPlayer(final ApiSignUpRequest request) {
    final Player player = new Player();
    player.setEmail(request.getMail());
    player.setPassword(request.getPassword());
    player.setSalt(DEFAULT_SALT);
    player.setUsername(request.getNickname());

    return player;
  }

}
