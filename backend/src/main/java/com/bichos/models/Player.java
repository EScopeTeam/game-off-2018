package com.bichos.models;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends UserAccount {

  private String username;

  private BigDecimal coins;

  private BigInteger experiencePoints;

  private boolean online;

  public BigInteger getLevel() {
    // TODO calculate level from experiencePoints
    return BigInteger.ZERO;
  }

}
