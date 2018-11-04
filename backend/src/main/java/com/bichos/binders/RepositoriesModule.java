package com.bichos.binders;

import com.bichos.repositories.PlayersRepository;
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

}
