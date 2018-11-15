package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugPattern extends BaseBugPattern {

  private List<BugImage> images = new ArrayList<>();

  public BugSelectedPattern generate(final Randomizer randomizer, final BugColorPalette colorPalette) {
    final BugSelectedPattern result = new BugSelectedPattern();
    result.setBugPatternId(getBugPatternId());
    result.setName(getName());
    result.setGenerationChance(getGenerationChance());
    result.setImages(generateImages(randomizer, colorPalette));

    return result;
  }

  private List<BugSelectedImage> generateImages(final Randomizer randomizer, final BugColorPalette colorPalette) {
    return images.stream()
        .sorted()
        .map(i -> i.generate(randomizer, colorPalette))
        .collect(Collectors.toList());
  }

}
