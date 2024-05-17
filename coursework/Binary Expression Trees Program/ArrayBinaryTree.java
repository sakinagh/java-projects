package assignment4;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class representing an array-based binary tree.
 */
public class ArrayBinaryTree {
    private static final int MAX_SIZE = 1023;
    public String[] arrayTree;
    public int size;
    
    public void setElement(int index, String element) {
    	arrayTree[index] = element;
    }

    /**
     * Constructs ArrayBinaryTree with default settings.
     */
    public ArrayBinaryTree() {
        arrayTree = new String[MAX_SIZE];
        size = 0;
    }

    /**
     * Adds a left child with the given data to the node at the specified parent index.
     * @param parentIndex The index of the parent node.
     * @param data The data of the left child node to be added.
     */
    public void addLeft(int parentIndex, String data) {
        int leftChildIndex = 2 * parentIndex + 1;
        if (leftChildIndex < MAX_SIZE) {
        	arrayTree[leftChildIndex] = data;
        	size = Math.max(size, leftChildIndex + 1);
        }
    }

    /**
     * Adds a right child with the given data to the node at the specified parent index.
     * @param parentIndex The index of the parent node.
     * @param data The data of the right child node to be added.
     */
    public void addRight(int parentIndex, String data) {
        int rightChildIndex = 2 * parentIndex + 2;
        if (rightChildIndex < MAX_SIZE) {
            arrayTree[rightChildIndex] = data;
        	size = Math.max(size, rightChildIndex + 1);
        }
    }

    /**
     * Displays the graphical representation of the binary tree.
     */
    public void displayTree() {
        int count = 0;
        for (String c : arrayTree) {
            if (c != null) {
                count++;
            }
        }
        int arrSize = count;
        printTree(arrayTree, arrSize);
    }
    
    /**
     * Helper method to print a balanced binary tree.
     * @param array The array representing the binary tree.
     * @param arrayLength The length of the array.
     */
    public void printTree(String[] array, int arrayLength) {
        
    	int levels = (int) (Math.log(arrayLength) / Math.log(2)) + 1;
	    int currentIndex = 0;
	    int level = 1;
	    int nodesInLevel = 1;
	    int spaces = (int) Math.pow(2, levels - level) - 1;
	
	    for (int i = 0; i < 10; i++) { // stop at height 9 
	        for (int j = 0; j < nodesInLevel; j++) {
	            if (j == 0) printSpaces(spaces);
	            if (array[currentIndex] != null) System.out.print(array[currentIndex]);
	            else System.out.print(" "); 
	            currentIndex++;
	            if (j < nodesInLevel - 1) printSpaces(2 * spaces + 1);
	        }
	        System.out.println();
	        nodesInLevel *= 2;
	        spaces = (int) Math.pow(2, levels - level - 1) - 1;
	        level++;
	    }
        
    }
    
    /**
     * Prints the specified number of spaces.
     * @param level The number of spaces to print.
     */
    public void printSpaces(int level) {
    	for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
    }
 


    /**
     * Method to build a binary tree based on a prefix expression and add nodes to the array.
     * @param stream The tokenizer stream containing the prefix expression.
     */
    public void buildAndAddTreePrefix(StringTokenizer stream) {
    	String root = buildTreePrefix(stream, 0);
        if (root != null) {
            arrayTree[0] = root;
        }
        displayTree();
    }

    /**
     * Method to build a binary tree based on a postfix expression and add nodes to the array.
     * @param stream The tokenizer stream containing the postfix expression.
     */
    public void buildAndAddTreePostfix(StringTokenizer stream) {
    	String root = buildTreePostfix(stream, 0);
        if (root != null) {
            arrayTree[0] = root;
        }
        displayTree();
    }

    /**
     * Method to build a binary tree for a prefix expression.
     * @param stream The tokenizer stream containing the prefix expression.
     * @param index The index of the current node being processed.
     * @return The root node of the constructed binary tree.
     */
    public String buildTreePrefix(StringTokenizer stream, int index) {
        if (!stream.hasMoreTokens()) return null;
        String token = stream.nextToken();
        if (isOperand(token)) {
        	char operand = token.charAt(0);
        	String temp = String.valueOf(operand);
            addToArray(temp, index);
            return temp;
        } else {
        	char operator = token.charAt(0);
        	String temp = String.valueOf(operator);
            addToArray(temp, index); 
            String leftChild = buildTreePrefix(stream, 2 * index + 1);
            String rightChild = buildTreePrefix(stream, 2 * index + 2);
            return temp;
        }

    }

