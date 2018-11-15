package com.bichos.services;

import com.bichos.models.bugs.Bug;

import io.vertx.core.Future;

public interface BugGeneratorService {

  Future<Bug> generate();

}
