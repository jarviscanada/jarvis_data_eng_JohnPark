package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.internal.matchers.Find;

public class FindDuplicateNumberTest {

  FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();

  @Test
  public void sorting() {
    int[] input1 = new int[]{3, 1, 2, 1};
    int[] input2 = new int[]{10, 6, 4, 3, 2, 5, 1, 5, 7, 9, 8};

    assertEquals(1, findDuplicateNumber.sorting(input1));
    assertEquals(5, findDuplicateNumber.sorting(input2));

  }

  @Test
  public void usingSet() {
    int[] input1 = new int[]{3, 1, 2, 1};
    int[] input2 = new int[]{10, 6, 4, 3, 2, 5, 1, 5, 7, 9, 8};

    assertEquals(1, findDuplicateNumber.usingSet(input1));
    assertEquals(5, findDuplicateNumber.usingSet(input2));
  }
}