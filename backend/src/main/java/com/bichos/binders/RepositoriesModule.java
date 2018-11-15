package com.bichos.binders;

import java.time.Clock;

import com.bichos.repositories.PlayersRepository;
import com.bichos.repositories.PlayersSessionsRepository;
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

}
