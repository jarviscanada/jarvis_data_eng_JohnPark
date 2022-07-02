package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  /**
   * Big-O: O(n^2)
   * Justification: There is two for loop, for each i, j iterates as many as nums.length - i.
   * Then, if n = length of nums, the two for loop itertes n-1, n-2, ..., 1 times
   * Addition of all means (n-1)*(n)/2 which is element of O(n^2). Everything else is O(1) complexity
   */
  public int[] twoSumBruteForce(int[] nums, int target) {
    for (int i = 0; i < nums.length-1; i++) {
      for (int j = i+1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i,j};
        }
      }
    }
    return null;
  }

  /**
   * Big-O: O(n)
   * Justification: Looping through the map only once. Only one for-loop, others are O(1) time execution
   */
  public int[] twoSumHashMap(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
      } else {
        map.put(nums[i], i);
      }
    }
    return null;
  }
}
