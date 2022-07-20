package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveElementTest {
  RemoveElement removeElement = new RemoveElement();

  @Test
  public void removeElement() {
    int[] array1 = new int[]{3, 2, 2, 3};
    int[] array2 = new int[]{0,1,2,2,3,0,4,2};

    assertEquals(2, removeElement.removeElement(array1, 2));
    assertNotEquals(2, array1[0]);
    assertNotEquals(2, array1[1]);
    assertEquals(5, removeElement.removeElement(array2, 2));
    assertNotEquals(2, array2[0]);
    assertNotEquals(2, array2[1]);
    assertNotEquals(2, array2[2]);
    assertNotEquals(2, array2[3]);
    assertNotEquals(2, array2[4]);
  }
}