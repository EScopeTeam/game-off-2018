package com.bichos.services.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
public class BugGeneratorServiceImplTest {

  private static final String RACE_ID = "1";

  private Randomizer randomizer;

  private BugRacesRepository racesRepository;

  private BugGeneratorServiceImpl service;

  @Before
  public void initialize() {
    randomizer = mock(Randomizer.class);
    racesRepository = mock(BugRacesRepository.class);

    service = new BugGeneratorServiceImpl(randomizer, racesRepository);
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
