package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugImage {

  private String bugImageId;

  private String name;

  private String imageUrl;

  private int position;

  private boolean allowColor;

  private int generationChance;

  public String generate(BugColorPalette colorPalette) {
    BugColor color = colorPalette.getRandomColor();

    return bugImageId + "," + color.getRgbCode() + "|";
  }

}
