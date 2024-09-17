package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.StringParse;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Here, you will provide a main method that will repeatedly read a line the user types, use a
 * BFCalculator to compute the result, and print the result for the user. This type interaction is
 * often called a “REPL”, for “Read-Eval-Print loop”.
 */
public class InteractiveCalculator {

    public static BigFraction processCommand(String input, BFCalculator calc) { 
        return BigFraction.ZERO;
    }

  /**
   * Main function of the program. QUIT to quit STORE x to store value in a key
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calc = new BFCalculator();

    String input = " ";
    mainloop:
    while (!input.equals("QUIT")) {
      input = scan.nextLine();
      String[] inputSplits = input.split(" ");

      if (inputSplits.length == 0) {
        System.err.println("Error: empty input");
        continue;
      } else if (inputSplits[0].equals("QUIT")) {
        break;
      } else if (inputSplits[0].equals("STORE")) {
        pen.println("STORE");
        calc.store(inputSplits[1].charAt(0));
      } else {
        calc.clear();
        String operator = "+";
        for (String str : inputSplits) {
          // Valid input: 1/3 1 a
          if (!(StringParse.isFraction(str)
              || StringParse.isOperator(str)
              || StringParse.isSingleLowercaseLetter(str)
              || StringParse.isNumber(str))) {
            // https://stackoverflow.com/questions/886955/how-do-i-break-out-of-nested-loops-in-java
            System.err.println("Error: Invalid Input");
            continue mainloop;
          } // Check valid inputs

          if (StringParse.isOperator(str)) {
            operator = str;
          } else {
            calc.handleOperators(operator, calc.handleValue(str));
          } // operator logics
        } // for each input items

        pen.println(calc.get());
      } // input parsing
    } // main logic loop
    scan.close();
  } // main
} // InteractiveCalculator
