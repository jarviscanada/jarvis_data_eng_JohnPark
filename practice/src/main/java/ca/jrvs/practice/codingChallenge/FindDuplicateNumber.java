package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-a66f24896bc449dbb8eadcd0d9a9ac76
 */
public class FindDuplicateNumber {

  /**
   * Big-O: O(nlog(n))
   * Justification: The sorting cost O(nlog(n)). Then the code iterates n times. Thus, it is around
   * O(nlog(n) + n) = O(nlog(n))
   */
  public int sorting(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i-1]) {
        return nums[i];
      }
    }
    return -1;
  }

  /**
   * Big-O: O(n)
   * Justification: Iterates the input once. Therefore, O(n)
   */
  public int usingSet(int[] nums) {
    Set<Integer> seen = new HashSet<Integer>();
    for (int num : nums) {
      if (seen.contains(num)) {
        return num;
      }
      seen.add(num);
    }
    return -1;
  }
}
