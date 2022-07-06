package ca.jrvs.practice.search;

import java.util.Arrays;
import java.util.Optional;

public class BinarySearch {

  /**
   * find the the target index in a sorted array
   *
   * @param arr    input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable<E>> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    if (arr == null || arr.length == 0) {
      return Optional.empty();
    }
    int mid = Math.floorDiv(arr.length, 2);

    if (arr[mid].compareTo(target) > 0) {
      return binarySearchRecursion(Arrays.copyOfRange(arr, 0, mid), target);
    } else if (arr[mid].compareTo(target) < 0) {
      Optional<Integer> result = binarySearchRecursion(Arrays.copyOfRange(arr, mid + 1, arr.length), target);
      return result.map(integer -> mid + integer + 1);
    } else if (arr[mid].compareTo(target) == 0) {
      return Optional.of(mid);
    } else {
      return Optional.empty();
    }
  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr    input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable<E>> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    if (arr == null || arr.length == 0) {
      return Optional.empty();
    }

    int high = arr.length - 1;
    int low = 0;
    int mid = Math.floorDiv(arr.length, 2);

    while (high > low) {
      if (arr[mid].compareTo(target) == 0) {
        return Optional.of(mid);
      } else if (arr[mid].compareTo(target) < 0) {
        low = mid + 1;
      } else {
        high = mid;
      }
      mid = Math.floorDiv(high + low, 2);
    }
    return Optional.empty();
  }
}