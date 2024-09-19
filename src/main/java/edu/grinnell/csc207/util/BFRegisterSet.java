package edu.grinnell.csc207.util;

/**
 * A set of registers corresponding to the letters 'a' through 'z'. It should provide the following
 * methods. As with the case of BFCalculator, these should all be object methods so that we can have
 * multiple register sets.
 *
 * @author Harrison Zhu
 */
public class BFRegisterSet {

  /** Size of the register array. Set to 26 because there are 26 characters in the alphabet. */
  private static final int SIZE = 26;

  /** List of stored values. */
  private BigFraction[] registerValues = new BigFraction[SIZE];

  /**
   * Converts a letter to integer based on alphabetical order.
   *
   * @param letter the letter
   * @return alphabetical index
   */
  private int letter2int(char letter) {
    return (int) letter - (int) 'a';
  } // letter2int

  /**
   * stores the given value in the specified register.
   *
   * @param register
   * @param val
   */
  public void store(char register, BigFraction val) {
    registerValues[letter2int(register)] = val;
  } // store(char, BigFraction)

  /**
   * retrieves the value from the given register.
   *
   * @param register
   * @return the value in the register
   */
  public BigFraction get(char register) {
    return registerValues[letter2int(register)].simplify();
  } // get(char)

  /** Default constructor. Sets all item in register to 0. */
  public BFRegisterSet() {
    for (int i = 0; i < registerValues.length; ++i) {
      registerValues[i] = BigFraction.ZERO;
    } // for each value
  } // default constructor
} // BFRegisterSet
