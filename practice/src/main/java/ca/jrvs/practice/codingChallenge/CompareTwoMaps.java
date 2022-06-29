package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-6d6abcf668fb46fb970953f70e2952ba
 */
public class CompareTwoMaps {

  /**
   * Time Complexity: O(n)
   * Justification: The way that Map.equals() works is by comparing keys and values using the
   * Object.equals() method. Also, note that hashcode() must be implemented to work properly.
   * It will eventually have to compare every single key-value node of the map. Thus, it iterates
   * n times
   */
  public <K, V> boolean compareMapsA1(Map<K, V> m1, Map<K, V> m2) {
    return m1.equals(m2);
  }

  /**
   * Time Complexity: O(n)
   * Justification: You are iterating each key from m1 and checking whether its corresponding values
   * are equal. This will cost O(n) time complexity - The for loop. Everything else takes only
   * constant time
   */
  public <K, V> boolean compareMapsA2(Map<K, V> m1, Map<K, V> m2) {
    if (m1 == m2) {
      return true;
    }
    if (m1.size() != m2.size()) {
      return false;
    }
    for (K key: m1.keySet()) {
      if (!m2.containsKey(key) || !m1.get(key).equals(m2.get(key))) {
        return false;
      }
    }
    return true;
  }
}
