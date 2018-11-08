package com.bichos.mappers;

import com.bichos.models.ApiLoginResponse;

public final class LoginMappers {

  private LoginMappers() {
  }

  public static ApiLoginResponse mapTokenToResponse(final String token) {
    final ApiLoginResponse result = new ApiLoginResponse();
    result.setToken(token);

    return result;
  }

}
