package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-Largest-Smallest-a78d12c5c2a44d24bdaa98b151d887cd
 */
public class FindLargest {

  /**
   * Big-O: O(n)
   * Justification: The loop iterates the array once - approximately n times. Therefore, O(n)
   */
  public int findLargestLoop(ArrayList<Integer> arr) {
    int max = Integer.MIN_VALUE;
    for (int i : arr) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  /**
   * Big-O: O(n)
   * Justification: The Stream iterates the array once - approximately n times. Therefore, O(n)
   */
  public int findLargestStreamAPI(ArrayList<Integer> arr) {
    if (arr.size() == 0) {
      return Integer.MIN_VALUE;
    }
    return arr.stream().mapToInt(i->i).max().orElse(Integer.MIN_VALUE);
  }

  /**
   * Big-O: O(n)
   * Justification: By the source code
   */
  public int findLargestBuiltInAPI(ArrayList<Integer> arr) {

    return Collections.max(arr);
  }
}
