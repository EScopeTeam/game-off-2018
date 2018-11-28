package com.bichos.models.bugs;

import org.junit.Assert;
import org.junit.Test;

public class BugSelectedImageTest {

  private static final String IMAGE_URL = "BODY-1-1.png";
  private static final String COLOR_RGB = "#000";

  @Test
  public void theHashShouldReturnTheImageUrlAndTheColorCodeIfColorIsAllowed() {
    final BugSelectedImage image = new BugSelectedImage();
    image.setImageUrl(IMAGE_URL);
    image.setBugColorRgbCode(COLOR_RGB);
    image.setAllowColor(true);

    Assert.assertEquals(IMAGE_URL + "," + COLOR_RGB, image.hash());
  }

  @Test
  public void theHashShouldReturnTheImageUrlOnlyIfColorIsNOTAllowed() {
    final BugSelectedImage image = new BugSelectedImage();
    image.setImageUrl(IMAGE_URL);
    image.setAllowColor(false);

    Assert.assertEquals(IMAGE_URL, image.hash());
  }

}
