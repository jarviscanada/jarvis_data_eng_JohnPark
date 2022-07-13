package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class FindLargestTest {
  FindLargest findLargest = new FindLargest();

  @Test
  public void findLargestLoop() {
    ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(23, 4, 2, 7,5));

    assertEquals(5, findLargest.findLargestLoop(arr1));
    assertEquals(23, findLargest.findLargestLoop(arr2));
  }

  @Test
  public void findLargestStreamAPI() {
    ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(23, 4, 2, 7,5));

    assertEquals(5, findLargest.findLargestStreamAPI(arr1));
    assertEquals(23, findLargest.findLargestStreamAPI(arr2));
  }

  @Test
  public void findLargestBuiltInAPI() {
    ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(23, 4, 2, 7,5));

    assertEquals(5, findLargest.findLargestBuiltInAPI(arr1));
    assertEquals(23, findLargest.findLargestBuiltInAPI(arr2));
  }
}