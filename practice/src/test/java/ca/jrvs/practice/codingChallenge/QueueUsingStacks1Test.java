package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueUsingStacks1Test {

  QueueUsingStacks1 queueUsingStacks1 = new QueueUsingStacks1();

  @Test
  public void pushAndPop() {
    queueUsingStacks1.push(1);
    queueUsingStacks1.push(2);
    queueUsingStacks1.push(3);

    assertEquals(1, queueUsingStacks1.pop());
    assertEquals(2, queueUsingStacks1.pop());
    assertEquals(3, queueUsingStacks1.pop());
  }

  @Test
  public void peek() {
    queueUsingStacks1.push(1);
    queueUsingStacks1.push(2);
    queueUsingStacks1.push(3);

    assertEquals(1, queueUsingStacks1.peek());

    queueUsingStacks1.pop();
    queueUsingStacks1.pop();
    queueUsingStacks1.pop();
  }

  @Test
  public void empty() {
    queueUsingStacks1.push(1);
    assertFalse(queueUsingStacks1.empty());
    queueUsingStacks1.pop();
    assertTrue(queueUsingStacks1.empty());
  }
}