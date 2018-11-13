package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugPart implements RandomElement {

  private String bugPartId;

  private String name;

  private int position;

  private boolean required;

  private int generationChance;

  private List<BugPart> relatedParts = new ArrayList<>();

  private List<BugPattern> patterns = new ArrayList<>();

  public String generate(BugColorPalette colorPalette) {
    StringBuilder builder = new StringBuilder();

    Collections.sort(relatedParts, (a, b) -> Integer.compare(a.getPosition(), b.getPosition()));

    boolean addedCurrent = false;
    for (BugPart relatedPart : relatedParts) {
      if (relatedPart.isRequired() || Randomizer.binaryDecision(relatedPart)) {
        if (relatedPart.getPosition() >= getPosition() && !addedCurrent) {
          builder.append(generateCurrent(colorPalette));
          addedCurrent = true;
        }

        builder.append(relatedPart.generate(colorPalette));
      }
    }

    if (!addedCurrent) {
      builder.append(generateCurrent(colorPalette));
    }

    return builder.toString();
  }

  private String generateCurrent(BugColorPalette colorPalette) {
    BugPattern pattern = Randomizer.getOneRandomly(patterns);

    if (pattern == null) {
      return "";
    } else {
      return pattern.generate(colorPalette);
    }
  }

}
