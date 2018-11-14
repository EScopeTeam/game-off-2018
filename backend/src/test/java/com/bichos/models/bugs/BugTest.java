package com.bichos.models.bugs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BugTest {

  private static final String PART_SEPARATION = "|";

  private static final int BUG_PART_POSITION1 = 10;
  private static final int BUG_PART_POSITION1B = 2;
  private static final int BUG_PART_POSITION2 = 1;
  private static final int BUG_PART_POSITION3 = 5;

  private static final String BUG_PART_HASH1 = "hash1";
  private static final String BUG_PART_HASH1B = "hash1b";
  private static final String BUG_PART_HASH2 = "hash2";
  private static final String BUG_PART_HASH3 = "hash3";

  private Bug bug;

  @Before
  public void initialize() {
    bug = new Bug();
    bug.setParts(Arrays.asList(
        createBugPart(BUG_PART_HASH1, BUG_PART_POSITION1, BUG_PART_HASH1B, BUG_PART_POSITION1B),
        createBugPart(BUG_PART_HASH2, BUG_PART_POSITION2),
        createBugPart(BUG_PART_HASH3, BUG_PART_POSITION3)));
  }

  private BugSelectedPart createBugPart(String hash, int position) {
    return createBugPart(hash, position, null, null);
  }

  private BugSelectedPart createBugPart(String hash, int position, String hashB, Integer positionB) {
    BugSelectedPart part = mock(BugSelectedPart.class);
    when(part.hashPattern()).thenReturn(Arrays.asList(hash));
    when(part.getPosition()).thenReturn(position);
    when(part.compareTo(any())).thenCallRealMethod();

    if (hashB == null) {
      when(part.flat()).thenReturn(Stream.of(part));
    } else {
      final BugSelectedPart otherPart = createBugPart(hashB, positionB);
      when(part.flat()).thenReturn(Stream.of(part, otherPart));
    }

    return part;
  }

  @Test
  public void hashReturnsTheHashedImagesOfAllTheBugPartsInOrder() {
    String hash = bug.hash();
    String decodedHash = new String(Base64.getDecoder().decode(hash), StandardCharsets.UTF_8);

    Assert.assertEquals(
        BUG_PART_HASH2 + PART_SEPARATION + BUG_PART_HASH1B + PART_SEPARATION + BUG_PART_HASH3 + PART_SEPARATION + BUG_PART_HASH1,
        decodedHash);
  }

}
