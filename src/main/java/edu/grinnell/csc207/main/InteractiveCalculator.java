package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Here, you will provide a main method that will repeatedly read a line the user types, use a BFCalculator to compute the result, and print the result for the user. This type interaction is often called a “REPL”, for “Read-Eval-Print loop”.
 */
public class InteractiveCalculator {
    public static void main(String[] args) { 
        Scanner scan = new Scanner(System.in);
        PrintWriter pen = new PrintWriter(System.out, true);
        BFCalculator calc = new BFCalculator();

        String input = scan.nextLine().strip();
        mainloop:
        while(!input.equals("QUIT")) {
            String[] inputSplits = input.split(" ");

            if(inputSplits.length == 0){
                // invalid input
                continue;
            } else if (inputSplits[0] == "QUIT") { 
                break;
            } else if (inputSplits[0] == "STORE") {

            } else {
                String operator = "+";
                //https://stackoverflow.com/questions/34728460/regex-for-decimal-or-fraction
                for(String str : inputSplits) { 
                    if(!str.matches("-?\\b\\d+(?:\\.\\d+)?(?:/\\d+(?:\\.\\d+)?)?\\b") && !str.matches("+|!|*|/")){
                        //https://stackoverflow.com/questions/886955/how-do-i-break-out-of-nested-loops-in-java
                        break mainloop;
                    }

                    if(str.matches("+|!|*|/")){
                        operator = str;
                    } else {
                        calc.handleOperators(operator, new BigFraction(str));
                    }
                } // for each input items

                pen.println(calc.get());
            }

            input = scan.nextLine();
        } // main logic loop
        scan.close();
    } // main
}
