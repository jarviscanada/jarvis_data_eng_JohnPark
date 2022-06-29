package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-4b6e763b333c45c7a94c510d2e4ac6a6
 */
public class OddEven {
  /**
   * Big-O: O(1)
   * Justification: it's an arithmetic operation
   */
  public String oddEvenMod(int i){
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Big-O: O(1)
   * Justification: It's a simple bitwise and (&)
   */
  public String oddEvenBit(int i){
    return (i & 1) == 0 ? "even" : "odd";
  }
}
