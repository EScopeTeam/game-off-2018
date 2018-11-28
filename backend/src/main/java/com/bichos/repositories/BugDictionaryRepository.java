package com.bichos.repositories;

import io.vertx.core.Future;

public interface BugDictionaryRepository {

  Future<String> getRandomAdjective();

  Future<String> getRandomNoun();

}
