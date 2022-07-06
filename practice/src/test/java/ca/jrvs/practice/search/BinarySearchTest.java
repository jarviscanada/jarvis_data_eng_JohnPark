package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTest {
  BinarySearch binarySearch = new BinarySearch();
  Integer[] arr = new Integer[]{1,3,7,12,24,31,40,46,71,12,123,128,545,891};

  @Test
  public void binarySearchRecursion() {
    assertEquals((Integer) 6, binarySearch.binarySearchRecursion(arr, 40).get());
    assertEquals((Integer) 11, binarySearch.binarySearchRecursion(arr, 128).get());
    assertFalse(binarySearch.binarySearchRecursion(arr, -1).isPresent());
  }

  @Test
  public void binarySearchIteration() {
    assertEquals((Integer) 5, binarySearch.binarySearchIteration(arr, 31).get());
    assertEquals((Integer) 11, binarySearch.binarySearchIteration(arr, 128).get());
    assertFalse(binarySearch.binarySearchIteration(arr, -1).isPresent());
  }
}