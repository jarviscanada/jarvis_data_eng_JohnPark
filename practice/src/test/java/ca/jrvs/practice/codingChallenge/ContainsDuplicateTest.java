package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainsDuplicateTest {
  ContainsDuplicate containsDuplicate = new ContainsDuplicate();

  @Test
  public void sorting() {
    int[] array1 = new int[]{3,2,2,1};
    int[] array2 = new int[]{1,1,1,3,3,4,3,2,4,2};
    int[] array3 = new int[]{1,2,3,4};

    assertTrue(containsDuplicate.sorting(array1));
    assertTrue(containsDuplicate.sorting(array2));
    assertFalse(containsDuplicate.sorting(array3));
  }

  @Test
  public void usingSet() {
    int[] array1 = new int[]{3,2,2,1};
    int[] array2 = new int[]{1,1,1,3,3,4,3,2,4,2};
    int[] array3 = new int[]{1,2,3,4};

    assertTrue(containsDuplicate.usingSet(array1));
    assertTrue(containsDuplicate.usingSet(array2));
    assertFalse(containsDuplicate.usingSet(array3));

  }
}