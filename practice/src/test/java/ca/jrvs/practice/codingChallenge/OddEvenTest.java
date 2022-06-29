package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class OddEvenTest {
  OddEven oddEven = new OddEven();;

  @Test
  public void oddEvenMod() {
    String testOdd = oddEven.oddEvenMod(1);
    assertEquals("odd", testOdd);
    String testEven = oddEven.oddEvenMod(2);
    assertEquals("even", testEven);
  }

  @Test
  public void oddEvenBit() {
    String testOdd = oddEven.oddEvenBit(1);
    assertEquals("odd", testOdd);
    String testEven = oddEven.oddEvenBit(2);
    assertEquals("even", testEven);
  }
}