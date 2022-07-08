package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Test;

public class DuplicateLinkedListNodeTest {
  DuplicateLinkedListNode duplicateLinkedListNode = new DuplicateLinkedListNode();
  @Test
  public void duplicateLinkedListNode() {
    LinkedJList<Integer> list1 = new LinkedJList<>();
    LinkedJList<Integer> list2 = new LinkedJList<>();

    list1.add(1);
    list1.add(2);
    list1.add(2);
    list1.add(3);

    LinkedJList<Integer> actualResult1 = duplicateLinkedListNode.duplicateLinkedListNode(list1);

    assertEquals(actualResult1.get(0), (Integer) 1);
    assertEquals(actualResult1.get(1), (Integer) 3);


    list2.add(1);
    list2.add(3);
    list2.add(4);
    list2.add(4);
    list2.add(5);
    list2.add(13);
    list2.add(2);
    list2.add(10);

    LinkedJList<Integer> actualResult2 = duplicateLinkedListNode.duplicateLinkedListNode(list2);


    assertEquals(actualResult2.get(0), (Integer) 1);
    assertEquals(actualResult2.get(1), (Integer) 3);
    assertEquals(actualResult2.get(2), (Integer) 5);
    assertEquals(actualResult2.get(3), (Integer) 13);
    assertEquals(actualResult2.get(4), (Integer) 2);
    assertEquals(actualResult2.get(5), (Integer) 10);





  }
}