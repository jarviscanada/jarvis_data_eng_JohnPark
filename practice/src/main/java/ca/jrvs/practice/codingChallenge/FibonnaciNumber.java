package ca.jrvs.practice.codingChallenge;

public class FibonnaciNumber {

  /**
   * Time Complexity: O(2^n)
   * Justification: Let T(n) be the time complexity of fibonacciNumberRecursive. Then,
   * T(n) = T(n-1) + T(n-2) + c where c is some constant. Then,
   * T(n) >= T(n-2) + T(n-2) + c >= 2T(n-2) + c = 2 (2T(n-4) + c) + c = 2^2 T(n-2*2) + (2^2 - 1) c
   * = 2^k T(n-2k) + (2^k - 1) c = 2^(n/2) * T(0) + (2^(n/2) - 1) c => approximately 2^n
   * Thus, because recursion causes functions that are called to be called again and again
   * ex. f(10) = f(9) + f(8) = f(8) + f(7) + f(7) + f(6) -> f(7) called twice and f(8) was called and
   * in called again sometime later because f(9) was derived into f(8)
   * This causes exponential time complexity
   */
  public int fibonnaciNumberRecursive(int n) {
    if (n < 1) {
      return 0;
    } else if (n == 1) {
      return 1;
    } else {
      return fibonnaciNumberRecursive(n - 1) + fibonnaciNumberRecursive(n - 2);
    }
  }

  /**
   * Time complexity: O(n)
   * Justification: Except for the for-loop, which iterates at most n-2 times, everything else is
   * constant time execution.
   */
  public int fibonacciNumberIterative(int n) {
    if (n < 1) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    else if (n == 2) {
      return 1;
    }
    else {
      int n1 = 1;
      int n2 = 1;
      for (int i = 0; i < n-2; i++) {
        int tmp = n2;
        n2 = n1 + n2;
        n1 = tmp;
      }
      return n2;
    }
  }
}
