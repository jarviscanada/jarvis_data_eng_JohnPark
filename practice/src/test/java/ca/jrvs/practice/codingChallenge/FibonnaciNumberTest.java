package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonnaciNumberTest {
  FibonnaciNumber fibonnaciNumber = new FibonnaciNumber();

  @Test
  public void fibonnaciNumberRecursive() {
    int actual = fibonnaciNumber.fibonnaciNumberRecursive(3);
    assertEquals(2, actual);
    actual = fibonnaciNumber.fibonnaciNumberRecursive(6);
    assertEquals(8, actual);
  }

  @Test
  public void fibonacciNumberRecursive() {
    int actual = fibonnaciNumber.fibonacciNumberIterative(3);
    assertEquals(2, actual);
    actual = fibonnaciNumber.fibonacciNumberIterative(6);
    assertEquals(8, actual);
  }
}