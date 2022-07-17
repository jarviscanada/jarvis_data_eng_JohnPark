package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountPrimesTest {
  CountPrimes countPrimes = new CountPrimes();

  @Test
  public void countPrimes() {
    assertEquals(4, countPrimes.countPrimes(10));
    assertEquals(8, countPrimes.countPrimes(20));
    assertEquals(10, countPrimes.countPrimes(30));

  }
}