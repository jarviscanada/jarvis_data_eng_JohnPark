package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class MissingNumberTest {
  MissingNumber missingNumber = new MissingNumber();

  @Test
  public void sumAllNumber() {
    int actual1 = missingNumber.sumAllNumber(new int[]{3, 0, 2});
    int actual2 = missingNumber.sumAllNumber(new int[]{0, 3, 1, 4, 5});

    assertEquals(1, actual1);
    assertEquals(2, actual2);

  }

  @Test
  public void usingSet() {
    int actual1 = missingNumber.usingSet(new int[]{3, 0, 2});
    int actual2 = missingNumber.usingSet(new int[]{0, 3, 1, 4, 5});

    assertEquals(1, actual1);
    assertEquals(2, actual2);
  }

  @Test
  public void gaussFormula() {
    int actual1 = missingNumber.gaussFormula(new int[]{3, 0, 2});
    int actual2 = missingNumber.gaussFormula(new int[]{0, 2, 1, 4, 5});

    assertEquals(1, actual1);
    assertEquals(3, actual2);
  }
}