package ca.jrvs.practice.codingChallenge;

public class FindLargest {
  public int findLargestLoop(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int i : arr) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  public int findLargestStreamAPI(int[] arr) {
    return -1;
  }

  public 
}
