package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugPattern implements RandomElement {

  private String bugPatternId;

  private String name;

  private int generationChance;

  private List<BugImage> images = new ArrayList<>();

  public String generate(BugColorPalette colorPalette) {
    StringBuilder builder = new StringBuilder();

    Collections.sort(images, (a, b) -> Integer.compare(a.getPosition(), b.getPosition()));

    for (BugImage image : images) {
      builder.append(image.generate(colorPalette));
    }

    return builder.toString();
  }

}
