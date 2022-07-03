package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * Big-O: push - O(1), pop - Amortized O(1) per operation., top - O(1), empty - O(1)
 * Justification: Everything else is constant execution time. For pop, it is Amortized O(1) because
 * all contents from s1 is poured to s2 only when s2 is empty, thus there is not much pouring happening
 * over time. Thus, O(1) amortized.
 */
public class QueueUsingStacks2 {

  private Stack<Integer> s1 = new Stack<>();
  private Stack<Integer> s2 = new Stack<>();
  private int front;

  public void push(int x) {
    if (s1.isEmpty()) {
      front = x;
    }
    s1.add(x);
  }

  public int pop() {
    if (s2.isEmpty()) {
      while (!s1.isEmpty()) {
        s2.add(s1.pop());
      }
    }
    return s2.pop();
  }

  public int peek() {
    if (!s2.isEmpty()) {
      return s2.peek();
    }
    return front;
  }

  public boolean empty() {
    return s1.empty() && s2.empty();
  }
}
