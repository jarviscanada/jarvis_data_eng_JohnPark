package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotateStringTest {
  RotateString rotateString = new RotateString();

  @Test
  public void rotateString() {
    assertTrue(rotateString.rotateString("abcde", "cdeab"));
    assertTrue(rotateString.rotateString("trevor", "vortre"));
    assertFalse(rotateString.rotateString("abcde", "abced"));
    assertFalse(rotateString.rotateString("abcde", "xxxxx"));


  }
}