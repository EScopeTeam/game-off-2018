package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugRace {

  private String bugRaceId;

  private String name;

  private int generationChange;

  private List<BugPart> parts = new ArrayList<>();

  private List<BugColorPalette> colorPalettes = new ArrayList<>();

  public String generate() {
    BugColorPalette colorPalette = Randomizer.getOneRandomly(colorPalettes);

    Collections.sort(parts, (a, b) -> Integer.compare(a.getPosition(), b.getPosition()));

    StringBuilder builder = new StringBuilder();
    for (BugPart part : parts) {
      if (part.isRequired() || Randomizer.binaryDecision(part)) {
        builder.append(part.generate(colorPalette));
      }
    }

    return builder.toString();
  }

}
