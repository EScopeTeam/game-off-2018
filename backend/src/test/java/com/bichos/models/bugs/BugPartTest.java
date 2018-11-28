package com.bichos.models.bugs;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bichos.utils.Randomizer;

public class BugPartTest {

  private static final String PART_ID1 = "id1";
  private static final int POSITION1 = 10;
  private static final boolean REQUIRED1 = true;
  private static final boolean RANDOMIZER_RESULT1 = false;
  private static final String PART_NAME1 = "part 1";
  private static final int PART_GENERATION_CHANCE1 = 20;

  private static final String PART_ID2 = "id2";
  private static final int POSITION2 = 1;
  private static final String PART_ID3 = "id3";
  private static final int POSITION3 = 9;
  private static final String PART_ID4 = "id4";
  private static final int POSITION4 = 7;

  private BugPart part;

  private Randomizer randomizer;

  private BugColorPalette colorPalette;

  @Before
  public void initialize() {
    randomizer = mock(Randomizer.class);
    colorPalette = mock(BugColorPalette.class);

    part = createPart(PART_ID1, POSITION1, REQUIRED1, RANDOMIZER_RESULT1);
    part.setName(PART_NAME1);
    part.setGenerationChance(PART_GENERATION_CHANCE1);
  }

  private BugPart createPart(final String partId, final int position, final boolean required, final boolean randomizerResult) {
    final BugPart result = new BugPart();
    result.setBugPartId(partId);
    result.setPosition(position);
    result.setRequired(required);
    when(randomizer.binaryDecision(result)).thenReturn(randomizerResult);

    return result;
  }

  @Test
  public void generateShouldGenerateASelectedBugPart() {
    final BugSelectedPart selectedPart = part.generate(randomizer, colorPalette);

    Assert.assertThat(selectedPart, hasProperty("bugPartId", equalTo(PART_ID1)));
    Assert.assertThat(selectedPart, hasProperty("name", equalTo(PART_NAME1)));
    Assert.assertThat(selectedPart, hasProperty("required", equalTo(REQUIRED1)));
    Assert.assertThat(selectedPart, hasProperty("position", equalTo(POSITION1)));
    Assert.assertThat(selectedPart, hasProperty("generationChance", equalTo(PART_GENERATION_CHANCE1)));
  }

  @Test
  public void generateShouldSetAndGenerateARandomPattern() {
    final List<BugPattern> patterns = Arrays.asList(createPattern(), createPattern());
    when(randomizer.getOneRandomly(patterns)).thenReturn(patterns.get(1));
    part.setPatterns(patterns);

    final BugSelectedPart selectedPart = part.generate(randomizer, colorPalette);

    Assert.assertEquals(patterns.get(1).generate(randomizer, colorPalette), selectedPart.getPattern());
  }

  private BugPattern createPattern() {
    final BugPattern result = mock(BugPattern.class);
    when(result.generate(randomizer, colorPalette)).thenReturn(new BugSelectedPattern());

    return result;
  }

  @Test
  public void generateShouldAddSortedTheRequiredRandomlyRelatedParts() {
    final List<BugPart> relatedParts = Arrays.asList(
        createPart(PART_ID2, POSITION2, false, false),
        createPart(PART_ID3, POSITION3, true, false),
        createPart(PART_ID4, POSITION4, false, true));
    part.setRelatedParts(relatedParts);

    final BugSelectedPart selectedPart = part.generate(randomizer, colorPalette);

    Assert.assertThat(selectedPart.getRelatedParts().get(0), hasProperty("bugPartId", equalTo(PART_ID4)));
    Assert.assertThat(selectedPart.getRelatedParts().get(1), hasProperty("bugPartId", equalTo(PART_ID3)));
  }

  @Test
  public void shouldBeGeneratedRandomlyShouldReturnTrueIfRequiredIsTrue() {
    part.setRequired(true);

    Assert.assertTrue(part.shouldBeGenerateRandomly(randomizer));
  }

  @Test
  public void shouldBeGeneratedRandomlyShouldReturnTrueIfRandomReturnsTrueAndIsNotRequired() {
    part.setRequired(false);
    when(randomizer.binaryDecision(part)).thenReturn(true);

    Assert.assertTrue(part.shouldBeGenerateRandomly(randomizer));
  }

  @Test
  public void shouldBeGeneratedRandomlyShouldReturnFalseIfRandomReturnsFalseAndIsNotRequired() {
    part.setRequired(false);
    when(randomizer.binaryDecision(part)).thenReturn(false);

    Assert.assertFalse(part.shouldBeGenerateRandomly(randomizer));
  }

}
