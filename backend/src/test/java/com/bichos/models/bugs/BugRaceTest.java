package com.bichos.models.bugs;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.bichos.utils.Randomizer;

public class BugRaceTest {

  private static final int BUG_POSITION_1 = 10;
  private static final int BUG_POSITION_2 = 1;
  private static final int BUG_POSITION_3 = 5;

  private Randomizer randomizer;

  private BugRace race;

  @Before
  public void initialize() {
    randomizer = mock(Randomizer.class);

    race = new BugRace();
    race.setParts(Arrays.asList(
        createBugPart(BUG_POSITION_1),
        createBugPart(BUG_POSITION_2),
        createBugPart(BUG_POSITION_3)));
    race.setColorPalettes(Arrays.asList(
        createColorPalette(),
        createColorPalette()));
  }

  private BugPart createBugPart(int position) {
    BugPart result = mock(BugPart.class);
    when(result.getPosition()).thenReturn(position);
    when(result.generate(any(), any())).thenReturn(new BugSelectedPart());
    when(result.shouldBeGenerateRandomly(randomizer)).thenReturn(false);
    when(result.compareTo(any())).thenCallRealMethod();

    return result;
  }

  private BugColorPalette createColorPalette() {
    return new BugColorPalette();
  }

  @Test
  public void generateShouldReturnTheRequiredRandomlyPartsGenerated() {
    when(race.getParts().get(1).shouldBeGenerateRandomly(randomizer)).thenReturn(true);

    List<BugSelectedPart> selectedParts = race.generate(randomizer);
    Assert.assertThat(selectedParts, hasSize(1));
    Assert.assertThat(selectedParts, hasItem(race.getParts().get(1).generate(randomizer, null)));
  }

  @Test
  public void generateShouldGenerateThePartsWithARandomColorPalette() {
    when(race.getParts().get(0).shouldBeGenerateRandomly(randomizer)).thenReturn(true);
    when(randomizer.getOneRandomly(race.getColorPalettes())).thenReturn(race.getColorPalettes().get(1));

    race.generate(randomizer);

    ArgumentCaptor<BugColorPalette> argumentCaptor = ArgumentCaptor.forClass(BugColorPalette.class);
    verify(race.getParts().get(0), times(1)).generate(eq(randomizer), argumentCaptor.capture());

    Assert.assertEquals(race.getColorPalettes().get(1), argumentCaptor.getValue());
  }

  @Test
  public void generateShouldReturnThePartsSorted() {
    when(race.getParts().get(0).shouldBeGenerateRandomly(randomizer)).thenReturn(true);
    when(race.getParts().get(1).shouldBeGenerateRandomly(randomizer)).thenReturn(true);
    when(race.getParts().get(2).shouldBeGenerateRandomly(randomizer)).thenReturn(true);

    List<BugSelectedPart> selectedParts = race.generate(randomizer);
    Assert.assertThat(selectedParts, hasSize(race.getParts().size()));
    Assert.assertThat(selectedParts, contains(
        race.getParts().get(1).generate(randomizer, null),
        race.getParts().get(2).generate(randomizer, null),
        race.getParts().get(0).generate(randomizer, null)));
  }

}
