package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackUsingOneQueueTest {
  StackUsingOneQueue stackUsingOneQueue = new StackUsingOneQueue();

  @Test
  public void pushAndPop() {
    stackUsingOneQueue.push(3);
    assertEquals((Integer) 3, stackUsingOneQueue.pop());

    stackUsingOneQueue.push(3);
    stackUsingOneQueue.push(2);
    stackUsingOneQueue.push(1);

    assertEquals((Integer) 1, stackUsingOneQueue.pop());
    assertEquals((Integer) 2, stackUsingOneQueue.pop());
    assertEquals((Integer) 3, stackUsingOneQueue.pop());

  }

  @Test
  public void top() {
    stackUsingOneQueue.push(3);
    stackUsingOneQueue.push(2);
    stackUsingOneQueue.push(1);
    stackUsingOneQueue.push(4);

    assertEquals((Integer) 4, stackUsingOneQueue.top());
    stackUsingOneQueue.pop();
    stackUsingOneQueue.pop();
    stackUsingOneQueue.pop();
    stackUsingOneQueue.pop();

  }

  @Test
  public void empty() {
    assertTrue(stackUsingOneQueue.empty());
    stackUsingOneQueue.push(1);
    assertFalse(stackUsingOneQueue.empty());
  }
}