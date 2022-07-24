package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Merge-Sorted-Array-3285178779a643f0926e7cf1cd942029
 */
public class MergeSortedArray {

  /**
   * Big-O: O(m+n)
   * Justification: Iterates the nums1 and nums2 at most once.
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m - 1;
    int p2 = n - 1;
    int curr = n + m - 1;

    while (curr >= 0) {
      if (p2 < 0) {
        return;
      }
      if (p1 >= 0 && nums1[p1] >= nums2[p2]) {
        nums1[curr] = nums1[p1--];
      } else {
        nums1[curr] = nums2[p2--];
      }
      curr--;
    }
  }
}
