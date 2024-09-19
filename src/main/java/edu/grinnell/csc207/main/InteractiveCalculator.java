package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.StringParse;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Interactive calculator that computes mathematical operations on fractions. Repeatedly asks for
 * inputs. QUIT to quit, STORE x to store last computed value.
 *
 * @author Harrison Zhu
 */
public class InteractiveCalculator {

  /**
   * Evaluates a valid expression. <br>
   * Does not return anything, stores the calculated value in calc
   *
   * @param inputArray List of expressions in order
   * @param calc BFCalculator instance
   */
  public static void evaluateExpression(String[] inputArray, BFCalculator calc) {
    calc.clear();
    String operator = "+";
    for (String str : inputArray) {
      if (StringParse.isOperator(str)) {
        operator = str;
      } else {
        calc.handleOperators(operator, calc.handleValue(str));
      } // operator logics
    } // for each input items
  } // evaluateExpression(String[], BFCalculator)

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
        evaluateExpression(inputSplits, calc);
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
