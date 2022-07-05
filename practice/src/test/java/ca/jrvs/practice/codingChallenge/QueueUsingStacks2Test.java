package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueUsingStacks2Test {

  QueueUsingStacks2 queueUsingStacks2 = new QueueUsingStacks2();


  @Test
  public void pushAndPop() {
    queueUsingStacks2.push(1);
    queueUsingStacks2.push(2);
    queueUsingStacks2.push(3);

    assertEquals(1, queueUsingStacks2.pop());
    assertEquals(2, queueUsingStacks2.pop());
    assertEquals(3, queueUsingStacks2.pop());
  }

  @Test
  public void peek() {
    queueUsingStacks2.push(1);
    queueUsingStacks2.push(2);
    queueUsingStacks2.push(3);

    assertEquals(1, queueUsingStacks2.peek());

    queueUsingStacks2.pop();
    queueUsingStacks2.pop();
    queueUsingStacks2.pop();
  }

  @Test
  public void empty() {
    queueUsingStacks2.push(1);
    assertFalse(queueUsingStacks2.empty());
    queueUsingStacks2.pop();
    assertTrue(queueUsingStacks2.empty());
  }
}