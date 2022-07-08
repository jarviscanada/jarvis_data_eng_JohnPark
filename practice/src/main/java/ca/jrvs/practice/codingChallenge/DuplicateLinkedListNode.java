package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import ca.jrvs.practice.dataStructure.list.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-85df6f4ee7d74ff3a299813339887e59
 */
public class DuplicateLinkedListNode {

  /**
   * Big-O: O(n)
   * Justification: The function loops through linkedlist twice - 2n iteration. This is O(n).
   */
  public LinkedJList<Integer> duplicateLinkedListNode(LinkedJList<Integer> linkedJList) {

    Map<Integer, Integer> counter = new HashMap<>();
    Node<Integer> currNode = linkedJList.getHead();
    LinkedJList<Integer> result = new LinkedJList<>();

    while (currNode != null) {
      counter.put(currNode.item,counter.getOrDefault(currNode.item,0)+1);
      currNode=currNode.next;
    }

    currNode = linkedJList.getHead();

    for (int i = 0; i < linkedJList.size(); i++) {
      Integer curr = linkedJList.get(i);
      if (counter.get(curr) == 1) {
        result.add(curr);
      }
    }

    return result;
  }

}
