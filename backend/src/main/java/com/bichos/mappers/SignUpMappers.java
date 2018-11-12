package com.bichos.mappers;

import com.bichos.models.ApiSignUpRequest;
import com.bichos.models.Player;
import com.bichos.models.UserAccount;

public final class SignUpMappers {

  private static final String DEFAULT_SALT = "";

  private SignUpMappers() {

  }

  public static Player mapSignUpToPlayer(ApiSignUpRequest request) {

    Player player = new Player();
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail(request.getMail());
    userAccount.setPassword(request.getPassword());
    userAccount.setSalt(DEFAULT_SALT);

    player.setUserAccount(userAccount);
    player.setUsername(request.getNickname());

    return player;
  }

}
