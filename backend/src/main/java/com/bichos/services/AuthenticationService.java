package com.bichos.services;

import io.vertx.core.Future;

public interface AuthenticationService {

	Future<String> login(String username, String password);
	
}
