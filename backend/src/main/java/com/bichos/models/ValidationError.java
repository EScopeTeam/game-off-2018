package com.bichos.models;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ValidationError implements Serializable {

  private static final long serialVersionUID = -6564048827737415781L;

  private final String attribute;

  private final String code;

  private final String description;

  private final Map<String, String> params;

  @Override
  public String toString() {
    return "attribute: " + attribute + ", code: " + code + ", description: " + description;
  }

}
