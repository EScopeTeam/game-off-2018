package com.bichos.utils;

import io.vertx.ext.auth.User;

public final class UserUtils {

  private UserUtils() {
  }

  public static String getUserId(final User user) {
    return user.principal().getString("sub");
  }

}
