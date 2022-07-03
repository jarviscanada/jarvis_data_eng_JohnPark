package ca.jrvs.practice.dataStructure.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E> implements JTree<E> {

  /**
   * The comparator used to maintain order in this tree map
   * Comparator cannot be null
   */
  private Comparator<E> comparator;

  private Node<E> head = null;

  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  /**
   * Insert an object into the BST.
   * Please review the BST property.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    if (this.head == null)
      this.head = new Node<>(object, null);
    this.head.insert(object, comparator);
    return object;
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */
  @Override
  public E search(E object) {
    Node<E> searchedNode = head.search(object, comparator);
    if (searchedNode == null)
      return null;
    else
      return searchedNode.getValue();
  }

  /**
   * Remove an object from the tree.
   *
   * @param object to be removed
   * @return removed object
   * @throws IllegalArgumentException if the object not exists
   */
  @Override
  public E remove(E object) {
    Node<E> current = this.head;
    Node<E> parent = this.head;
    boolean isleftChild = false;

    if (current == null)
      throw new IllegalArgumentException("the object not exists");

    // Gets the proper node
    while (current != null && !current.getValue().equals(object)) {
      parent = current;

      if (comparator.compare(current.getValue(), object) < 0) {
        current = current.getLeft();
        isleftChild = true;
      } else {
        current = current.getRight();
        isleftChild = false;
      }
    }

    if (current == null)
      throw new IllegalArgumentException("the object not exists");

    if (current.getLeft() == null && current.getRight() == null) {
      if (current == head) {
        head = null;
        return current.getValue();
      } else {
        if (isleftChild)
          parent.setLeft(null);
        else
          parent.setRight(null);
      }
    }
    else if (current.getRight() == null) {
      if (current == head) {
        head = head.getLeft();
        head.setParent(null);
      } else if (isleftChild) { // current is left child of the parent
        parent.setLeft(current.getLeft());
      } else {
        parent.setRight(current.getLeft());
      }
    }
    else if (current.getLeft() == null) {
      if (current == head) {
        head = head.getRight();
        head.setParent(null);
      } else if (isleftChild) { // current is right child of the parent
        parent.setLeft(current.getRight());
      } else {
        parent.setRight(current.getRight());
      }
    }
    else {
      Node<E> successor = getSuccessor(current);
      if (current == head)
        head = successor;
      else if (isleftChild)
        parent.setLeft(successor);
      else
        parent.setRight(successor);
      successor.setLeft(current.getLeft());
    }
    return current.getValue();

  }

  private Node<E> getSuccessor(Node<E> node) {
    Node<E> parentOfSuccessor = node;
    Node<E> successor = node;
    Node<E> current = node.getRight();

    while (current != null) {
      parentOfSuccessor = successor;
      successor = current;
      current = current.getLeft();
    }
    if (successor != node.getRight()) {
      parentOfSuccessor.setLeft(successor.getRight());
      successor.setRight(node.getRight());
    }
    return successor;
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in pre-order
   */
  @Override
  public E[] preOrder() {
    ArrayList<E> arr = new ArrayList<>();
    this.head.preOrderHelper(arr);
    return (E[]) arr.toArray();
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in-order
   */
  @Override
  public E[] inOrder() {
    ArrayList<E> arr = new ArrayList<>();
    this.head.inOrderHelper(arr);
    return (E[]) arr.toArray();
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects pre-order
   */
  @Override
  public E[] postOrder() {
    ArrayList<E> arr = new ArrayList<>();
    this.head.postOrderHelper(arr);
    return (E[]) arr.toArray();
  }

  /**
   * traverse through the tree and find out the tree height
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    if (head == null)
      throw new NullPointerException("BST empty");
    return this.head.height();
  }

  static final class Node<E> {

    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }


    public void insert(E object, Comparator<E> comparator) {
      if (comparator.compare(object, this.value) == 0) {
        throw new IllegalArgumentException("The object already exist");
      }
      else if (comparator.compare(object, this.value) > 0) {
        if (this.getRight() == null)
          this.setRight(new Node<E>(object, this));
        else
          this.getRight().insert(object, comparator);
      } else {
        if (this.getLeft() == null)
          this.setLeft(new Node<E>(object, this));
        else
          this.getLeft().insert(object,comparator);
      }
    }

    public Node<E> search(E object, Comparator<E> comparator) {
      if (comparator.compare(this.getValue(), object) == 0)
        return this;
      if (comparator.compare(this.getValue(), object) > 0 && this.left != null)
        return this.left.search(object, comparator);
      if (this.right != null)
        return this.right.search(object, comparator);
      return null;
    }

    public void inOrderHelper(ArrayList<E> container) {
      if (this.left == null && this.right == null)
        container.add(this.value);
      else if (this.left == null) {
        container.add(this.value);
        this.getRight().inOrderHelper(container);
      } else if (this.right == null) {
        this.getLeft().inOrderHelper(container);
        container.add(this.value);
      } else {
        this.getLeft().inOrderHelper(container);
        container.add(this.value);
        this.getRight().inOrderHelper(container);
      }
    }

    public void preOrderHelper(ArrayList<E> container) {
      if (this.left == null && this.right == null)
        container.add(this.value);
      else if (this.left == null) {
        container.add(this.value);
        this.getRight().inOrderHelper(container);
      } else if (this.right == null) {
        container.add(this.value);
        this.getLeft().inOrderHelper(container);
      } else {
        container.add(this.value);
        this.getLeft().inOrderHelper(container);
        this.getRight().inOrderHelper(container);
      }
    }

    public void postOrderHelper(ArrayList<E> container) {
      if (this.left == null && this.right == null)
        container.add(this.value);
      else if (this.left == null) {
        this.getRight().inOrderHelper(container);
        container.add(this.value);
      } else if (this.right == null) {
        this.getLeft().inOrderHelper(container);
        container.add(this.value);
      } else {
        this.getLeft().inOrderHelper(container);
        this.getRight().inOrderHelper(container);
        container.add(this.value);
      }
    }

    public int height() {
      if (this.left == null && this.right == null)
        return 1;

      int left = 0;
      int right = 0;
      if (this.getLeft() != null)
        left = this.getLeft().height();
      if (this.getRight() != null)
        right = this.getRight().height();
      return (left > right) ? (left + 1) : (right + 1);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Node)) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return getValue().equals(node.getValue()) &&
          Objects.equals(getLeft(), node.getLeft()) &&
          Objects.equals(getRight(), node.getRight()) &&
          getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getValue(), getLeft(), getRight(), getParent());
    }
  }
}