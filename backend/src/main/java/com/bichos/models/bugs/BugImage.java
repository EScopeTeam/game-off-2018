package com.bichos.models.bugs;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugImage extends BaseBugImage {

  public BugSelectedImage generate(Randomizer randomizer, BugColorPalette colorPalette) {
    BugColor color = colorPalette.getRandomColor(randomizer);
    BugSelectedImage result = new BugSelectedImage();
    result.setBugColorId(getBugImageId());
    result.setName(getName());
    result.setImageUrl(getImageUrl());
    result.setAllowColor(isAllowColor());
    result.setPosition(getPosition());
    result.setBugColorId(color.getBugColorId());
    result.setBugColorRgbCode(color.getRgbCode());

    return result;
  }

}
