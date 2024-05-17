package assignment4;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class representing the main program for processing expressions.
 */
public class Project4 {
	
	// The main method of the program where input is received. 
    public static void main(String[] args) {
    	    	
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            char expressionType = line.charAt(0);
            String expression = line.substring(2); // Extract the expression

            ArrayBinaryTree tree = new ArrayBinaryTree();

            // Build binary tree based on expression type
            if (expressionType == '!') {
                // Prefix to Postfix
                StringTokenizer tokenizer = new StringTokenizer(expression);
                // Convert to postfix
                String postfixResult = tree.prefixToPostfix(expression);
                System.out.println("Postfix expression: " + postfixResult);
                tree.buildAndAddTreePrefix(tokenizer);

            } else if (expressionType == '@') {
                // Postfix to Prefix
                expression = new StringBuilder(expression).reverse().toString();
                StringTokenizer tokenizer = new StringTokenizer(expression);
                // Convert to prefix
                String prefixResult = tree.postfixToPrefix(expression);
                System.out.println("Prefix expression: " + prefixResult);
                tree.buildAndAddTreePostfix(tokenizer);
                
            }

            System.out.println(); // Empty line
            System.out.println("Done!");
            
        }
    }
}
