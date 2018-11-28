package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugStats extends Bug {

  private Long currentFoodLevel;

  private Long maxFoodLevel;

  private Long currentHappinessLevel;

  private Long maxHappinessLevel;

  private BugStatus status;

  public enum BugStatus {
    ALIVE, DEAD, HUNGRY, SAD, HUNGRY_AND_SAD
  }

}
