package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class DuplicatesFromSortedArrayTest {

  DuplicatesFromSortedArray duplicatesFromSortedArray = new DuplicatesFromSortedArray();

  @Test
  public void twoPointers() {
    int[] array1 = new int[]{1,1,2};
    int[] array2 = new int[]{0,0,1,1,1,2,2,3,3,4};

    assertEquals(2, duplicatesFromSortedArray.TwoPointers(array1));
    assertEquals(1, array1[0]);
    assertEquals(2, array1[1]);

    assertEquals(5, duplicatesFromSortedArray.TwoPointers(array2));
    assertEquals(0, array2[0]);
    assertEquals(1, array2[1]);
    assertEquals(2, array2[2]);
    assertEquals(3, array2[3]);
    assertEquals(4, array2[4]);


  }
}