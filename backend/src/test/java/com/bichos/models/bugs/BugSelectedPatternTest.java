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

  private static final String HASH1 = "hash1.png";
  private static final String HASH2 = "hash2.png";
  private static final String HASH3 = "hash3.png";

  private static final int POSITION1 = 10;
  private static final int POSITION2 = 1;
  private static final int POSITION3 = 5;

  @Test
  public void shouldReturnTheHashOfAllTheImagesSorted() {
    final BugSelectedPattern pattern = new BugSelectedPattern();
    pattern.setImages(Arrays.asList(
        createImage(HASH1, POSITION1),
        createImage(HASH2, POSITION2),
        createImage(HASH3, POSITION3)));

    final List<String> hashes = pattern.hash();

    Assert.assertThat(hashes, hasSize(pattern.getImages().size()));
    Assert.assertThat(hashes, contains(
        HASH2,
        HASH3,
        HASH1));
  }

  private BugSelectedImage createImage(final String hash, final int position) {
    final BugSelectedImage result = mock(BugSelectedImage.class);
    when(result.hash()).thenReturn(hash);
    when(result.getPosition()).thenReturn(position);
    when(result.compareTo(any())).thenCallRealMethod();

    return result;
  }

}
