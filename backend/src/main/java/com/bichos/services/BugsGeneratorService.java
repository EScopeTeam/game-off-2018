package com.bichos.services;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugStats;

import io.vertx.core.Future;

public interface BugsGeneratorService {

  Future<Bug> generate();

  Future<BugStats> generateFullBug();

  Future<Void> saveBug(BugStats bugStats);

}
