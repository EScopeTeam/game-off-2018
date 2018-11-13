package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugImage implements Comparable<BugImage> {

  private String bugImageId;

  private String name;

  private String imageUrl;

  private int position;

  private boolean allowColor;

  private int generationChance;

  public BugSelectedImage generate(BugColorPalette colorPalette) {
    BugColor color = colorPalette.getRandomColor();
    BugSelectedImage result = new BugSelectedImage();
    result.setBugColorId(bugImageId);
    result.setBugColorId(color.getBugColorId());
    result.setBugColorRgbCode(color.getRgbCode());
    result.setPosition(position);

    return result;
  }

  @Override
  public int compareTo(BugImage o) {
    return Integer.compare(position, o.position);
  }

}
