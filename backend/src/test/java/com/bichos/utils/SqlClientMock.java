package com.bichos.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLOperations;
import io.vertx.ext.sql.UpdateResult;
import lombok.Getter;
import lombok.Setter;

public class SqlClientMock implements SQLClient {

  @Setter
  private JsonArray resultQuerySingleWithParams;

  @Setter
  private Throwable exceptionQuerySingleWithParams;

  @Setter
  private UpdateResult resultUpdateWithParams;

  @Setter
  private Throwable exceptionUpdateWithParams;

  @Getter
  private JsonArray paramsUpdateWithParams;

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
  public SQLClient updateWithParams(final String sql, final JsonArray params, final Handler<AsyncResult<UpdateResult>> handler) {
    paramsUpdateWithParams = params;

    if (exceptionUpdateWithParams == null) {
      handler.handle(Future.succeededFuture(resultUpdateWithParams));
    } else {
      handler.handle(Future.failedFuture(exceptionUpdateWithParams));
    }
    return this;
  }

}
