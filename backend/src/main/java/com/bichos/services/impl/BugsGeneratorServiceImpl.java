package com.bichos.services.impl;

import java.util.List;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugRace;
import com.bichos.repositories.BugRacesRepository;
import com.bichos.services.BugsGeneratorService;
import com.bichos.utils.Randomizer;

import io.vertx.core.Future;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugsGeneratorServiceImpl implements BugsGeneratorService {

  private final Randomizer randomizer;

  private final BugRacesRepository racesRepository;

  @Override
  public Future<Bug> generate() {
    return getBugRaces().map(races -> {
      final BugRace race = randomizer.getOneRandomly(races);

      final Bug result = new Bug();
      result.setBugRaceId(race.getBugRaceId());
      result.setParts(race.generate(randomizer));
      return result;
    });
  }

  private Future<List<BugRace>> getBugRaces() {
    // TODO cache races
    return racesRepository.findAll();
  }

}
