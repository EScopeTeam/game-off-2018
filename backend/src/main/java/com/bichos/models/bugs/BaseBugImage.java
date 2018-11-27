package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBugImage implements Comparable<BaseBugImage> {

  private String bugImageId;

  private String name;

  private String imageUrl;

  private boolean allowColor;

  private int position;

  protected BaseBugImage() {
  }

  @Override
  public int compareTo(final BaseBugImage o) {
    return Integer.compare(getPosition(), o.getPosition());
  }

}
