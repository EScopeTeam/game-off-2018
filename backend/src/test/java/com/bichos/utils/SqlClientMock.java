package com.bichos.utils;

import java.util.function.BiFunction;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLOperations;
import lombok.Setter;

@Setter
public class SqlClientMock implements SQLClient {

  private JsonArray resultQuerySingleWithParams;

  private Throwable exceptionQuerySingleWithParams;

  private ResultSet resultQueryWithParams;

  private BiFunction<String, JsonArray, ResultSet> funcQueryWithParams;

  private Throwable exceptionQueryWithParams;

  private ResultSet resultQuery;

  private Throwable exceptionQuery;

  @Override
  public SQLClient getConnection(final Handler<AsyncResult<SQLConnection>> handler) {
    return null;
  }

  @Override
  public void close(final Handler<AsyncResult<Void>> handler) {
    // Do nothing
  }

  @Override
  public void close() {
    // Do nothing
  }

  @Override
  public SQLOperations querySingleWithParams(final String sql, final JsonArray arguments, final Handler<AsyncResult<JsonArray>> handler) {
    if (exceptionQuerySingleWithParams == null) {
      handler.handle(Future.succeededFuture(resultQuerySingleWithParams));
    } else {
      handler.handle(Future.failedFuture(exceptionQuerySingleWithParams));
    }
    return null;
  }

  @Override
  public SQLClient queryWithParams(final String sql, final JsonArray arguments, final Handler<AsyncResult<ResultSet>> handler) {
    if (funcQueryWithParams != null) {
      handler.handle(Future.succeededFuture(funcQueryWithParams.apply(sql, arguments)));
    } else if (exceptionQuerySingleWithParams == null) {
      handler.handle(Future.succeededFuture(resultQueryWithParams));
    } else {
      handler.handle(Future.failedFuture(exceptionQueryWithParams));
    }
    return null;
  }

  @Override
  public SQLClient query(String sql, Handler<AsyncResult<ResultSet>> handler) {
    if (exceptionQuery == null) {
      handler.handle(Future.succeededFuture(resultQuery));
    } else {
      handler.handle(Future.failedFuture(exceptionQuery));
    }

    return null;
  }

}
