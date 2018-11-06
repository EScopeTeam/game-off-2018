package com.bichos.utils;

import org.mockito.invocation.InvocationOnMock;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public final class HandlersUtils {

  private HandlersUtils() {
  }

  public static <T> T asyncResult(final InvocationOnMock invokation, final int handlerPosition, final T result) {
    final Handler<AsyncResult<T>> handler = invokation.getArgument(handlerPosition);
    handler.handle(Future.succeededFuture(result));

    return null;
  }

  public static <T> T asyncFailure(final InvocationOnMock invokation, final int handlerPosition, final Exception exception) {
    final Handler<AsyncResult<T>> handler = invokation.getArgument(handlerPosition);
    handler.handle(Future.failedFuture(exception));

    return null;
  }

}
