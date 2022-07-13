package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-59f0704e8b794fe6b122b02dedcbf9d9
 */
public class StringContainsOnlyDigits {

  /**
   * Big-O: O(n)
   * Justification: Iterating each character, thus O(n)
   */
  public boolean usingASCII(String s) {
    for (int i = 0; i < s.length(); i++) {
      if ((int) s.charAt(i) < 48 || (int)s.charAt(i) > 57) {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: Iterating each character, thus O(n)
   */
  public boolean usingJavaAPI(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (Integer.valueOf(s.charAt(i)) < 48 || Integer.valueOf(s.charAt(i)) > 57) {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: Iterating each character to check whether each character is a digit, thus O(n)
   */
  public boolean usingRegex(String s) {
    return s.matches("[0-9]+");
  }
}
