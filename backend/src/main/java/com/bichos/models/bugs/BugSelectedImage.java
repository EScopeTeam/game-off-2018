package com.bichos.models.bugs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugSelectedImage extends BaseBugImage {

  private String bugColorId;

  private String bugColorRgbCode;

  public String hash() {
    return getBugImageId() + (isAllowColor() ? "," + bugColorRgbCode : "");
  }

}
