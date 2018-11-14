package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseBugImage implements Comparable<BaseBugImage> {

  private String bugImageId;

  private String name;

  private String imageUrl;

  private boolean allowColor;

  private int position;

  @Override
  public int compareTo(final BaseBugImage o) {
    return Integer.compare(getPosition(), o.getPosition());
  }

}
