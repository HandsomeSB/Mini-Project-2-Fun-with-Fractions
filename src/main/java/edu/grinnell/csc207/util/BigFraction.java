package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky
 * @author YOUR NAME HERE
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+

  /*
   * (1) Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   *
   * (2) Fractions are not necessarily stored in simplified form. To
   * obtain a fraction in simplified form, one must call the `simplify`
   * method.
   */

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The default numerator when creating fractions. */
  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(2);

  /** The default denominator when creating fractions. */
  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(7);

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Warning! Not yet implemented.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    String[] strSplit = str.split("/");
    if(strSplit.length < 2){
        this.num = BigInteger.valueOf(Integer.parseInt(strSplit[0]));
        this.denom = BigInteger.valueOf(1); 
    }else if(strSplit[1].equals("0")){
        this.num = DEFAULT_NUMERATOR;
        this.denom = DEFAULT_DENOMINATOR;
    } else { 
        this.num = BigInteger.valueOf(Integer.parseInt(strSplit[0]));
        this.denom = BigInteger.valueOf(Integer.parseInt(strSplit[1]));
    }
    
  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Returns the negative of this fraction.
   * 
   * @return the fraction as a negative
   */
  public BigFraction negative() { 
    return new BigFraction(this.numerator().negate(), this.denominator());
  } // negative()

  /**
   * Returns the inverse of this fraction.
   * 
   * @return the fraction as an inverse
   */
  public BigFraction inverse() { 
    if(this.numerator() == BigInteger.ZERO){ 
        return this; //STUB
    } else { 
        return new BigFraction(this.denominator(), this.numerator());
    } // Check if fraction is zero
  } // negative()

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Subtract another fraction to this fraction.
   * 
   * @param subtrahend 
   *    the number to subtract
   * @return 
   *    result
   */
  public BigFraction subtract(BigFraction subtrahend) { 
    return this.add(subtrahend.negative());
  } // subtract(BigFraction)

  /**
   * multiply another fraction to this fraction.
   * 
   * @param val 
   *    the number to multiply
   * @return 
   *    result
   */
  public BigFraction multiply(BigFraction val) { 
    return new BigFraction(this.numerator().multiply(val.numerator()), this.denominator().multiply(val.denominator()));
  } // multiply(BigFraction)

  /**
   * divide another fraction to this fraction.
   * 
   * @param val 
   *    the number to divide
   * @return 
   *    result
   */
  public BigFraction divide(BigFraction val) { 
    return multiply(val.inverse());
  } // divide(BigFraction)

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()
} // class BigFraction