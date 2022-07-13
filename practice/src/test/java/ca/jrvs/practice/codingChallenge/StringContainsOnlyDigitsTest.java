package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringContainsOnlyDigitsTest {
  StringContainsOnlyDigits stringContainsOnlyDigits = new StringContainsOnlyDigits();

  @Test
  public void usingASCII() {
    assertTrue(stringContainsOnlyDigits.usingASCII("1234"));
    assertFalse(stringContainsOnlyDigits.usingASCII("123,000"));
  }

  @Test
  public void usingJavaAPI() {
    assertTrue(stringContainsOnlyDigits.usingJavaAPI("1234"));
    assertFalse(stringContainsOnlyDigits.usingJavaAPI("123,000"));
  }

  @Test
  public void usingRegex() {
    assertTrue(stringContainsOnlyDigits.usingRegex("1234"));
    assertFalse(stringContainsOnlyDigits.usingRegex("123,000"));
  }
}