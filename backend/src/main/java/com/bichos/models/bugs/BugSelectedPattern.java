package com.bichos.models.bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugSelectedPattern extends BaseBugPattern {

  private List<BugSelectedImage> images = new ArrayList<>();

  public List<String> hash() {
    return images.stream()
        .sorted()
        .map(i -> getBugPatternId() + "-" + i.hash())
        .collect(Collectors.toList());
  }

}
