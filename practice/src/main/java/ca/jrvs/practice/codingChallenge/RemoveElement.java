package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Remove-Element-7104be6bce3644b284700556f4290881
 */
public class RemoveElement {

  /**
   * Big-O: O(n)
   * Justification: either last decreases from length of array to 0 or i increases to length of array
   * from 0. Everything else is constant time execution. Therefore, the code cannot exceed O(2n)
   * which is O(n)
   */
  public int removeElement(int[] nums, int val) {
    int last = nums.length - 1;
    int i = 0;
    while (i <= last) {
      if (nums[i] == val) {
        nums[i] = nums[last];
        last--;
      } else {
        i++;
      }
    }
    return last + 1;
  }
}
