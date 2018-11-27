package com.bichos.utils;

import java.util.List;
import java.util.Random;

import com.bichos.models.bugs.RandomElement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Randomizer {

  private static final int MAX_CHANCE_BINARY_DECISION = 100;

  private final Random rand;

  public <T extends RandomElement> T getOneRandomly(final List<T> elements) {
    final int count = (int) elements.stream().mapToInt(RandomElement::getGenerationChance).sum();
    final int random = rand.nextInt(count);

    int acc = 0;
    T result = null;
    for (final T element : elements) {
      acc += element.getGenerationChance();

      if (random < acc) {
        result = element;
        break;
      }
    }

    return result;
  }

  public boolean binaryDecision(final RandomElement element) {
    return rand.nextInt(MAX_CHANCE_BINARY_DECISION) < element.getGenerationChance();
  }

}
