package edu.grinnell.csc207.util;

/**
 * Util function to process the user inputs for the calculator program.
 *
 * @author Harrison Zhu
 */
public class StringParse {
  /**
   * Check if string contains a single lowercase letter ONLY. e.g. "e"
   *
   * @param str string to check
   * @return boolean
   */
  public static boolean isSingleLowercaseLetter(String str) {
    return str.length() == 1 && Character.isLowerCase(str.charAt(0));
  } // isSingleLowercaseLetter(String)

  /**
   * Check if string represents a fraction. e.g "1/3"
   *
   * @param str string to check
   * @return boolean
   */
  public static boolean isFraction(String str) {
    return str.matches("^-?\\d+/\\d+$");
  } // isFraction(String)

  /**
   * Check if a string is a number. e.g. 123
   *
   * @param str string to check
   * @return boolean
   */
  public static boolean isNumber(String str) {
    return str.matches("-?\\d+");
  } // isNumber(String)

  /**
   * Check if a string is an operator. e.g. + - * /
   *
   * @param str string to check
   * @return boolean
   */
  public static boolean isOperator(String str) {
    return str.matches("[+\\-*/]");
  } // isOperator(String)

  /**
   * Check if a single term is valid within an expression. is fraction, operator, single lowercase
   * letter, or a number
   *
   * @param str the single term to check
   * @return true if term is valid
   */
  public static boolean isValidTerm(String str) {
    return (StringParse.isFraction(str)
        || StringParse.isOperator(str)
        || StringParse.isSingleLowercaseLetter(str)
        || StringParse.isNumber(str));
  } // isValidTermm

  /**
   * Check if the expression is in valid format. Not valid if two operators/numbers in a row. Not
   * valid if any of the terms is invalid.
   *
   * @param input the full input expression
   * @return true if input is a valid expression
   */
  public static boolean isValidFormat(String input) {
    String[] inputSplits = input.split(" ");
    boolean wasOperator = isOperator(inputSplits[0]);
    for (int i = 1; i < inputSplits.length; ++i) {
      if (wasOperator == isOperator(inputSplits[i]) || !isValidTerm(inputSplits[i])) {
        return false;
      } // Check no consecutive operator/number and is valid term
      wasOperator = isOperator(inputSplits[i]);
    } // For each term starting from 1
    return isValidTerm(inputSplits[0]) // Check if first term is valid
        && !isOperator(inputSplits[0]) // First term can't be operator
        && !isOperator(inputSplits[inputSplits.length - 1]);  // Last term can't be operator
  } // isValidFormat

  /**
   * Check if input is valid. Differs from isValidFormat, isValidInput checks for STORE and QUIT
   * commands as well
   *
   * @param input the full string input
   * @return true if input is valid
   */
  public static boolean isValidInput(String input) {
    String[] inputSplits = input.split(" ");

    if (inputSplits.length == 0) {
      System.err.println("Error: empty input");
      return false;
    } else if (inputSplits[0].equals("STORE")) {
      if (inputSplits.length < 2) {
        System.err.println("Error: STORE command requires 1 extra parameter");
        return false;
      } else if (!StringParse.isSingleLowercaseLetter(inputSplits[1])) {
        System.err.println("Error: parameter should be a single lowercase letter");
        return false;
      } else {
        return true;
      } // Conditions specific to STORE command
    } else if (inputSplits[0].equals("QUIT")) {
      return true;
    } else if (!StringParse.isValidFormat(input)) {
      System.err.println("Error: Wrong format");
      return false;
    } else {
      return true;
    } // input cases
  } // isValidInput
} // StringParse
