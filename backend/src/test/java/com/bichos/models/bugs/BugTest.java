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

  private static final String RACE_SEPARATOR = "/";
  private static final String PART_SEPARATOR = "|";

  private static final String RACE_ID = "2";

  private static final int BUG_PART_POSITION1 = 10;
  private static final int BUG_PART_POSITION1B = 2;
  private static final int BUG_PART_POSITION2 = 1;
  private static final int BUG_PART_POSITION3 = 5;

  private static final String BUG_PART_HASH1 = "hash1.png";
  private static final String BUG_PART_HASH1B = "hash1b.png";
  private static final String BUG_PART_HASH2 = "hash2.png";
  private static final String BUG_PART_HASH3 = "hash3.png";

  private Bug bug;

  @Before
  public void initialize() {
    bug = new Bug();
    bug.setBugRaceId(RACE_ID);
    bug.setParts(Arrays.asList(
        createBugPart(BUG_PART_HASH1, BUG_PART_POSITION1, BUG_PART_HASH1B, BUG_PART_POSITION1B),
        createBugPart(BUG_PART_HASH2, BUG_PART_POSITION2),
        createBugPart(BUG_PART_HASH3, BUG_PART_POSITION3)));
  }

  private BugSelectedPart createBugPart(final String hash, final int position) {
    return createBugPart(hash, position, null, null);
  }

  private BugSelectedPart createBugPart(final String hash, final int position, final String hashB, final Integer positionB) {
    final BugSelectedPart part = mock(BugSelectedPart.class);
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
  public void hashReturnsTheHashedImagesOfAllTheBugPartsInOrderAndPrependedByTheRaceId() {
    final String hash = bug.hash();
    final String decodedHash = new String(Base64.getDecoder().decode(hash), StandardCharsets.UTF_8);

    Assert.assertEquals(
        RACE_ID + RACE_SEPARATOR + BUG_PART_HASH2 + PART_SEPARATOR + BUG_PART_HASH1B + PART_SEPARATOR + BUG_PART_HASH3 + PART_SEPARATOR + BUG_PART_HASH1,
        decodedHash);
  }

}
