package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

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
