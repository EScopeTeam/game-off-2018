package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBugPart implements RandomElement, Comparable<BaseBugPart> {

  private String bugPartId;

  private String name;

  private boolean required;

  private int position;

  private int generationChance;

  protected BaseBugPart() {
  }

  @Override
  public int compareTo(final BaseBugPart o) {
    return Integer.compare(getPosition(), o.getPosition());
  }

}
