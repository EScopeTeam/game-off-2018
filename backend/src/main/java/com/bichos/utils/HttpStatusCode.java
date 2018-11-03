package com.bichos.utils;

public enum HttpStatusCode {
  OK(200, "OK"),
  FOUND(302, "Found"),
  NOT_MODIFIED(304, "Not Modified"),
  BAD_REQUEST(400, "Bad Request"),
  UNAUTHORIZED(401, "Unauthorized"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not Found"),
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
  CONFLICT(409, "Conflict"),
  UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error");

  private final int statusCode;
  private final String statusMessage;

  HttpStatusCode(final int statusCode, final String statusMessage) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  @Override
  public String toString() {
    return statusCode + " " + statusMessage;
  }

}
