package edu.grinnell.csc207.util;

/** Util function to process the user inputs for the calculator program. */
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

  public static boolean isValidTerm(String str) {
    return (StringParse.isFraction(str)
        || StringParse.isOperator(str)
        || StringParse.isSingleLowercaseLetter(str)
        || StringParse.isNumber(str));
  }

  public static boolean isValidFormat(String input) {
    String[] inputSplits = input.split(" ");
    boolean wasOperator = isOperator(inputSplits[0]);
    for (int i = 1; i < inputSplits.length; ++i) {
      if (wasOperator == isOperator(inputSplits[i]) || !isValidTerm(inputSplits[i])) {
        return false;
      }
      wasOperator = isOperator(inputSplits[i]);
    }
    return true && isValidTerm(inputSplits[0]);
  }

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
      }
    } else if (inputSplits[0].equals("QUIT")) {
      return true;
    } else if (!StringParse.isValidFormat(input)) {
      System.err.println("Error: Wrong format");
      return false;
    } else {
      return true;
    }
  }
} // StringParse
