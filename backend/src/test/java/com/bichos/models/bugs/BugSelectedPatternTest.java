package com.bichos.models.bugs;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BugSelectedPatternTest {

  private static final String HASH_SEPARATOR = "-";

  private static final String PATTERN_ID = "pa1";

  private static final String HASH1 = "hash1";
  private static final String HASH2 = "hash2";
  private static final String HASH3 = "hash3";

  private static final int POSITION1 = 10;
  private static final int POSITION2 = 1;
  private static final int POSITION3 = 5;

  @Test
  public void shouldReturnTheHashOfAllTheImagesSortedAndPrependedByThePatternId() {
    BugSelectedPattern pattern = new BugSelectedPattern();
    pattern.setBugPatternId(PATTERN_ID);
    pattern.setImages(Arrays.asList(
        createImage(HASH1, POSITION1),
        createImage(HASH2, POSITION2),
        createImage(HASH3, POSITION3)));

    List<String> hashes = pattern.hash();

    Assert.assertThat(hashes, hasSize(pattern.getImages().size()));
    Assert.assertThat(hashes, contains(
        PATTERN_ID + HASH_SEPARATOR + HASH2,
        PATTERN_ID + HASH_SEPARATOR + HASH3,
        PATTERN_ID + HASH_SEPARATOR + HASH1));
  }

  private BugSelectedImage createImage(String hash, int position) {
    BugSelectedImage result = mock(BugSelectedImage.class);
    when(result.hash()).thenReturn(hash);
    when(result.getPosition()).thenReturn(position);
    when(result.compareTo(any())).thenCallRealMethod();

    return result;
  }

}
