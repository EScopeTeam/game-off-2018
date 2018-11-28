package com.bichos.binders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.List;
import java.util.Random;

import com.bichos.repositories.BugDictionaryRepository;
import com.bichos.repositories.BugRacesRepository;
import com.bichos.repositories.PlayersRepository;
import com.bichos.repositories.PlayersSessionsRepository;
import com.bichos.repositories.impl.BugColorsPalettesSqlRepository;
import com.bichos.repositories.impl.BugColorsSqlRepository;
import com.bichos.repositories.impl.BugDictionaryInMemoryRepository;
import com.bichos.repositories.impl.BugImagesSqlRepository;
import com.bichos.repositories.impl.BugPartsSqlRepository;
import com.bichos.repositories.impl.BugPatternsSqlRepository;
import com.bichos.repositories.impl.BugRacesSqlRepository;
import com.bichos.repositories.impl.PlayersSessionsSqlRepository;
import com.bichos.repositories.impl.PlayersSqlRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import io.vertx.ext.sql.SQLClient;

public class RepositoriesModule extends AbstractModule {

  @Provides
  @Singleton
  public PlayersRepository providePlayersRepository(final SQLClient client, final Clock clock) {
    return new PlayersSqlRepository(client, clock);
  }

  @Provides
  @Singleton
  public PlayersSessionsRepository providePlayersSessionsRepository(final SQLClient client) {
    return new PlayersSessionsSqlRepository(client);
  }

  @Provides
  @Singleton
  public BugRacesRepository provideBugRacesRepository(final SQLClient client) {
    final BugImagesSqlRepository imagesRepository = new BugImagesSqlRepository(client);
    final BugPatternsSqlRepository patternsRepository = new BugPatternsSqlRepository(client, imagesRepository);
    final BugPartsSqlRepository partsRepository = new BugPartsSqlRepository(client, patternsRepository);
    final BugColorsSqlRepository colorsRepository = new BugColorsSqlRepository(client);
    final BugColorsPalettesSqlRepository colorsPalettesRepository = new BugColorsPalettesSqlRepository(client, colorsRepository);

    return new BugRacesSqlRepository(client, partsRepository, colorsPalettesRepository);
  }

  @Provides
  @Singleton
  public BugDictionaryRepository provideDictionaryRepository() throws IOException {

    final File adjFile = Paths.get(".", "/src/main/resources/adj.txt").toFile();
    final File nounsFile = Paths.get(".", "/src/main/resources/nns.txt").toFile();

    final List<String> adjectives = Files.readAllLines(adjFile.toPath());
    final List<String> nouns = Files.readAllLines(nounsFile.toPath());

    return new BugDictionaryInMemoryRepository(adjectives, nouns, new Random());
  }

}
