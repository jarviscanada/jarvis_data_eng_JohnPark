package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackUsingTwoQueueTest {
  StackUsingTwoQueue stackUsingTwoQueue = new StackUsingTwoQueue();

  @Test
  public void pushAndPop() {
    stackUsingTwoQueue.push(3);
    assertEquals((Integer) 3, stackUsingTwoQueue.pop());

    stackUsingTwoQueue.push(3);
    stackUsingTwoQueue.push(2);
    stackUsingTwoQueue.push(1);

    assertEquals((Integer) 1, stackUsingTwoQueue.pop());
    assertEquals((Integer) 2, stackUsingTwoQueue.pop());
    assertEquals((Integer) 3, stackUsingTwoQueue.pop());

  }

  @Test
  public void top() {
    stackUsingTwoQueue.push(3);
    stackUsingTwoQueue.push(2);
    stackUsingTwoQueue.push(1);
    stackUsingTwoQueue.push(4);

    assertEquals((Integer) 4, stackUsingTwoQueue.top());
    stackUsingTwoQueue.pop();
    stackUsingTwoQueue.pop();
    stackUsingTwoQueue.pop();
    stackUsingTwoQueue.pop();

  }

  @Test
  public void empty() {
    assertTrue(stackUsingTwoQueue.empty());
    stackUsingTwoQueue.push(1);
    assertFalse(stackUsingTwoQueue.empty());
  }
}