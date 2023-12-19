/* Sakina Ghafoor 
 * Lab 3 Assignment Fall 2023
 * Techniques in Programming
 */

package Lab3;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Represents a class that controls the execution of a program.
 */
public class Control extends View implements ActionListener, WindowListener {

	
	// these are the fields for manipulating input and output
	protected Model model = new Model();
	
	protected Hashtable<String, String> next = new Hashtable<String,String>();
	protected Hashtable<String, String> code = new Hashtable<String,String>();
	protected Hashtable<String, Double> var = new Hashtable<String,Double>();
	
	protected String start;
	protected String file;
	
	protected ArrayList<String> linesList = new ArrayList<>();	
	protected double operand1, operand2, result;
	protected String exp, nextExp;
	protected String[] tokens, tokens2;

	protected RandomAccessFile f;
	
	protected boolean runFlag = false;
	protected String lineNum;
	protected String codeLine;
	protected int iType;
	
	/**
     * Default constructor for the Control class.
     * Initializes action listeners and window events.
     */
	public Control() {
		read.addActionListener(this);
		save.addActionListener(this);
		execute.addActionListener(this);
		reset.addActionListener(this);
		
		this.addWindowListener(this);
		
	}
	
	/**
     * Handle action events from UI components.
     * @param e ActionEvent object representing the event.
     */
	public void actionPerformed(ActionEvent e) {
		String which = e.getActionCommand();
		
		if(which.equals("read")) processRead();
		if(which.equals("save")) processSave();
		if(which.equals("run")) processRun();
		if(which.equals("reset")) processReset();
	}
	
