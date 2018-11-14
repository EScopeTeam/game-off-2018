package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugPart extends BaseBugPart {

  private List<BugPart> relatedParts = new ArrayList<>();

  private List<BugPattern> patterns = new ArrayList<>();

  public BugSelectedPart generate(Randomizer randomizer, BugColorPalette colorPalette) {
    BugSelectedPart result = new BugSelectedPart();
    result.setBugPartId(getBugPartId());
    result.setName(getName());
    result.setRequired(isRequired());
    result.setPosition(getPosition());
    result.setGenerationChance(getGenerationChance());
    result.setRelatedParts(generateRelatedParts(randomizer, colorPalette));
    result.setPattern(generatePattern(randomizer, colorPalette));

    return result;
  }

  public boolean shouldBeGenerateRandomly(Randomizer randomizer) {
    return isRequired() || randomizer.binaryDecision(this);
  }

  private List<BugSelectedPart> generateRelatedParts(Randomizer randomizer, BugColorPalette colorPalette) {
    return relatedParts.stream()
        .filter(p -> p.shouldBeGenerateRandomly(randomizer))
        .sorted()
        .map(part -> part.generate(randomizer, colorPalette))
        .collect(Collectors.toList());
  }

  private BugSelectedPattern generatePattern(Randomizer randomizer, BugColorPalette colorPalette) {
    return randomizer.getOneRandomly(patterns).generate(randomizer, colorPalette);
  }

}
