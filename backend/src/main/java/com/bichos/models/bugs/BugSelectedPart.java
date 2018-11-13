package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugSelectedPart implements Comparable<BugSelectedPart> {

  private String bugPartId;

  private int position;

  private List<BugSelectedPart> relatedParts = new ArrayList<>();

  private BugSelectedPattern pattern;

  public List<String> hashPattern() {
    return pattern.hash().stream().map(s -> bugPartId + "-" + s).collect(Collectors.toList());
  }

  public Stream<BugSelectedPart> flat() {
    return Stream.concat(Stream.of(this), relatedParts.stream().flatMap(BugSelectedPart::flat));
  }

  @Override
  public int compareTo(BugSelectedPart o) {
    return Integer.compare(position, o.position);
  }

}
