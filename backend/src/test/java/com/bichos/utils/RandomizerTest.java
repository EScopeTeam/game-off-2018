package com.bichos.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bichos.models.bugs.RandomElement;

public class RandomizerTest {

  private static final int MAX_CHANCE_BINARY_DECISION = 100;

  private static final int ELEMENT_GENERATION_CHANCE_1 = 10;
  private static final int ELEMENT_GENERATION_CHANCE_2 = 5;
  private static final int ELEMENT_GENERATION_CHANCE_3 = 20;

  private Randomizer randomizer;

  private Random random;

  @Before
  public void initialize() {
    random = mock(Random.class);

    randomizer = new Randomizer(random);
  }

  @Test
  public void shouldReturnTheElementIfTheRandomIsBetweenTheSumOfThePrevAndNextGenerationChances() {
    final List<RandomElement> elements = Arrays.asList(
        createRandomElement(ELEMENT_GENERATION_CHANCE_1),
        createRandomElement(ELEMENT_GENERATION_CHANCE_2),
        createRandomElement(ELEMENT_GENERATION_CHANCE_3));

    when(random.nextInt(ELEMENT_GENERATION_CHANCE_1 + ELEMENT_GENERATION_CHANCE_2 + ELEMENT_GENERATION_CHANCE_3))
        .thenReturn(ELEMENT_GENERATION_CHANCE_1 + ELEMENT_GENERATION_CHANCE_2 - 1);

    Assert.assertEquals(elements.get(1), randomizer.getOneRandomly(elements));
  }

  private RandomElement createRandomElement(final int generationChance) {
    return new RandomElement() {
      @Override
      public int getGenerationChance() {
        return generationChance;
      }
    };
  }

  @Test
  public void shouldReturnNullIfTheListIsEmpty() {
    final List<RandomElement> elements = Arrays.asList();

    when(random.nextInt(0)).thenReturn(0);

    Assert.assertNull(randomizer.getOneRandomly(elements));
  }

  @Test
  public void shouldReturnTrueIfTheRandomIsLowerThanTheGenerationChance() {
    final RandomElement element = createRandomElement(ELEMENT_GENERATION_CHANCE_1);

    when(random.nextInt(MAX_CHANCE_BINARY_DECISION)).thenReturn(ELEMENT_GENERATION_CHANCE_1 - 1);

    Assert.assertTrue(randomizer.binaryDecision(element));
  }

  @Test
  public void shouldReturnFalseIfTheRandomIsHigherThanTheGenerationChance() {
    final RandomElement element = createRandomElement(ELEMENT_GENERATION_CHANCE_1);

    when(random.nextInt(MAX_CHANCE_BINARY_DECISION)).thenReturn(ELEMENT_GENERATION_CHANCE_1 + 1);

    Assert.assertFalse(randomizer.binaryDecision(element));
  }

}
