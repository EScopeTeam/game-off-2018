package com.bichos.models;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerSession {

  private String playerId;

  private String sessionId;

  private OffsetDateTime startDate;

}
