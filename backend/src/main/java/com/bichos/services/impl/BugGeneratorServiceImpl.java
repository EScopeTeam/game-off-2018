package com.bichos.services.impl;

import java.util.List;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugRace;
import com.bichos.repositories.BugRacesRepository;
import com.bichos.services.BugGeneratorService;
import com.bichos.utils.Randomizer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugGeneratorServiceImpl implements BugGeneratorService {

  private final Randomizer randomizer;

  private final BugRacesRepository racesRepository;

  @Override
  public Bug generate() {
    final BugRace race = randomizer.getOneRandomly(getBugRaces());

    final Bug result = new Bug();
    result.setBugRaceId(race.getBugRaceId());
    result.setParts(race.generate(randomizer));
    return result;
  }

  private List<BugRace> getBugRaces() {
    // TODO cache races
    return racesRepository.findAllRaces();
  }

}
