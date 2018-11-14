package com.bichos.services.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bichos.models.bugs.Bug;
import com.bichos.models.bugs.BugRace;
import com.bichos.models.bugs.BugSelectedPart;
import com.bichos.repositories.BugRacesRepository;
import com.bichos.utils.Randomizer;

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
  public void shouldGetARandomRaceAndSetItInTheNewBug() {
    final BugRace race = new BugRace();
    race.setBugRaceId(RACE_ID);
    final List<BugRace> races = Arrays.asList(race);
    when(racesRepository.findAllRaces()).thenReturn(races);
    when(randomizer.getOneRandomly(races)).thenReturn(race);

    final Bug bug = service.generate();
    Assert.assertEquals(RACE_ID, bug.getBugRaceId());
  }

  @Test
  public void shouldSetTheRandomPartsGeneratedByTheRaceInTheNewBug() {
    final List<BugSelectedPart> parts = Arrays.asList(new BugSelectedPart());
    final BugRace race = mock(BugRace.class);
    when(race.generate(randomizer)).thenReturn(parts);
    final List<BugRace> races = Arrays.asList(race);
    when(racesRepository.findAllRaces()).thenReturn(races);
    when(randomizer.getOneRandomly(races)).thenReturn(race);

    final Bug bug = service.generate();
    Assert.assertEquals(parts, bug.getParts());
  }

}
