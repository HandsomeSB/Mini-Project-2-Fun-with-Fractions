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

        if (inputSplits.length == 0) {
            System.err.println("Error: empty input");
            return null;
        } else if (inputSplits[0].equals("QUIT")) {
            return null; //break the loop
        } else if (inputSplits[0].equals("STORE")) {
            if (inputSplits.length < 2) { 
                System.err.println("Error: STORE command requires 1 extra parameter");
                return null;
            } else if (!StringParse.isSingleLowercaseLetter(inputSplits[1])){
                System.err.println("Error: parameter should be a single lowercase letter");
                return null;
            } else {
                calc.store(inputSplits[1].charAt(0));
            }
        } else if(!StringParse.isValidFormat(input)){
            System.err.println("Error: Wrong format");
            return null;
        }
        else {
            calc.clear();
            String operator = "+";
            for (String str : inputSplits) {
                // Valid input: 1/3 1 a
                if (!StringParse.isValidTerm(str)) {
                    System.err.println("Error: Invalid Input");
                    return null;
                } // Check valid inputs

                if (StringParse.isOperator(str)) {
                    operator = str;
                } else {
                    calc.handleOperators(operator, calc.handleValue(str));
                } // operator logics
            } // for each input items
        }
        return calc.get().simplify();
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
      if(result != null) {
        pen.println(result);
      }
    } // main logic loop
    scan.close();
  } // main
} // InteractiveCalculator