    /**
     * Method to build a binary tree for a postfix expression.
     * @param stream The tokenizer stream containing the postfix expression.
     * @param index The index of the current node being processed.
     * @return The root node of the constructed binary tree.
     */
    public String buildTreePostfix(StringTokenizer stream, int index) {
        if (!stream.hasMoreTokens()) return null;
        String token = stream.nextToken();
        if (isOperand(token)) {
        	char operand = token.charAt(0);
        	String temp = String.valueOf(operand);
            addToArray(temp, index);
            return temp;
        } else {
        	char operator = token.charAt(0);
        	String temp = String.valueOf(operator);
            addToArray(temp, index); 
            String rightChild = buildTreePostfix(stream, 2 * index + 2); // Build right subtree first
            String leftChild = buildTreePostfix(stream, 2 * index + 1); 
            return temp;
        }
    }

    /**
     * Method to add nodes from the binary tree to the array.
     * @param n The current node.
     * @param index The index of the current node in the array.
     */
    public void addToArray(String n, int index) {
        if (n != null) {
            arrayTree[index] = n;
            // Calculate parent index
            int parentIndex = (index - 1) / 2;

            // Determine if it's the left or right child
            if (index % 2 == 1) {
                // Odd index indicates left child
            	// Add left child to the parent node
                if (parentIndex >= 0) addLeft(parentIndex, n); 
            } else {
                // Even index indicates right child
            	// Add right child to the parent node
                if (parentIndex >= 0) addRight(parentIndex, n); 
            }
        } else {
            if (index >= MAX_SIZE) throw new IllegalStateException("Tree height exceeds maximum height of 9");
        	// If the root is null, add a placeholder node to maintain the structure
            arrayTree[index] = null; 
        }
        
    }


    /**
     * Converts a prefix expression to a postfix expression.
     * @param prefixExpression The prefix expression to be converted.
     * @return The equivalent postfix expression.
     */
    public String prefixToPostfix(String prefixExpression) {
        Stack<String> stack = new Stack<String>(); 
        String[] arrOfStr = prefixExpression.split(" ");
        int l = arrOfStr.length; 
        for(int i = l-1; i >= 0; i--){ 
            if(isOperatorConvert(arrOfStr[i].charAt(0))) { 
                String stack1 = stack.peek(); 
                stack.pop();
                String stack2 = stack.peek(); 
                stack.pop(); 
                String temp = stack1 + " " + stack2 + " " + arrOfStr[i]; 
                stack.push(temp); 
            } else { 
                stack.push(arrOfStr[i]); 
            } 
        } 
        return stack.peek();
    }
    
    
    /**
     * Converts a postfix expression to a prefix expression.
     * @param postfixExpression The postfix expression to be converted.
     * @return The equivalent prefix expression.
     */
    public String postfixToPrefix(String postfixExpression) {
        Stack<String> stack = new Stack<String>(); 
        String[] arrOfStr = postfixExpression.split(" ");
        int l = arrOfStr.length; 
        for(int i = l-1; i >= 0; i--){ 
            if(isOperatorConvert(arrOfStr[i].charAt(0))) { 
                String stack1 = stack.peek(); 
                stack.pop();
                String stack2 = stack.peek(); 
                stack.pop(); 
                String temp = arrOfStr[i] + " " + stack2 + " " + stack1; 
                stack.push(temp); 
            } else { 
                stack.push(arrOfStr[i]); 
            } 
        } 
        return stack.peek();
    }


    /**
     * Checks if the character is an operator.
     * @param c The character to be checked.
     * @return True if the character is an operator, otherwise false.
     */
    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    /**
     * Checks if the character is an operator.
     * @param x The character to be checked.
     * @return True if the character is an operator, otherwise false.
     */
    public boolean isOperatorConvert(char x){ 
        switch (x){ 
            case '+':
            case '-': 
            case '/': 
            case '*':
            case '^':
                return true; 
        } 
        return false; 
    } 
    
    /**
     * Checks if the token is an operand.
     * @param token The token to be checked.
     * @return True if the token is an operand, otherwise false.
     */
    public static boolean isOperand(String token) {
        return token.length() == 1 && Character.isLetterOrDigit(token.charAt(0));
    }


}
