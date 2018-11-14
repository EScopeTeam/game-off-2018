package com.bichos.utils;

import java.util.List;
import java.util.Random;

import com.bichos.models.bugs.RandomElement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Randomizer {

  private static final int MAX_CHANCE_BINARY_DECISION = 100;

  private final Random rand;

  public <T extends RandomElement> T getOneRandomly(List<T> elements) {
    int count = (int) elements.stream().mapToInt(RandomElement::getGenerationChance).count();
    int random = rand.nextInt(count);

    int acc = 0;
    for (T element : elements) {
      acc += element.getGenerationChance();

      if (random < acc) {
        return element;
      }
    }

    return elements.isEmpty() ? null : elements.get(elements.size() - 1);
  }

  public boolean binaryDecision(RandomElement element) {
    return rand.nextInt(MAX_CHANCE_BINARY_DECISION) < element.getGenerationChance();
  }

}
