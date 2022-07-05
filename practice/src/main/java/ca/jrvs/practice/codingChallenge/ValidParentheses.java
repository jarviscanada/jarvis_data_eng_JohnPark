package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
  private HashMap<Character, Character> map;

  public ValidParentheses() {
    this.map = new HashMap<Character, Character>();
    this.map.put('(', ')');
    this.map.put('[', ']');
    this.map.put('{', '}');
  }

  /**
   * Big-O: O(n)
   * Justification: It iterates each character of the string once - approximately n iteration.
   * Thus, O(n) time complexity. Everything else is constant time complexity.
   */
  public boolean isValid(String s) {
   Stack<Character> stack = new Stack<>();

   for (int i = 0; i < s.length(); i++) {
     char c = s.charAt(i);
     if (map.containsKey(c))
       stack.add(map.get(c));
     else
       if (stack.isEmpty() || c != stack.pop())
         return false;
   }
   return stack.isEmpty();
  }
}
