package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class IsAnagramTest {
  IsAnagram isAnagram = new IsAnagram();

  @Test
  public void isAnagramSort() {
    assertTrue(isAnagram.isAnagramSort("bab", "abb"));
    assertTrue(isAnagram.isAnagramSort("anagram", "nagaram"));
    assertFalse(isAnagram.isAnagramSort("notanagram", "notanngram"));
    assertFalse(isAnagram.isAnagramSort("thing", "pew"));
  }

  @Test
  public void isAnagramCounter() {
    assertTrue(isAnagram.isAnagramCounter("bab", "abb"));
    assertTrue(isAnagram.isAnagramCounter("anagram", "nagaram"));
    assertFalse(isAnagram.isAnagramCounter("notanagram", "notanngram"));
    assertFalse(isAnagram.isAnagramCounter("thing", "pew"));
  }
}