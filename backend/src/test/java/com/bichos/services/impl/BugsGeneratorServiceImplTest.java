package com.bichos.services.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bichos.models.bugs.BugRace;
import com.bichos.models.bugs.BugSelectedPart;
import com.bichos.repositories.BugRacesRepository;
import com.bichos.utils.Randomizer;

import io.vertx.core.Future;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class BugsGeneratorServiceImplTest {

  private static final String RACE_ID = "1";
  private static final Instant INSTANT_NOW = Instant.now();
  private static final ZoneId INSTANT_ZONEID = ZoneId.of("UTC");

  private Randomizer randomizer;

  private BugRacesRepository racesRepository;

  private BugsGeneratorServiceImpl service;

  private Clock clock;

  private Random random;

  @Before
  public void initialize() {
    randomizer = mock(Randomizer.class);
    racesRepository = mock(BugRacesRepository.class);

    clock = mock(Clock.class);
    when(clock.instant()).thenReturn(INSTANT_NOW);
    when(clock.getZone()).thenReturn(INSTANT_ZONEID);

    random = mock(Random.class);

    service = new BugsGeneratorServiceImpl(randomizer, racesRepository, clock, random);
  }

  @Test
  public void shouldGetARandomRaceAndSetItInTheNewBug(final TestContext context) {
    final BugRace race = new BugRace();
    race.setBugRaceId(RACE_ID);
    final List<BugRace> races = Arrays.asList(race);
    when(racesRepository.findAll()).thenReturn(Future.succeededFuture(races));
    when(randomizer.getOneRandomly(races)).thenReturn(race);

    final Async async = context.async();
    service.generate().setHandler(result -> {
      if (result.succeeded()) {
        context.assertEquals(RACE_ID, result.result().getBugRaceId());
        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

  @Test
  public void shouldSetTheRandomPartsGeneratedByTheRaceInTheNewBug(final TestContext context) {
    final List<BugSelectedPart> parts = Arrays.asList(new BugSelectedPart());
    final BugRace race = mock(BugRace.class);
    when(race.generate(randomizer)).thenReturn(parts);
    final List<BugRace> races = Arrays.asList(race);
    when(racesRepository.findAll()).thenReturn(Future.succeededFuture(races));
    when(randomizer.getOneRandomly(races)).thenReturn(race);

    final Async async = context.async();
    service.generate().setHandler(result -> {
      if (result.succeeded()) {
        context.assertEquals(parts, result.result().getParts());
        async.complete();
      } else {
        context.fail(result.cause());
      }
    });
  }

}
