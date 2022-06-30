package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
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
