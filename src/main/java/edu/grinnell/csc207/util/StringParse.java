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
} // StringParse
