package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPalindromeTest {

  ValidPalindrome validPalindrome = new ValidPalindrome();

  @Test
  public void validPalindromeTwoPointers() {
    assertTrue(validPalindrome.validPalindromeTwoPointers("aba"));
    assertTrue(validPalindrome.validPalindromeTwoPointers("dddaabaaddd"));
    assertTrue(validPalindrome.validPalindromeTwoPointers("hello:a:olleh"));
    assertFalse(validPalindrome.validPalindromeTwoPointers("ddabaaddd"));
    assertFalse(validPalindrome.validPalindromeTwoPointers("ab"));
  }

  @Test
  public void validPalindromeRecursion() {
    assertTrue(validPalindrome.validPalindromeRecursion("aba"));
    assertTrue(validPalindrome.validPalindromeRecursion("dddaabaaddd"));
    assertTrue(validPalindrome.validPalindromeRecursion("hello:a:olleh"));
    assertFalse(validPalindrome.validPalindromeRecursion("ddabaaddd"));
    assertFalse(validPalindrome.validPalindromeRecursion("ab"));
  }
}