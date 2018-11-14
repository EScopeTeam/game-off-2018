package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugPattern extends BaseBugPattern {

  private List<BugImage> images = new ArrayList<>();

  public BugSelectedPattern generate(BugColorPalette colorPalette) {
    BugSelectedPattern result = new BugSelectedPattern();
    result.setBugPatternId(getBugPatternId());
    result.setName(getName());
    result.setGenerationChance(getGenerationChance());
    result.setImages(generateImages(colorPalette));

    return result;
  }

  private List<BugSelectedImage> generateImages(BugColorPalette colorPalette) {
    return images.stream()
        .sorted()
        .map(i -> i.generate(colorPalette))
        .collect(Collectors.toList());
  }

}
