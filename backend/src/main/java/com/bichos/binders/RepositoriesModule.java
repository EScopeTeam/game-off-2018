package com.bichos.binders;

import com.bichos.repositories.PlayersRepository;
import com.bichos.repositories.impl.PlayersInMemoryRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class RepositoriesModule extends AbstractModule {

	@Provides
	@Singleton
	public PlayersRepository providePlayersRepository() {
		return new PlayersInMemoryRepository();
	}
	
}
