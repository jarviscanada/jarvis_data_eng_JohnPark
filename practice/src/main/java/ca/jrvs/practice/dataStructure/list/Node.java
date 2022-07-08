package ca.jrvs.practice.dataStructure.list;

public class Node<E> {

  public E item;
  public Node<E> prev;
  public Node<E> next;

  public Node(E item, Node<E> prev, Node<E> next) {
    this.item = item;
    this.prev = prev;
    this.next = next;
  }
}