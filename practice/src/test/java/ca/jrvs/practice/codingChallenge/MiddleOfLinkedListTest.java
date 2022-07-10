package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.MiddleOfLinkedList.ListNode;
import org.junit.Test;

public class MiddleOfLinkedListTest {
  MiddleOfLinkedList middleOfLinkedList = new MiddleOfLinkedList();

  @Test
  public void middleOfLinkedList() {
    ListNode head1 = new ListNode(3);
    head1.next = new ListNode(2);
    head1.next.next = new ListNode(1);

    ListNode mid1 = middleOfLinkedList.middleOfLinkedList(head1);

    for (int i=2; i >= 1; i--) {
      assertEquals(i, mid1.val);
      mid1 = mid1.next;
    }

    ListNode head2 = new ListNode(1);
    head2.next = new ListNode(2);
    head2.next.next = new ListNode(3);
    head2.next.next.next = new ListNode(4);
    head2.next.next.next.next = new ListNode(5);

    ListNode mid2 = middleOfLinkedList.middleOfLinkedList(head2);
    for (int i=3; i <= 5; i++) {
      assertEquals(i, mid2.val);
      mid2 = mid2.next;
    }

    ListNode head3 = new ListNode(1);
    head3.next = new ListNode(2);
    head3.next.next = new ListNode(3);
    head3.next.next.next = new ListNode(4);
    head3.next.next.next.next = new ListNode(5);
    head3.next.next.next.next.next = new ListNode(6);

    ListNode mid3 = middleOfLinkedList.middleOfLinkedList(head3);
    for (int i=4; i <= 6; i++) {
      assertEquals(i, mid3.val);
      mid3 = mid3.next;
    }


  }
}