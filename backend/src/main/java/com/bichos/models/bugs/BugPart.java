package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugPart implements RandomElement, Comparable<BugPart> {

  private String bugPartId;

  private String name;

  private int position;

  private boolean required;

  private int generationChance;

  private List<BugPart> relatedParts = new ArrayList<>();

  private List<BugPattern> patterns = new ArrayList<>();

  public BugSelectedPart generate(BugColorPalette colorPalette) {
    BugSelectedPart result = new BugSelectedPart();
    result.setBugPartId(bugPartId);
    result.setRelatedParts(generateRelatedParts(colorPalette));
    result.setPattern(generatePattern(colorPalette));
    result.setPosition(position);

    return result;
  }

  public boolean shouldBeGenerateRandomly() {
    return required || Randomizer.binaryDecision(this);
  }

  private List<BugSelectedPart> generateRelatedParts(BugColorPalette colorPalette) {
    return relatedParts.stream()
        .filter(p -> p.shouldBeGenerateRandomly())
        .sorted()
        .map(part -> part.generate(colorPalette))
        .collect(Collectors.toList());
  }

  private BugSelectedPattern generatePattern(BugColorPalette colorPalette) {
    return Randomizer.getOneRandomly(patterns).generate(colorPalette);
  }

  @Override
  public int compareTo(BugPart o) {
    return Integer.compare(position, o.position);
  }

}
