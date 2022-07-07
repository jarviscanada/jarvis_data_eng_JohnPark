package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Anagram-d2a3ae21da1447afb08a5061b71ffb5c
 */
public class IsAnagram {

  /**
   * Big-O: O(nlog(n))
   * Justification: Sorting costs O(nlog(n)). You sort twice and the equals of str1, str2 takes O(n)
   * Thus, in total the time complexity for this function is O(nlog(n))
   */
  public boolean isAnagramSort(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    Arrays.sort(str1);
    Arrays.sort(str2);
    return Arrays.equals(str1, str2);
  }

  /**
   * Big-O: O(n)
   * Justification: Generating a counter of 26 is constant time. For regular computer array of size
   * 26 is not significant. Thus, the for-loop of iterating counter is looping 26 times at max
   * this is not significant and takes only O(1). The for loop in lines 38-41 which loops through
   * the length of s and t depends on the input and it iterates once. Thus, this is linear and
   * the time complexity of this function is O(n).
   */
  public boolean isAnagramCounter(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] counter = new int[26];

    for (int i = 0; i < s.length(); i++) {
      counter[s.charAt(i) - 'a']++;
      counter[t.charAt(i) - 'a']--;
    }

    for (int count: counter) {
      if (count > 0) {
        return false;
      }
    }

    return true;
  }
}
