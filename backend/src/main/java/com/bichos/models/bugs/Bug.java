package com.bichos.models.bugs;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.bichos.utils.Randomizer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bug {

  private String bugRaceId;

  private List<BugSelectedPart> parts = new ArrayList<>();

  public String hash() {
    String hashedParts = parts.stream()
        .flatMap(BugSelectedPart::flat)
        .sorted()
        .flatMap(p -> p.hashPattern().stream())
        .collect(Collectors.joining("|"));

    return Base64.getEncoder().encodeToString(hashedParts.getBytes(StandardCharsets.UTF_8));
  }

  public static Bug generate(List<BugRace> races) {
    BugRace race = Randomizer.getOneRandomly(races);

    Bug result = new Bug();
    result.setBugRaceId(race.getBugRaceId());
    result.setParts(race.generate());
    return result;
  }

}
