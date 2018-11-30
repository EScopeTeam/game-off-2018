package com.bichos.repositories;

import java.util.List;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugSelectedImage;
import com.bichos.models.bugs.BugSelectedPart;
import com.bichos.models.bugs.BugStats;

import io.vertx.core.Future;

public interface BugRepository {

  Future<Bug> findById(String bugId);

  Future<List<Bug>> findAllByPlayerId(String playerId);

  Future<String> insertBug(BugStats bugStats);

  Future<Void> insertSelectedImage(String bugId, BugSelectedImage bugSelectedImage);

  Future<Void> insertSelectedPart(String bugId, BugSelectedPart bugSelectedPart);

}
