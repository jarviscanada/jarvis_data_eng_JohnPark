package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class DuplicateCharactersTest {
  DuplicateCharacters duplicateCharacters = new DuplicateCharacters();

  @Test
  public void duplicateChars() {
    ArrayList<Character> actual1 = duplicateCharacters.duplicateChars("A black cat");
    assertEquals(2, actual1.size());
    assertTrue(actual1.contains('a'));
    assertTrue(actual1.contains('c'));

    ArrayList<Character> actual2 = duplicateCharacters.duplicateChars("ttrruucckk is a vehicle");
    assertEquals(6, actual2.size());
    assertTrue(actual2.contains('t'));
    assertTrue(actual2.contains('r'));
    assertTrue(actual2.contains('u'));
    assertTrue(actual2.contains('i'));
    assertTrue(actual2.contains('k'));
    assertTrue(actual2.contains('e'));



  }
}