package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwapTwoNumbersTest {
  SwapTwoNumbers swapTwoNumbers = new SwapTwoNumbers();
  int[] arr1 = {2,3};
  int[] arr2 = {32, 45};

  @Test
  public void bitManipulation() {
    int[] actualResult1 = swapTwoNumbers.bitManipulation(arr1);
    assertEquals(3, actualResult1[0]);
    assertEquals(2, actualResult1[1]);

    int[] actualResult2 = swapTwoNumbers.bitManipulation(arr2);
    assertEquals(45, actualResult2[0]);
    assertEquals(32, actualResult2[1]);
  }

  @Test
  public void arithmeticManipulation() {
    int[] actualResult1 = swapTwoNumbers.arithmeticManipulation(arr1);
    assertEquals(3, actualResult1[0]);
    assertEquals(2, actualResult1[1]);

    int[] actualResult2 = swapTwoNumbers.arithmeticManipulation(arr2);
    assertEquals(45, actualResult2[0]);
    assertEquals(32, actualResult2[1]);
  }
}