package edu.grinnell.csc207.util;

/**
 * A set of registers corresponding to the letters 'a' through 'z'. It should provide the following methods. As with the case of BFCalculator, these should all be object methods so that we can have multiple register sets.
 */
public class BFRegisterSet {
    private BigFraction[] registerValues = new BigFraction[26];

    private int letter2int(char letter) {
        return (int) letter - (int) 'a';
      } // letter2int

    /**
     * stores the given value in the specified register.
     * @param register
     * @param val
     */
    public void store(char register, BigFraction val) { 
        registerValues[letter2int(register)] = val;
    }

    /**
     * retrieves the value from the given register.
     * @param register
     * @return
     */
    public BigFraction get(char register) { 
        return registerValues[letter2int(register)];
    }
}
