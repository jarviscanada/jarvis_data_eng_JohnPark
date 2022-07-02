package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Big-O: push - O(1), pop - O(n), top - O(1), empty - O(1)
 * Justification: For pop(), we move every element in queue1 to queue2 except the last element.
 * Then, we return the last element and put back all the elements of queue2 to queue1. This cost
 * 2n iteration. Thus, O(n). Everything else is constant time execution - one line.
 */
public class StackUsingTwoQueue {
  private final Queue<Integer> queue1;
  private final Queue<Integer> queue2;
  private int top;


  public StackUsingTwoQueue() {
    this.queue1 = new LinkedList<Integer>();
    this.queue2 = new LinkedList<Integer>();
  }

  public void push(int x) {
    top = x;
    queue1.add(x);
  }

  public Integer pop() {
    int size = queue1.size();
    while (size > 1) {
      top = queue1.remove();
      queue2.add(top);
      size--;
    }
    while (queue2.size() > 0) {
      queue1.add(queue2.remove());
    }

    return queue1.remove();

  }

  public Integer top() {
    return top;
  }

  public boolean empty() {
    return queue1.size() == 0;
  }
}
