package com.bichos.repositories;

import java.util.List;

import com.bichos.models.bugs.BugRace;

import io.vertx.core.Future;

public interface BugRacesRepository {

  Future<List<BugRace>> findAll();

}
