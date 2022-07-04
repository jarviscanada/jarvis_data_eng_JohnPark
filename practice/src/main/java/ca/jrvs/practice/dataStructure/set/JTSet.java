package ca.jrvs.practice.dataStructure.set;

import java.util.Comparator;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

public class JTSet<E> implements JSet<E> {

  private final NavigableMap<E,Object> map;
  private static final Object PRESENT = new Object();


  public JTSet(Comparator<? super E> comparator) {
    map = new TreeMap<>(comparator);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean contains(Object o) {
    return map.containsKey(o);
  }

  @Override
  public boolean add(E e) {
    if (map.containsKey(e)) {
      return false;
    }
    map.put(e, PRESENT);
    return true;
  }

  @Override
  public boolean remove(Object o) {
    if (!map.containsKey(o)) {
      return false;
    }
    map.remove(o);
    return false;
  }

  @Override
  public void clear() {
    map.clear();
  }
}
