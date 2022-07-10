package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-e7e1cd5f9beb487fad08ac4ac7cbb7dd
 */
public class MiddleOfLinkedList {

  /**
   * Big-O: O(n)
   * Justification: The two pointers iterates the linkedlist at most n times. Everything else is
   * O(1) time execution time
   */
  public ListNode middleOfLinkedList(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
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
