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
    String[] inputSplits = input.split(" ");

    if (StringParse.isValidInput(input)) {
      if (inputSplits[0].equals("STORE")) {
        calc.store(inputSplits[1].charAt(0));
        return null;
      } else if (inputSplits[0].equals("QUIT")) {
        return null;
      } else {
        calc.clear();
        String operator = "+";
        for (String str : inputSplits) {
          if (StringParse.isOperator(str)) {
            operator = str;
          } else {
            calc.handleOperators(operator, calc.handleValue(str));
          } // operator logics
        } // for each input items
      }
    } else {
      return null;
    }
    return calc.get();
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
    calc.clear();

    String input = " ";
    while (!input.equals("QUIT")) {
      input = scan.nextLine();
      BigFraction result = processCommand(input, calc);
      if (result != null) {
        pen.println(result);
      }
    } // main logic loop
    scan.close();
  } // main
} // InteractiveCalculator