	/**
     * Opens a file dialog for selecting a program file.
     * @return The selected file's name and path.
     */
	public String getFileName() {
		
		FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename == null) System.exit(0);
		else file = fd.getDirectory() + filename;
		return file;
	}
	
	/**
     * Executes the program starting from a specified line.
     * @param start The line number to start execution from.
     * @param next  Hashtable storing the next line for each line.
     * @param code  Hashtable storing program code for each line.
     */
	public void runProgram(String start, Hashtable<String,String> next, Hashtable<String,String> code) {
		String nextKey = start;
		
		runFlag = true;
		while(runFlag) {
			lineNum = nextKey;
			
			codeLine = code.get(lineNum);
			if(verbose) System.out.println("line " + lineNum + " code: " + (code.get(lineNum)));
			
			nextKey = next.get(lineNum);
			if(verbose) System.out.println(lineNum + " " + nextKey + " ");
			
			iType = instructionType(codeLine);
			switch(iType) {
				case 0: proComment(); break;
				case 1: proAssign(); break;
				case 2: proPrint(); break;
				case 3: String x = proGOTO(next);
						if(!x.equals("UNDEFINED")) nextKey = x; break;
				case 4: String x2 = proIF(next);
						if(!x2.equals("NOTRANSFER")) nextKey = x2; break;
				case 5: proEND(); break;
			}
		}
	}
	
	/**
     * Handles the "END" instruction and terminates program execution.
     */
	public void proEND() {
				
		if (codeLine != null && codeLine.trim().equalsIgnoreCase("END")) {
	        runFlag = false;
	        display.append("Program execution terminated." + "\n");
	    }
	
	}

	/**
     * Process an "IF" instruction and determine whether to transfer control.
     * @param next Hashtable storing the next line for each line.
     * @return The line to jump to if the condition is met or "NOTRANSFER."
     */
	public String proIF(Hashtable<String, String> next) {
	    
	    // Split the IF statement into parts
	    String[] parts = codeLine.split(" ");
	    if (parts.length == 7) {
	        String ifKeyword = parts[0];          // "if("
	        //String openParenthesis = parts[1];    // "("
	        String variable = parts[1];           // Variable name
	        String operator = parts[2];           // Comparison operator (e.g., "=")
	        String value = parts[3];              // Value to compare
	        String closeParenthesis = parts[4];   // ")"
	        String gotoKeyword = parts[5];        // "goto"
	        String destination = parts[6];        // Line number to jump to

	        // Check if the IF statement is well-formed
	        if (ifKeyword.equals("if(") && closeParenthesis.equals(")") && gotoKeyword.equals("goto")) {
	            // Check if the variable exists in the variable table
	            if (var.containsKey(variable)) {
	                double variableValue = var.get(variable);
	                
	             // Convert the variable from the file input to double
	                double valueFromInput = Double.parseDouble(value);
	                
	                // Check the operator and compare the variable value
	                if (operator.equals("=") && valueFromInput == variableValue) {
	                    // Return the destination line to execute
	                    proGOTO(next);
	                    return destination;
	                    
	                }
	            } else {
	                // Handle the case when the variable is not found
	                display.setText("Variable not found: " + variable);
	            }
	        }
	    } else {
	        // Handle the case when the IF statement is not well-formed
	        display.append("Invalid IF statement: " + codeLine + "\n");
	    }

	    // If the IF condition is not met, return "NOTRANSFER" to continue sequentially
	    return "NOTRANSFER";
	    
	}

	/**
     * Process a "GOTO" instruction and jump to the specified line.
     * @param next Hashtable storing the next line for each line.
     * @return The line to jump to or "UNDEFINED" if the destination is not found.
     */
	public String proGOTO(Hashtable<String, String> next) {
		//String nextKey;
		//return null;
		
	    // Split the GOTO statement into parts
	    String[] parts = codeLine.split(" ");
	    if (parts.length == 2) {
	        String destination = parts[1];  // Get the destination

	        // Check if the destination line exists in the program
	        if (next.containsKey(destination)) {
	            // Return the destination line to execute
	            return destination;
	        } else {
	            // Handle the case when the destination line is not found
	            display.append("GOTO destination not found: " + destination + "\n");
	        }
	        
	        runProgram(destination, next, code);
	        
	    }

	    // If the GOTO statement is not valid, return "UNDEFINED"
	    return "UNDEFINED";
		
	}

	/**
     * Process a "print" instruction to display the value of a variable.
     */
	public void proPrint() {
	
		try {
			// Extract the variable name from the print statement
	        String variableName = codeLine.trim().substring(6);

	        if (var.containsKey(variableName)) {
	            double variableValue = var.get(variableName);

	            // Append the variable value to the console text area
	            consoleLines.append(String.format("%.2f%n", variableValue));
	        } /*else if(variableName.startsWith("//")) {
	        	consoleLines.append(variableName + "\n");
	        }*/ else {
	            // Handle the case when the variable is not found
	            display.append("Variable not found: " + variableName + "\n");
	            consoleLines.append("0.0" + "\n");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur during printing
	        display.append("Error in print statement: " + e.getMessage() + "\n");
	    }
		
	}

	/**
     * Process an assignment statement, which includes variable assignments and comparisons.
     */
	public void proAssign() {
		
		if (codeLine.toLowerCase().contains("if(")) {
	        proIF(code);
	    }
		else {
		try {
	        // Split the assignment statement into variable and expression
	        String[] assignmentParts = codeLine.split("=");
	        if (assignmentParts.length == 2) {
	            String variable = assignmentParts[0].trim();
	            String expression = assignmentParts[1].trim();
	            
	            // Check if this is a comparison statement
	            if (expression.toLowerCase().startsWith("if(") && expression.endsWith(")")) {
	                // This is a comparison statement
	                String condition = expression.substring(3, expression.length() - 1);
	                // Process the comparison and decide whether to transfer control
	                if (processComparison(condition)) {
	                    // The condition is true, transfer control to the specified line
	                    String[] tokens = codeLine.split(" ");
	                    if (tokens.length >= 6) {
	                        String destination = tokens[tokens.length - 1];
	                        runProgram(destination, next, code);
	                    }
	                }
	            } else {
	                // This is a regular assignment
	                double result = evaluateExpression(expression);
	                var.put(variable, result);
	            }
	        }
	    } catch (Exception e) {
	        display.append("Error in assignment: " + e.getMessage() + "\n");
	    } }
		
	}
	
	/**
     * Evaluate a mathematical expression and return the result.
     * @param expression The expression to evaluate.
     * @return The result of the expression.
     */
	public double evaluateExpression(String expression) {
	    String[] tokens = expression.split(" ");
	    double result = 0.0;
	    double currentOperand = 0.0;
	    String operator = "";

	    for (String token : tokens) {
	        if (token.matches("[+\\-*/]")) {
	            operator = token;
	        } else {
	            double operand = 0.0;
	            if (var.containsKey(token)) {
	                operand = var.get(token);
	            } else {
	                try {
	                    operand = Double.parseDouble(token);
	                } catch (NumberFormatException ex) {
	                    display.append("Error: Variable or constant not found: " + token + "\n");
	                }
	            }

	            if (operator.isEmpty()) {
	                result = operand;
	            } else {
	                if (operator.equals("+")) {
	                    result += operand;
	                } else if (operator.equals("-")) {
	                    result -= operand;
	                } else if (operator.equals("*")) {
	                    result *= operand;
	                } else if (operator.equals("/")) {
	                    result /= operand;
	                }
	                operator = "";
	            }
	        }
	    }

	    return result;
	}
	
	/**
     * Process a comparison condition and decide whether to transfer control.
     * @param condition The comparison condition to evaluate.
     * @return True if the condition is met; otherwise, false.
     */
	public boolean processComparison(String condition) {
	    // Split the condition into its parts
	    String[] parts = condition.split("=");
	    if (parts.length == 2) {
	        String variable = parts[0].trim();
	        String value = parts[1].trim();
	        
	        // Check if the variable exists and its value matches the specified value
	        if (var.containsKey(variable)) {
	            double variableValue = var.get(variable);
	            try {
	                double comparisonValue = Double.parseDouble(value);
	                return variableValue == comparisonValue;
	            } catch (NumberFormatException e) {
	                display.append("Invalid comparison value: " + value + "\n");
	            }
	        } else {
	            display.append("Variable not found: " + variable + "\n");
	        }
	    } else {
	        display.append("Invalid comparison format: " + condition + "\n");
	    }
	    return false;
	}

	
	/**
     * Process a comment instruction and display it in the console.
     */
	public void proComment() {
		
		try {
            // Extract the comment text from the codeLine
            String commentText = codeLine.trim().substring(2); // Skip the "//"

            // Append the comment to the console text area
            consoleLines.append("// " + commentText + "\n");
        } catch (Exception e) {
            display.append("Error in comment: " + e.getMessage() + "\n");
        }
		
	}

	/**
     * Determine the type of instruction based on the code line.
     * @param line The code line to analyze.
     * @return An integer representing the instruction type (0 for comment, 1 for assignment, etc.).
     */
	public int instructionType(String line) {
	    
		if (line == null) {
			runFlag = false;
	        return -1; // Return a default value or handle it appropriately
	    }
		
		// Check for Comment (starts with "//")
	    if (line.trim().startsWith("//")) {
	        return 0; // Comment
	    }
	    
	    // Check for Assignment (contains an "=")
	    if (line.contains("=")) {
	        return 1; // Assignment
	    }
	    
	    // Check for Print (e.g., "print variable" or "print expression")
	    if (line.toLowerCase().startsWith("print")) {
	        return 2; // Print
	    }
	    
	    // Check for GOTO (e.g., "goto n")
	    if (line.toLowerCase().startsWith("goto")) {
	        return 3; // GOTO
	    }
	    
	    // Check for IF (e.g., "if (condition) goto n")
	    if (line.toLowerCase().startsWith("if")) {
	        return 4; // IF
	    }
	    
	    // Check for END (e.g., "END")
	    if (line.trim().equalsIgnoreCase("END")) {
	        return 5; // END
	    }
	    
	    // Default to an unknown type
	    return -1;
		
	}

	/**
     * Read and process the contents of a selected program file.
     */
	public void processRead() {
		String filename = getFileName();
		String startLine;
		if (filename == null) {
	        return;
		}
	    try {
			f = new RandomAccessFile(file, "r");
		} catch(IOException ioe) {
			System.out.println(ioe);
			System.exit(0);
		}
		
	    // Clear the linesList to read the new file
	    linesList.clear();
	    
	    try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			//code.put("0","");	//first statement will be at index 1
			String line;
			while((line=in.readLine()) != null) {
				linesList.add(line.trim());
				display.append(line + "\n");
				
			}
			in.close();
			
			if (!linesList.isEmpty()) {
	            // Read the first line
	            String firstLine = linesList.get(0);

	            // Extract the line number from the first line
	            String[] tokens = firstLine.split(" ");
	            if (tokens.length > 0) {
	                startLine = firstLine;
	                start = startLine.substring(0,2);
	            }
	        }
			
		} catch(IOException ioe) {
			display.append(""+ioe + "\n");
			return;
		}
	    
	    String num, nextNum;
	    String inst, pattern;
	    for(int i=0; i<linesList.size()-1; i++) {
	    	
	    	exp = linesList.get(i);
	    	tokens = exp.split(" ");
	    	num = tokens[0];
	    	inst = exp.substring(3);
	    	code.put(num, inst);
	    	System.out.println(num + "::" + inst);
	    	
	    	nextExp = linesList.get(i + 1);
	    	tokens2 = nextExp.split(" ");
	    	nextNum = tokens2[0];
	    	next.put(num, nextNum);
	    		    	
	    	try {
	    		pattern = "^\\d.*";
	    		if (!exp.matches(pattern)) {
	    			throw new Exception("Check line format and numbering!");
	    		}
	    	} catch(Exception e) {
	    		display.append(e.getMessage() + "\n");
	    	}	
	    }
	    // System.out.println(" first line is" + code.get(codeLine));	    
	    
	}
	
	/**
     * Save the program code to a text file.
     */
	public void processSave() {
		
		// Save the program code to a text file
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
	    fileChooser.setFileFilter(filter);

	    int userSelection = fileChooser.showSaveDialog(this);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        try {
	            // Get the selected file
	            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

	            // Ensure the file has the ".txt" extension
	            if (!filePath.toLowerCase().endsWith(".txt")) {
	                filePath += ".txt";
	            }

	            // Get the text from the "display" text area
	            String textToSave = display.getText();

	            // Write the text to the selected file
	            FileWriter fileWriter = new FileWriter(filePath);
	            fileWriter.write(textToSave);
	            fileWriter.close();

	            consoleLines.append("Saved to: " + filePath + "\n");
	        } catch (IOException e) {
	            display.append("Error saving the file: " + e.getMessage() + "\n");
	        }
	    }
		
	}
	
	/**
     * Run the program starting from the specified line.
     */
	public void processRun() {
		
		runProgram(start, next, code);
		
	}
	
	/**
     * Reset the UI and data structures for a fresh start.
     */
	public void processReset() {
		// Clear the display and console text areas
	    display.setText("");
	    consoleLines.setText("");

	    // Optionally, clear the data structures used for the program
	    linesList.clear();
	    code.clear();
	    next.clear();
	    var.clear();
	}
	

	@Override
	public void windowOpened(WindowEvent e) {	}

	@Override
	public void windowClosing(WindowEvent e) {	  }

	@Override
	public void windowClosed(WindowEvent e) {	}

	@Override
	public void windowIconified(WindowEvent e) {	}

	@Override
	public void windowDeiconified(WindowEvent e) {	}

	@Override
	public void windowActivated(WindowEvent e) {	}

	@Override
	public void windowDeactivated(WindowEvent e) {	}
	
	/**
     * Entry point for the application.
     * @param args Command-line arguments (not used).
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Control();
	}
	
}
