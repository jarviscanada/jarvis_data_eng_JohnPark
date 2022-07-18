package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Big-O: O(n)
 * Justification: Iterates s once and iterates the HashMap which maps each character of s to its
 * occurring number. This is max 2n iteration. Thus O(n)
 */
public class DuplicateCharacters {
  public ArrayList<Character> duplicateChars(String s) {
    HashMap<Character, Integer> charCounter = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ' ') {
        if (charCounter.containsKey(s.charAt(i))) {
          charCounter.put(s.charAt(i), charCounter.get(s.charAt(i)) + 1);
        } else {
          charCounter.put(s.charAt(i), 1);
        }
      }
    }

    ArrayList<Character> duplicatedCharacters= new ArrayList<>();

    for (Character key : charCounter.keySet()) {
      if (charCounter.get(key) == 2) {
        duplicatedCharacters.add(key);
      }
    }
    return duplicatedCharacters;
  }
}
