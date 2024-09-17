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

  /**
   * Process a single line of command for interactive calculator.
   *
   * @param input a single line input from user
   * @param calc BFCalculator instance
   * @return the result from the calculator
   */
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
      } // Control flow for valid options
    } else {
      return null;
    } // check valid input
    return calc.get();
  } // processCommand(String, BFCalculator)

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
      } // print nothing if result is null
    } // main logic loop
    scan.close();
  } // main
} // InteractiveCalculator
