package com.bichos.binders;

import com.bichos.repositories.BugRacesRepository;
import com.bichos.repositories.PlayersRepository;
import com.bichos.repositories.PlayersSessionsRepository;
import com.bichos.repositories.impl.BugColorsPalettesSqlRepository;
import com.bichos.repositories.impl.BugColorsSqlRepository;
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
  public PlayersRepository providePlayersRepository(final SQLClient client) {
    return new PlayersSqlRepository(client);
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

}
