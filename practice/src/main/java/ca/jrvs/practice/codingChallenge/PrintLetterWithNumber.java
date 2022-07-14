package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Print-letter-with-number-a843f0d552af4a608536ca43cb413ced
 */
public class PrintLetterWithNumber {

  /**
   * Big-O: O(n)
   * Justification: Iterating each character of s and appending it to the string builder. This takes
   * one iteration which is O(n)
   */
  public String printLetterWithNumber(String s) {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char ithChar = s.charAt(i);
      if (Character.isLetter(ithChar)) {
        if (Character.isUpperCase(ithChar)) {
          res.append(ithChar);
          res.append((int) ithChar - 38);
        } else {
          res.append(ithChar);
          res.append((int) ithChar - 96);
        }
      } else {
        res.append(ithChar);
      }
    }
    return res.toString();
  }

//  public static void main(String[] args) {
//    PrintLetterWithNumber printLetterWithNumber = new PrintLetterWithNumber();
//    System.out.println(printLetterWithNumber.printLetterWithNumber("abcee"));
//    System.out.println(printLetterWithNumber.printLetterWithNumber("abc xyz"));
//  }
}
