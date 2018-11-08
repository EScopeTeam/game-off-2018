package com.bichos.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 7455906228417576515L;

  private final String method;

  private final String path;

}
