package com.bichos.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLOperations;
import lombok.Setter;

@Setter
public class SqlClientMock implements SQLClient {

  private JsonArray resultQuerySingleWithParams;

  private Throwable exceptionQuerySingleWithParams;

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

}
