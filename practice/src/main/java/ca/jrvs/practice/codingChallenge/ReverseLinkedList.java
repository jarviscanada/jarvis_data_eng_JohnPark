package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Reverse-Linked-List-34d3519f1b204272930d7e7ef6806f2b
 */
public class ReverseLinkedList {

  /**
   * Big-O: O(n)
   * Justification: Iterates the linked list once, and changing the pointer
   */
  public ListNode reverseListIteration(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode tmp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = tmp;
    }
    return prev;
  }

  /**
   * Big-O: O(n)
   * Justification: Recursively called for each node. O(n)
   */
  public ListNode reverseListRecursive(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode p = reverseListRecursive(head.next);
    head.next.next = head;
    head.next = null;
    return p;
  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
