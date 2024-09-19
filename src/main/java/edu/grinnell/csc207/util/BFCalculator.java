package edu.grinnell.csc207.util;

/**
 * The primary workhorse. This class should have a field that stores the last value calculated and
 * provide the following methods. (Note that all of these methods are object methods, not static
 * methods; we should be able to invoke multiple calculators simultaneously.)
 *
 * @author Harrison Zhu
 */
public class BFCalculator {
  /** Last calculated value. Initialize to BigFraction.ZERO */
  private BigFraction mem = BigFraction.ZERO;

  /** Register for STORE commands. */
  private BFRegisterSet register = new BFRegisterSet();

  /**
   * gets the last computed value (returns 0 if there is no such value).
   *
   * @return the last computed value
   */
  public BigFraction get() {
    return this.mem;
  } // get()

  /**
   * adds val to the last computed value.
   *
   * @param val number to add by
   */
  public void add(BigFraction val) {
    this.mem = this.mem.add(val);
  } // add(BigFraction)

  /**
   * subtracts val from the last computed value.
   *
   * @param val number to subtract by
   */
  public void subtract(BigFraction val) {
    this.mem = this.mem.subtract(val);
  } // subtract(BigFraction)

  /**
   * multiplies the last computed value by val.
   *
   * @param val number to multiply by
   */
  public void multiply(BigFraction val) {
    this.mem = this.mem.multiply(val);
  } // multiply(BigFraction)

  /**
   * divides the last computed value by val.
   *
   * @param val number to divide by
   */
  public void divide(BigFraction val) {
    this.mem = this.mem.divide(val);
  } // divide(BigFraction)

  /** resets the last computed value to 0. */
  public void clear() {
    this.mem = new BigFraction(0, 1);
  } // clear

  /**
   * Handles + - * / string operators.
   *
   * @param operator + - * /
   * @param val BigFraction value to do operation on
   */
  public void handleOperators(String operator, BigFraction val) {
    switch (operator) {
      case "+":
        this.add(val);
        break;
      case "-":
        this.subtract(val);
        break;
      case "*":
        this.multiply(val);
        break;
      case "/":
        this.divide(val);
        break;
      default:
        System.err.println("Error: operator " + operator + " not recongnized");
        break;
    } // four cases for four operators
  } // handleOperators(String, BigFraction)

  /**
   * Handles value input string. If string is a lowercase letter, get the value from register.
   *
   * @param str input string
   * @return BigFraction value
   */
  public BigFraction handleValue(String str) {
    if (StringParse.isSingleLowercaseLetter(str)) {
      return getVar(str.charAt(0));
    } else {
      return new BigFraction(str);
    } // new fraction or storeage
  } // handleValue(String)

  /**
   * Store the last calculated value in a variable.
   *
   * @param letter letter to store to
   */
  public void store(char letter) {
    this.register.store(letter, mem);
  } // store(char)

  /**
   * Get the store value.
   *
   * @param letter
   * @return BigFraction value
   */
  public BigFraction getVar(char letter) {
    return this.register.get(letter);
  } // getVar(char)
} // BFCalculator
