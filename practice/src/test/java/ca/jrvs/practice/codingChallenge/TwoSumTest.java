package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {
  TwoSum twoSum = new TwoSum();
  int[] expectedResult1 = new int[]{0,1};
  int[] expectedResult2 = new int[]{1,2};
  int[] expectedResult3 = new int[]{0,1};

  @Test
  public void twoSumBruteForce() {
    int[] actualResult1 = twoSum.twoSumBruteForce(new int[]{2,7,11,15}, 9);
    int[] actualResult2 = twoSum.twoSumBruteForce(new int[]{3,2,4}, 6);
    int[] actualResult3 = twoSum.twoSumBruteForce(new int[]{3,3}, 6);

    Arrays.sort(actualResult1);
    Arrays.sort(actualResult2);
    Arrays.sort(actualResult3);


    Assert.assertArrayEquals(expectedResult1, actualResult1);
    Assert.assertArrayEquals(expectedResult2, actualResult2);
    Assert.assertArrayEquals(expectedResult3, actualResult3);
  }

  @Test
  public void twoSumHashMap() {
    int[] actualResult1 = twoSum.twoSumHashMap(new int[]{2,7,11,15}, 9);
    int[] actualResult2 = twoSum.twoSumHashMap(new int[]{3,2,4}, 6);
    int[] actualResult3 = twoSum.twoSumHashMap(new int[]{3,3}, 6);

    Arrays.sort(actualResult1);
    Arrays.sort(actualResult2);
    Arrays.sort(actualResult3);


    Assert.assertArrayEquals(expectedResult1, actualResult1);
    Assert.assertArrayEquals(expectedResult2, actualResult2);
    Assert.assertArrayEquals(expectedResult3, actualResult3);
  }
}