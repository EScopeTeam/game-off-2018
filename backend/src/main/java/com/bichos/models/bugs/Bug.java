package com.bichos.models.bugs;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bug {

  private static final String PARTS_SEPARATOR = "|";
  private static final String RACE_SEPARATOR = "/";

  private String bugRaceId;

  private List<BugSelectedPart> parts = new ArrayList<>();

  public String hash() {
    final String hashedParts = parts.stream()
        .flatMap(BugSelectedPart::flat)
        .sorted()
        .flatMap(p -> p.hashPattern().stream())
        .collect(Collectors.joining(PARTS_SEPARATOR));
    final String data = bugRaceId + RACE_SEPARATOR + hashedParts;

    return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
  }

}
