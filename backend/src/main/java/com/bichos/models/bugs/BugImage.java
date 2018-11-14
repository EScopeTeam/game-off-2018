package com.bichos.models.bugs;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugImage extends BaseBugImage {

  public BugSelectedImage generate(final Randomizer randomizer, final BugColorPalette colorPalette) {
    final BugSelectedImage result = new BugSelectedImage();
    result.setBugImageId(getBugImageId());
    result.setName(getName());
    result.setImageUrl(getImageUrl());
    result.setAllowColor(isAllowColor());
    result.setPosition(getPosition());
    if (isAllowColor()) {
      final BugColor color = colorPalette.getRandomColor(randomizer);
      result.setBugColorId(color.getBugColorId());
      result.setBugColorRgbCode(color.getRgbCode());
    }

    return result;
  }

}
