package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugSelectedImage implements Comparable<BugSelectedImage> {

  private String bugImageId;

  private String bugColorId;

  private String bugColorRgbCode;

  private int position;

  public String hash() {
    return bugImageId + "," + bugColorRgbCode;
  }

  @Override
  public int compareTo(BugSelectedImage o) {
    return Integer.compare(position, o.position);
  }

}
