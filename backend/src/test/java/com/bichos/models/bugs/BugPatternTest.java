package com.bichos.models.bugs;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bichos.utils.Randomizer;

public class BugPatternTest {

  private static final int POSITION1 = 10;
  private static final int POSITION2 = 1;
  private static final int POSITION3 = 5;

  private static final String PATTERN_ID = "p1";
  private static final String PATTERN_NAME = "pattern 1";
  private static final int GENERATION_CHANCE = 20;

  private BugPattern pattern;

  private Randomizer randomizer;

  private BugColorPalette colorPalette;

  @Before
  public void initialize() {
    randomizer = mock(Randomizer.class);
    colorPalette = mock(BugColorPalette.class);

    pattern = new BugPattern();
    pattern.setBugPatternId(PATTERN_ID);
    pattern.setName(PATTERN_NAME);
    pattern.setGenerationChance(GENERATION_CHANCE);
    pattern.setImages(Arrays.asList(
        createImage(POSITION1),
        createImage(POSITION2),
        createImage(POSITION3)));
  }

  private BugImage createImage(int position) {
    BugImage result = mock(BugImage.class);
    when(result.getPosition()).thenReturn(position);
    when(result.generate(eq(randomizer), any())).thenReturn(new BugSelectedImage());
    when(result.compareTo(any())).thenCallRealMethod();

    return result;
  }

  @Test
  public void generateShouldReturnASelectedPattern() {
    BugSelectedPattern result = pattern.generate(randomizer, colorPalette);

    Assert.assertThat(result, hasProperty("bugPatternId", equalTo(PATTERN_ID)));
    Assert.assertThat(result, hasProperty("name", equalTo(PATTERN_NAME)));
    Assert.assertThat(result, hasProperty("generationChance", equalTo(GENERATION_CHANCE)));
  }

  @Test
  public void generateShouldReturnAllThePatternGeneratedImagesInOrder() {
    BugSelectedPattern result = pattern.generate(randomizer, colorPalette);

    Assert.assertThat(result.getImages(), hasSize(pattern.getImages().size()));
    Assert.assertThat(result.getImages(), contains(
        pattern.getImages().get(1).generate(randomizer, null),
        pattern.getImages().get(2).generate(randomizer, null),
        pattern.getImages().get(0).generate(randomizer, null)));
  }

}
