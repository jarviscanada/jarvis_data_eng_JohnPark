package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Big-O: push - O(n), pop - O(1), top - O(1), empty - O(1)
 * Justification: For push, we insert the element into the queue from the end, and take all the
 * elements out and re-insert them, making it act like a stack. For the other operations, it is
 * simple one line operation that only takes constant time.
 */
public class StackUsingOneQueue {
  private final Queue<Integer> queue;
  public StackUsingOneQueue() {
    queue = new LinkedList<Integer>();
  }

  public void push(int x) {
    queue.add(x);
    int size = queue.size();
    while (size > 1) {
      queue.add(queue.remove());
      size--;
    }
  }

  public Integer pop() {
    return queue.remove();
  }

  public Integer top() {
    return queue.size() == 0 ? null : queue.peek();
  }

  public boolean empty() {
    return queue.size() == 0;
  }
}
