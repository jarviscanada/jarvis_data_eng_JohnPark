package ca.jrvs.practice.codingChallenge;

public class RemoveNthNodeFromEnd {

  public ListNode removeNthNodeFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }

    ListNode back = head;
    ListNode front = head;

    for (int i = 0; i < n + 1; i++) {
      if (front == null) {
        return head.next;
      }
      front = front.next;
    }

    while (front != null) {
      back = back.next;
      front = front.next;
    }

    back.next = back.next.next;

    return head;

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
