package com.bichos.repositories;

import java.util.List;

import com.bichos.models.bugs.Bug;

import io.vertx.core.Future;

public interface BugRepository {

  Future<Bug> findById(String bugId);

  Future<List<Bug>> findAllByPlayerId(String playerId);

}
