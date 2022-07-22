package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Contains-Duplicate-3295353a471047f68c4e17fedc827a5b
 */
public class DuplicatesFromSortedArray {

  /**
   * Big-O: O(n)
   * Justification: Iterates the array nums once
   */
  public int TwoPointers(int[] nums) {
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      if (nums[i] != nums[j]) {
        i++;
        nums[i] = nums[j];
      }
    }
    return i + 1;
  }
}
