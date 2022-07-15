package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Swap-two-numbers-3a4d426646f5437d84aa6cc40720e49d
 */
public class SwapTwoNumbers {

  /**
   * Big-O: O(1)
   * Justification: The size of the input is fixed to 2 only an everything else is constant time
   */
  public int[] bitManipulation(int[] arr) {
    /**
     * to swap x and y:
     * y' = (x xor y) xor y
     * x' = (x xor y) xor x
     */
    if (arr.length != 2) {
      return new int[2];
    }
    arr[0] = arr[0] ^ arr[1];
    arr[1] = arr[0] ^ arr[1];
    arr[0] = arr[0] ^ arr[1];

    return arr;
  }

  /**
   * Big-O: O(1)
   * Justification: The size of the input is fixed to 2 only an everything else is constant time
   */
  public int[] arithmeticManipulation(int[] arr) {
    if (arr.length != 2) {
      return new int[2];
    }
    arr[0] = arr[0] + arr[1];
    arr[1] = arr[0] - arr[1];
    arr[0] = arr[0] - arr[1];
    return arr;
  }
}
