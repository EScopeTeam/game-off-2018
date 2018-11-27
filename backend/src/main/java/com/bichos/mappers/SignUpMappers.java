package com.bichos.mappers;

import com.bichos.models.ApiSignUpRequest;
import com.bichos.models.SignupRequest;

public final class SignUpMappers {

  private SignUpMappers() {
  }

  public static SignupRequest mapApiToModel(final ApiSignUpRequest request) {
    final SignupRequest model = new SignupRequest();
    model.setEmail(request.getEmail());
    model.setPassword(request.getPassword());
    model.setUsername(request.getUsername());

    return model;
  }

}
