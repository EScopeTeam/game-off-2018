package com.bichos.models.bugs;

import org.junit.Assert;
import org.junit.Test;

public class BugSelectedImageTest {

  private static final String IMAGE_ID = "image1";
  private static final String COLOR_RGB = "#000";

  @Test
  public void theHashShouldReturnTheImageIdAndTheColorCodeIfColorIsAllowed() {
    final BugSelectedImage image = new BugSelectedImage();
    image.setBugImageId(IMAGE_ID);
    image.setBugColorRgbCode(COLOR_RGB);
    image.setAllowColor(true);

    Assert.assertEquals(IMAGE_ID + "," + COLOR_RGB, image.hash());
  }

  @Test
  public void theHashShouldReturnTheImageIdOnlyIfColorIsNOTAllowed() {
    final BugSelectedImage image = new BugSelectedImage();
    image.setBugImageId(IMAGE_ID);
    image.setAllowColor(false);

    Assert.assertEquals(IMAGE_ID, image.hash());
  }

}
