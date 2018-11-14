package com.bichos.models;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserAccount {

  private String userId;

  private String email;

  private String password;

  private String salt;

  private Boolean active;

  private OffsetDateTime creationTime;

  private OffsetDateTime updateTime;

}
