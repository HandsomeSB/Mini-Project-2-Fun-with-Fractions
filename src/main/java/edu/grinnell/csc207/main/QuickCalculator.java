package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BigFraction;
import java.io.PrintWriter;

/**
 * Uses the same calculator logic as interactive calculator. Expects a sequence of expressions in
 * command line arguments rather than asking for input
 *
 * @author Harrison Zhu
 */
public class QuickCalculator {
  /**
   * Entry point for QuickCalculator class. Uses the processCommand function within
   * InteractiveCalculator.
   *
   * @param args Expressions or commands to pass to the calculator
   */
  public static void main(String[] args) {
    BFCalculator calc = new BFCalculator();
    PrintWriter pen = new PrintWriter(System.out, true);

    for (String input : args) {
      if (input.equals("QUIT")) {
        break;
      } // If input is QUIT, exit the program

      BigFraction result = InteractiveCalculator.processCommand(input, calc);
      if (result != null) {
        pen.println(input + " = " + result);
      } // If the command returns a result, print output.
    } // For each argument
  } // main
} // QuickCalculator
