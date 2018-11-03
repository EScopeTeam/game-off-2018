package com.bichos.verticles;

import com.bichos.binders.ConfigModule;
import com.bichos.binders.HandlersModule;
import com.bichos.binders.RepositoriesModule;
import com.bichos.binders.ServicesModule;
import com.bichos.handlers.authentication.AuthenticationHandler;
import com.bichos.handlers.authentication.LoginHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiVerticle extends AbstractVerticle {
	
	private static final int LOGIN_ROUTE_HANDLER_ORDER = 0;
	private static final int AUTHENTICATION_ROUTE_HANDLER_ORDER = 1;
	//private static final int GENERAL_ROUTE_HANDLER_ORDER = 2;
	
	private static final String KEY_PORT = "port";
	private static final int DEFAULT_PORT = 8080;
	
	private Injector injector; 

	@Override
	public void start(Future<Void> fStart) {
		initializeGuice();
		
		Router router = createRouter();
		
		int port = config().getInteger(KEY_PORT, DEFAULT_PORT);
		HttpServer server = vertx.createHttpServer();
		server
			.requestHandler(router::accept)
			.listen(
					port, 
					(serverResult) -> handleServerStart(port, serverResult, fStart));
	}
	
	private void initializeGuice() {
		injector = Guice.createInjector(
				new ConfigModule(vertx, config()),  
				new RepositoriesModule(), 
				new ServicesModule(),
				new HandlersModule());
	}
	
	private void handleServerStart(int port, AsyncResult<HttpServer> serverResult, Future<Void> fStart) {
		if(serverResult.succeeded()) {
			log.info("HttpServer started on port " + port);
			fStart.complete();
		} else {
			fStart.fail(serverResult.cause());
		}
	}
	
	private Router createRouter() {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.post("/login").order(LOGIN_ROUTE_HANDLER_ORDER).handler(injector.getInstance(LoginHandler.class));
		router.route().order(AUTHENTICATION_ROUTE_HANDLER_ORDER).handler(injector.getInstance(AuthenticationHandler.class));
		
		return router;
	}
	
}
