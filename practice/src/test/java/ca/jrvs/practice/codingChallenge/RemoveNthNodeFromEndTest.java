package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.RemoveNthNodeFromEnd.ListNode;
import org.junit.Test;

public class RemoveNthNodeFromEndTest {
  RemoveNthNodeFromEnd removeNthNodeFromEnd = new RemoveNthNodeFromEnd();

  @Test
  public void removeNthNodeFromEnd() {
    ListNode head1 = new ListNode(3);
    head1.next = new ListNode(2);
    head1.next.next = new ListNode(1);

    removeNthNodeFromEnd.removeNthNodeFromEnd(head1, 2);

    assertEquals(head1.val, 3);
    assertEquals(head1.next.val, 1);

    ListNode head2 = new ListNode(1);
    head2.next = new ListNode(2);
    head2.next.next = new ListNode(3);
    head2.next.next.next = new ListNode(4);
    head2.next.next.next.next = new ListNode(5);

    removeNthNodeFromEnd.removeNthNodeFromEnd(head2, 2);


    ListNode curr = head2;
    for (int i = 1; i <= 5; i++) {
      if (i == 4) {
        continue;
      }
      assertEquals(i, curr.val);
      curr = curr.next;
    }



  }
}