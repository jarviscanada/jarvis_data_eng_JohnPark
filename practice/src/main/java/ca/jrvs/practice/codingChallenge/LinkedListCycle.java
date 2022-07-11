package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/LinkedList-Cycle-ad990226a0104baab8ba77fc9b5981d1
 */
public class LinkedListCycle {

  /**
   * Big-O: O(n)
   * Justification: If there is no cycle, fast will iterate at most n/2 and end. When there is cycle
   * fast will go through a cycle within n/2 iteration and slow will cycle in n iteration. Thus,
   * within 3 cycle, fast will catch upto slow and be compared to each other that is 3*n literation
   * which is O(n)
   */
  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }

    return false;
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
