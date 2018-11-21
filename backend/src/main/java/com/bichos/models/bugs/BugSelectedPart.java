package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugSelectedPart extends BaseBugPart {

  private List<BugSelectedPart> relatedParts = new ArrayList<>();

  private BugSelectedPattern pattern;

  public List<String> hashPattern() {
    return pattern.hash();
  }

  public Stream<BugSelectedPart> flat() {
    return Stream.concat(Stream.of(this), relatedParts.stream().flatMap(BugSelectedPart::flat));
  }

}
