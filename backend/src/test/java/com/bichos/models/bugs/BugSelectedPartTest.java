package com.bichos.models.bugs;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BugSelectedPartTest {

  private static final String HASH1 = "hash1.png";
  private static final String HASH2 = "hash2.png";

  @Test
  public void hashPatternShouldReturnAllTheResultsOfHasingThePattern() {
    final BugSelectedPattern pattern = mock(BugSelectedPattern.class);
    when(pattern.hash()).thenReturn(Arrays.asList(HASH1, HASH2));

    final BugSelectedPart part = new BugSelectedPart();
    part.setPattern(pattern);

    final List<String> hash = part.hashPattern();

    Assert.assertThat(hash, hasSize(2));
    Assert.assertThat(hash, contains(
        HASH1,
        HASH2));
  }

}
