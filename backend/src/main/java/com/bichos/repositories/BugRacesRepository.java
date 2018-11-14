package com.bichos.repositories;

import java.util.List;

import com.bichos.models.bugs.BugRace;

public interface BugRacesRepository {

  List<BugRace> findAllRaces();

}
