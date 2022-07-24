package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortedArrayTest {
  MergeSortedArray mergeSortedArray = new MergeSortedArray();
  @Test
  public void merge() {
    int[] nums1 = new int[]{1,2,3,0,0,0};
    int[] nums2 = new int[]{2,5,6};
    int[] nums3 = new int[]{2,3,7,0,0};
    int[] nums4 = new int[]{1,2};

    mergeSortedArray.merge(nums1, 3, nums2, nums2.length);
    assertEquals(nums1[0], 1);
    assertEquals(nums1[1], 2);
    assertEquals(nums1[2], 2);
    assertEquals(nums1[3], 3);
    assertEquals(nums1[4], 5);
    assertEquals(nums1[5], 6);

    mergeSortedArray.merge(nums3, 3, nums4, nums4.length);
    assertEquals(nums3[0], 1);
    assertEquals(nums3[1], 2);
    assertEquals(nums3[2], 2);
    assertEquals(nums3[3], 3);
    assertEquals(nums3[4], 7);

  }
}