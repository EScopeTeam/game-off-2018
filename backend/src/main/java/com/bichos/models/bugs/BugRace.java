package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugRace implements RandomElement {

  private String bugRaceId;

  private String name;

  private int generationChance;

  private List<BugPart> parts = new ArrayList<>();

  private List<BugColorPalette> colorPalettes = new ArrayList<>();

  public List<BugSelectedPart> generate(final Randomizer randomizer) {
    final BugColorPalette colorPalette = randomizer.getOneRandomly(colorPalettes);

    return parts.stream()
        .filter(p -> p.shouldBeGenerateRandomly(randomizer))
        .sorted()
        .map(part -> part.generate(randomizer, colorPalette))
        .collect(Collectors.toList());
  }

}
