package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugColorPalette implements RandomElement {

  private String bugColorPaletteId;

  private String name;

  private int generationChance;

  private List<BugColor> colors = new ArrayList<>();

  public BugColor getRandomColor() {
    return Randomizer.getOneRandomly(colors);
  }

}
