package ca.jrvs.practice.codingChallenge;

public class CountPrimes {

  /**
   * Big-O: O(n)
   * Justification: The outer for loop iterates upto sqrt of n. The inner loop iterates up to n/2,
   * n/3, ... n/(last prime) - this is loglog(n). So the lines 15 - 22 takes O(sqrt(n) * loglog(n))
   * but the second for loop to determine the numberOfPrimes takes n iteration which is O(n).
   */
  public int countPrimes(int n) {
    if (n <= 2) {
      return 0;
    }

    boolean[] numbers = new boolean[n];
    for (int p = 2; p < (int) Math.sqrt(n) + 1; p++) {
      if (!numbers[p]) {
        for (int j = p*p; j < n; j += p) {
          numbers[j] = true;
        }
      }
    }

    int numberOfPrimes = 0;
    for (int i = 2; i < n; i++) {
      if (!numbers[i]) {
        numberOfPrimes++;
      }
    }

    return numberOfPrimes;
  }
}
