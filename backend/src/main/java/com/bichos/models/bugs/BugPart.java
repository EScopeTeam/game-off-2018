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

  public BugSelectedPart generate(final Randomizer randomizer, final BugColorPalette colorPalette) {
    final BugSelectedPart result = new BugSelectedPart();
    result.setBugPartId(getBugPartId());
    result.setName(getName());
    result.setRequired(isRequired());
    result.setPosition(getPosition());
    result.setGenerationChance(getGenerationChance());
    result.setRelatedParts(generateRelatedParts(randomizer, colorPalette));
    result.setPattern(generatePattern(randomizer, colorPalette));

    return result;
  }

  private List<BugSelectedPart> generateRelatedParts(final Randomizer randomizer, final BugColorPalette colorPalette) {
    return relatedParts.stream()
        .filter(p -> p.shouldBeGenerateRandomly(randomizer))
        .sorted()
        .map(part -> part.generate(randomizer, colorPalette))
        .collect(Collectors.toList());
  }

  private BugSelectedPattern generatePattern(final Randomizer randomizer, final BugColorPalette colorPalette) {
    final BugPattern pattern = randomizer.getOneRandomly(patterns);

    return pattern == null ? null : pattern.generate(randomizer, colorPalette);
  }

  public boolean shouldBeGenerateRandomly(final Randomizer randomizer) {
    return isRequired() || randomizer.binaryDecision(this);
  }

}
