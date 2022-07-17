package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * ticket: https://www.notion.so/jarvisdev/Missing-Number-bfa8edf22ddb4c33812fdb4f82ea680b
 */
public class MissingNumber {

  /**
   * Big-O: O(n)
   * Justification: The sum cost iterating all values and adding to it which is linear relative to
   * the input. Thus, O(n)
   */
  public int sumAllNumber(int[] nums) {
    return  IntStream.rangeClosed(0,nums.length).sum() - IntStream.of(nums).sum();
  }

  /**
   * Big-O: O(n)
   * Justification: The sum cost iterating all values and adding to it which is linear relative to
   * the input. Thus, O(n)
   */
  public int usingSet(int[] nums) {
    Set<Integer> numSet = new HashSet<>();
    for (int num : nums) numSet.add(num);


    for (int i = 0; i <= nums.length; i++) {
      if (!numSet.contains(i)) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Big-O: O(n)
   * Justification: The sum cost iterating all values and adding to it which is linear relative to
   * the input. Thus, O(n)
   */
  public int gaussFormula(int[] nums) {
    int gaussSum = ((nums.length)*(nums.length + 1)) / 2;
    return gaussSum - IntStream.of(nums).sum();
  }
}
