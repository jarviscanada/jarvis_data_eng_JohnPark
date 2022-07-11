package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.LinkedListCycle.ListNode;
import org.junit.Test;

public class LinkedListCycleTest {
  LinkedListCycle linkedListCycle = new LinkedListCycle();

  @Test
  public void hasCycle() {
    ListNode head1 = new ListNode(1);
    head1.next = new ListNode(2);
    head1.next.next = new ListNode(3);
    head1.next.next.next = head1;

    assertTrue(linkedListCycle.hasCycle(head1));

    ListNode head2 = new ListNode(1);
    head2.next = new ListNode(2);
    head2.next.next = new ListNode(3);
    head2.next.next.next = new ListNode(4);

    assertFalse(linkedListCycle.hasCycle(head2));

    ListNode head3 = new ListNode(1);
    head3.next = new ListNode(2);
    head3.next.next = new ListNode(3);
    head3.next.next.next = new ListNode(4);
    head3.next.next.next.next = head3.next;

    assertTrue(linkedListCycle.hasCycle(head3));

  }
}