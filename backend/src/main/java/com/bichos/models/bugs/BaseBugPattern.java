package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseBugPattern implements RandomElement {

  private String bugPatternId;

  private String name;

  private int generationChance;

}
