package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrintLetterWithNumberTest {
  PrintLetterWithNumber printLetterWithNumber = new PrintLetterWithNumber();

  @Test
  public void printLetterWithNumber() {
    assertEquals("a1b2c3e5e5", printLetterWithNumber.printLetterWithNumber("abcee"));
    assertEquals("a1b2c3 x24y25z26", printLetterWithNumber.printLetterWithNumber("abc xyz"));
    assertEquals("A27b2c3x24y25z26", printLetterWithNumber.printLetterWithNumber("Abcxyz"));

  }
}