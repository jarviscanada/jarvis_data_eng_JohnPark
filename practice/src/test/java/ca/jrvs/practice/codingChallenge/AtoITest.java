package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtoITest {
  AtoI atoI = new AtoI();
  int expectedResult1 = 42;
  int expectedResult2 = 324;
  String sampleInput1 = "42";
  String sampleInput2 = "324";

  @Test
  public void atoI1() {
    int actualResult1 = atoI.AtoI1(sampleInput1);
    int actualResult2 = atoI.AtoI1(sampleInput2);
    assertEquals(expectedResult1, actualResult1);
    assertEquals(expectedResult2, actualResult2);
  }

  @Test
  public void atoI2() {
    int actualResult1 = atoI.AtoI2(sampleInput1);
    int actualResult2 = atoI.AtoI2(sampleInput2);
    assertEquals(expectedResult1, actualResult1);
    assertEquals(expectedResult2, actualResult2);
  }
}