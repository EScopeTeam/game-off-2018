package com.bichos.models.bugs;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bichos.utils.Randomizer;

public class BugImageTest {

  private static final boolean ALLOW_COLOR = false;
  private static final String IMAGE_ID = "ID1";
  private static final String IMAGE_URL = "http://image.com";
  private static final String IMAGE_NAME = "IMG1";
  private static final int IMAGE_POSITION = 1;

  private static final String COLOR_ID1 = "color1";
  private static final String COLOR_RGB1 = "#000";
  private static final String COLOR_ID2 = "color2";
  private static final String COLOR_RGB2 = "#00F";

  private BugImage image;

  private Randomizer randomizer;

  private BugColorPalette colorPalette;

  @Before
  public void initialize() {
    image = new BugImage();
    image.setAllowColor(ALLOW_COLOR);
    image.setBugImageId(IMAGE_ID);
    image.setImageUrl(IMAGE_URL);
    image.setName(IMAGE_NAME);
    image.setPosition(IMAGE_POSITION);

    randomizer = mock(Randomizer.class);

    List<BugColor> colors = Arrays.asList(
        createColor(COLOR_ID1, COLOR_RGB1),
        createColor(COLOR_ID2, COLOR_RGB2));
    colorPalette = new BugColorPalette();
    colorPalette.setColors(colors);
  }

  private BugColor createColor(String bugColorId, String rgbCode) {
    BugColor result = new BugColor();
    result.setBugColorId(bugColorId);
    result.setRgbCode(rgbCode);

    return result;
  }

  @Test
  public void shouldCreateABugSelectedImage() {
    BugSelectedImage selectedImage = image.generate(randomizer, colorPalette);

    Assert.assertThat(selectedImage, hasProperty("allowColor", equalTo(ALLOW_COLOR)));
    Assert.assertThat(selectedImage, hasProperty("bugImageId", equalTo(IMAGE_ID)));
    Assert.assertThat(selectedImage, hasProperty("imageUrl", equalTo(IMAGE_URL)));
    Assert.assertThat(selectedImage, hasProperty("name", equalTo(IMAGE_NAME)));
    Assert.assertThat(selectedImage, hasProperty("position", equalTo(IMAGE_POSITION)));
  }

  @Test
  public void shouldCreateABugSelectedImageWithARandomColorFromTheGivenPaletteIfColorIsAllowed() {
    when(randomizer.getOneRandomly(colorPalette.getColors())).thenReturn(colorPalette.getColors().get(1));
    image.setAllowColor(true);

    BugSelectedImage selectedImage = image.generate(randomizer, colorPalette);

    Assert.assertThat(selectedImage, hasProperty("bugColorId", equalTo(COLOR_ID2)));
    Assert.assertThat(selectedImage, hasProperty("bugColorRgbCode", equalTo(COLOR_RGB2)));
  }

  @Test
  public void shouldCreateABugSelectedImageWithoutAColorIfColorIsNOTAllowed() {
    image.setAllowColor(false);

    BugSelectedImage selectedImage = image.generate(randomizer, colorPalette);

    Assert.assertThat(selectedImage, hasProperty("bugColorId", nullValue()));
    Assert.assertThat(selectedImage, hasProperty("bugColorRgbCode", nullValue()));
  }

}
