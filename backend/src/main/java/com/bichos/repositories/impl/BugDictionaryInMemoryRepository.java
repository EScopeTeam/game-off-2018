package com.bichos.repositories.impl;

import java.util.List;
import java.util.Random;

import com.bichos.repositories.BugDictionaryRepository;

import io.vertx.core.Future;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugDictionaryInMemoryRepository implements BugDictionaryRepository {

  private final List<String> adjectives;

  private final List<String> nouns;

  private final Random random;

  @Override
  public Future<String> getRandomAdjective() {
    return Future.succeededFuture(adjectives.get(random.nextInt(adjectives.size())));
  }

  @Override
  public Future<String> getRandomNoun() {
    return Future.succeededFuture(nouns.get(random.nextInt(nouns.size())));
  }

}
