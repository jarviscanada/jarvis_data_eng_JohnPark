package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Rotate-String-66eea3a38d69443389a5c062f8091df4
 */
public class RotateString {

  /**
   * Big-O: O(n^2)
   * Justification: When comparing two string, finding the location at which it contains a string
   * takes O(n) and checking that each characters of that length matches together will take O(n).
   * Thus, O(n^2).
   */
  public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length())
        return false;
    String duplicateS = s + s;

    return duplicateS.contains(goal);
  }
}
