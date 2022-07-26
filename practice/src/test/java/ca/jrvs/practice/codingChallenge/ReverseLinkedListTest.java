package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.ReverseLinkedList.ListNode;
import org.junit.Test;

public class ReverseLinkedListTest {
  ReverseLinkedList reverseLinkedList = new ReverseLinkedList();

  @Test
  public void reverseListIteration() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode result = reverseLinkedList.reverseListIteration(head);
    assertEquals(5, result.val);
    assertEquals(4, result.next.val);
    assertEquals(3, result.next.next.val);
    assertEquals(2, result.next.next.next.val);
    assertEquals(1, result.next.next.next.next.val);



  }

  @Test
  public void reverseListRecursive() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode result = reverseLinkedList.reverseListRecursive(head);
    assertEquals(5, result.val);
    assertEquals(4, result.next.val);
    assertEquals(3, result.next.next.val);
    assertEquals(2, result.next.next.next.val);
    assertEquals(1, result.next.next.next.next.val);
  }
}