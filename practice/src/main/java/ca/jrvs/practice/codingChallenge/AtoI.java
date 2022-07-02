package ca.jrvs.practice.codingChallenge;

public class AtoI {

  /**
   * Time Complexity: O(n)
   * Justification: Internally, Integer.parseInt() will at the bare minimum need to read every character of the given
   * String to determine int value of it. Thus it has a linear time complexity.
   */
  public int AtoI1(String s) {
    try {
      return Integer.parseInt(s);
    } catch(NumberFormatException e) {
      throw new RuntimeException("Provided string is not an integer", e);
    }
  }

  /**
   * Time Complexity: O(n)
   * Justification: The while loop at line 41 - 50 iterates at most n times and all statements inside
   * the while loop is constant time execution. Every other line only costs constant time.
   */
  public int AtoI2(String s) {
    s = s.replaceAll("^\\s", "");
    if (s.length() == 0) {
      return 0;
    }
    int n = s.length();

    int i = 0;
    boolean isPositive = true;

    if (s.charAt(0) == '-') {
      isPositive = false;
      i++;
    } else if (s.charAt(0) == '+') {
      i++;
    }

    int result = 0;

    while (i < n && Character.isDigit(s.charAt(i))) {
      int digit = s.charAt(i) - '0';

      if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && digit > 7)) {
        return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }

      result = (result * 10) + digit;
      i++;
    }
    return isPositive ? result : -1 * result;
  }
}
