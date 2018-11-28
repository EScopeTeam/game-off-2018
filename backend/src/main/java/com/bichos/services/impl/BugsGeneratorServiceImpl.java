package com.bichos.services.impl;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugRace;
import com.bichos.models.bugs.BugStats;
import com.bichos.repositories.BugRacesRepository;
import com.bichos.services.BugsGeneratorService;
import com.bichos.utils.Randomizer;

import io.vertx.core.Future;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BugsGeneratorServiceImpl implements BugsGeneratorService {

  private static final int MIN_MAX_FOOD_LEVEL = 100;

  private static final int MAX_MAX_FOOD_LEVEL = 250;

  private static final int MIN_MAX_HAPPINESS_LEVEL = 100;

  private static final int MAX_MAX_HAPPINESS_LEVEL = 250;

  private final Randomizer randomizer;

  private final BugRacesRepository racesRepository;

  private final Clock clock;

  private final Random random;

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

  @Override
  public Future<BugStats> generateStats() {
    return Future.succeededFuture(createBugStats());
  }

  public BugStats getBugStatsFromBug(final Bug bug) {
    final BugStats bugStats = new BugStats();
    bugStats.setBugId(bug.getBugId());
    bugStats.setBugRaceId(bug.getBugRaceId());
    bugStats.setName(bug.getName());
    bugStats.setParts(bug.getParts());
    bugStats.setPlayerId(bug.getPlayerId());
    bugStats.setUpdateTime(OffsetDateTime.now(clock));

    return bugStats;
  }

  private BugStats createBugStats() {
    final BugStats result = new BugStats();
    result.setMaxFoodLevel(Long.valueOf(MIN_MAX_FOOD_LEVEL + random.nextInt(MAX_MAX_FOOD_LEVEL - MIN_MAX_FOOD_LEVEL)));
    result.setMaxHappinessLevel(Long.valueOf(MIN_MAX_HAPPINESS_LEVEL + random.nextInt(MAX_MAX_HAPPINESS_LEVEL - MIN_MAX_HAPPINESS_LEVEL)));
    result.setCurrentFoodLevel(result.getMaxFoodLevel());
    result.setCurrentHappinessLevel(result.getCurrentHappinessLevel());
    result.setStatus(BugStats.BugStatus.ALIVE);

    return result;
  }

}
