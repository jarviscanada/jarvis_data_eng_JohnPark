package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * Big-O: push - O(n), pop - O(1), top - O(1), empty - O(1)
 * Justification: Every push involves pouring all contents from s1 to s2 then push the new element
 * to s1 and pour the all contents back to s1 from s2. This is about iteration of 2n. Thus, O(n)
 * push time
 * Everything else is just constant execution time
 */
public class QueueUsingStacks1 {

  private Stack<Integer> s1 = new Stack<>();
  private Stack<Integer> s2 = new Stack<>();

  public void push(int x) {
    while (!s1.empty()) {
      s2.push(s1.pop());
    }
    s1.push(x);
    while (!s2.empty()) {
      s1.push(s2.pop());
    }
  }

  public int pop() {
    return s1.pop();
  }

  public int peek() {
    return s1.peek();
  }

  public boolean empty() {
    return s1.empty() && s2.empty();
  }
}
