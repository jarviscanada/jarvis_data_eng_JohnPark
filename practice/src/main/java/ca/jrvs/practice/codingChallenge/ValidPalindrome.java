package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-2d8ca777c6b24c8382596b4fe53febf4
 */
public class ValidPalindrome {

  /**
   * Big-O: O(n)
   * Justification: The two pointers iterates the string at most once. Therefore, O(n)
   */
  public boolean validPalindromeTwoPointers(String s) {
    int high = s.length() - 1;
    int low = 0;

    while (high > low) {

      while (high > low && !Character.isLetterOrDigit(s.charAt(low))) {
        low++;
      }
      while (high > low && !Character.isLetterOrDigit(s.charAt(high))) {
        high--;
      }

      if (Character.toLowerCase(s.charAt(high)) != Character.toLowerCase(s.charAt(low))) {
        return false;
      }
      high--;
      low++;
    }

    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: There will be at most n recursion will be called because we call a recursion on
   * substring of s which its length is at most smaller by 1. However, this calls many recursion stacks
   * Therefore, the space complexity is O(n) - not preferred.
   */
  public boolean validPalindromeRecursion(String s) {
    if (s.length() <= 1) {
      return true;
    }

    if (!Character.isLetterOrDigit(s.charAt(0))) {
      return validPalindromeRecursion(s.substring(1));
    }
    if (!Character.isLetterOrDigit(s.charAt(s.length() - 1))) {
      return validPalindromeRecursion(s.substring(0, s.length() - 1));
    }

    return Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(s.charAt(s.length() - 1))
        && validPalindromeRecursion(s.substring(1, s.length() - 1));

  }
}
