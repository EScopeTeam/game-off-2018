package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugColor implements RandomElement {

  private String bugColorId;

  private String name;

  private String rgbCode;

  private int generationChance;

}
