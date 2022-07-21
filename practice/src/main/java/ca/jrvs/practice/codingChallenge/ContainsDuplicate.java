package ca.jrvs.practice.codingChallenge;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Contains-Duplicate-3295353a471047f68c4e17fedc827a5b
 */
public class ContainsDuplicate {

  /**
   * Big-O: O(nlog(n))
   * Justification: Sorting takes O(nlog(n)) and iterating the nums takes O(n). O(nlog(n) + n) = O(nlog(n))
   */
  public boolean sorting(int[] nums) {
    Arrays.sort(nums);

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i-1]) {
        return true;
      }
    }
    return false;
  }

  /**
   * Big-O: O(n)
   * Justification: Iterating the whole nums once and putting into set or checking if it is already
   * there it is O(n)
   */
  public boolean usingSet(int[] nums) {
    Set<Integer> seen = new HashSet<>();

    for (int num : nums) {
      if (seen.contains(num)) {
        return true;
      }
      else {
        seen.add(num);
      }
    }
    return false;
  }
}
