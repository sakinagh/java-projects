
// Sakina Ghafoor
// Lab 2 Assignment
// COSC 20203 Rinewalt 055

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Lab2 extends JFrame implements ActionListener {
	JButton open = new JButton("Next Program");
	JTextArea result = new JTextArea(20,40);
	JLabel errors = new JLabel();
	JScrollPane scroller = new JScrollPane();
	
	public Lab2() {
		setLayout(new java.awt.FlowLayout());
		setSize(500,430);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(open); open.addActionListener(this);
		scroller.getViewport().add(result);
		add(scroller);
		add(errors);
	}
	
	public void actionPerformed(ActionEvent evt) {
		result.setText("");	//clear TextArea for next program
		errors.setText("");
		processProgram();
	}
	
	public static void main(String[] args) {
		Lab2 display = new Lab2();
		display.setVisible(true);
	}
	
	String getFileName() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile().getPath();
		else
			return null;
	}
	
/************************************************************************/
/* Put your implementation of the processProgram method here.           */
/* Use the getFileName method to allow the user to select a program.    */
/* Then simulate the execution of that program.                         */
/* You may add any other methods that you think are appropriate.        */
/* However, you should not change anything in the code that I have      */
/* written.                                                             */
/************************************************************************/
	
	// declaring the array list which will store the statements and the hash map which will store the variables with the expressions
		ArrayList<String> inputS = new ArrayList<String>();
		HashMap<String, Double> valueE = new HashMap<String, Double>();
	// current value and previous value 
		int currentVal;
		int oldVal;
		
	// the process program method reads the lines in the program and sets up the array list, hash map, 
    // and initialize variables to be used later
	public void processProgram() {
		String fileName = getFileName();
		if (fileName == null) return;
		this.result.setText("");
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			this.inputS = new ArrayList<>();
		    this.valueE = new HashMap<>();
		    this.inputS.add("");
			String line;
			while ((line=in.readLine()) != null) {
				this.inputS.add(line.trim());
				} in.close();
		} catch (IOException ioe) {
			errors.setText("ERROR: " + ioe);
			return;
		}
		
	// catches the error of END not being a program marker 
		if(!((String)this.inputS.get(this.inputS.size()-1)).equals("END") 
				|| this.inputS.indexOf("END") != this.inputS.size()-1) {
			this.errors.setText("no last statement for END statement");
			return;
		}
	
	// catches error for GOTO nth line execution
		this.oldVal = 0;
		this.currentVal = 1;
		while(true) {
			if(this.currentVal < 1) {
				this.errors.setText("GOTO for " + this.oldVal + " line invalid");
				break;
			}
			if(this.currentVal >= this.inputS.size()) {
				this.errors.setText("GOTO for " + this.oldVal + " line invalid");
				break;
			}
		// termination execution by using the array list to determine the right value
			String s = this.inputS.get(this.currentVal);
			this.oldVal = this.currentVal;
			this.currentVal = this.currentVal+1;
			if(s.equals("END")) {
				break;
			}
			
		// using the statement methods to determine the output after each line input
		// these methods also contain error checking for each type of statements for input/output execution
			try {
				simpleStatement(s);
				continue;
			} catch(Exception e) {
				String line = e.getMessage();
		        if (line.startsWith("In line")) {
		          this.errors.setText(line);
		        } else { 
					this.errors.setText("invalid statement at line " + this.oldVal);
			} 
				
			}
			
			return;
			
		}
		
	}
	
 // split methods using tokens and correct use of END
	void simpleStatement(String s) throws Exception {
		String[] t = s.split("\\s+");
		for(int i=0; i<t.length; i++) {
			if(t[i].equals("END")) {
				throw new Exception("invalid END at line " + this.oldVal);
			}
		}
	// execution for each of the different types of statements with methods for each
		if(t[0].equals("PRINT")) printIn(t);
		else if(t[0].equals("IF")) ifIn(t);
		else if(t[0].equals("GOTO")) gotoIn(t);
		else operate(t);
		
	}
		
    // making sure that the statement PRINT is a variable and has the right amount of tokens
	// then if the value has been declared to be a double, then the string format method will return a string that is
	// the printable decimal value with 2 digits to the right of the decimal point (%.2f)
	void printIn(String[] tok) throws Exception {
		if (!pass(tok[1])) throw new Exception("for type it is invalid at line " + (this.currentVal - 1)); 
		    
		if (tok.length > 2) throw new Exception("token numnber invalid at " + (this.currentVal - 1)); 
		    
		double dv = doubVal(tok[1]);
		this.result.append(String.valueOf(String.format("%.2f", new Object[] {Double.valueOf(dv)})) + "\n");
		}
		  
	
	// making sure that the conditional statement IF has a previous variable and contain IS and THEN
	// once this is passed then the conditional statement methods can be executed 
	void ifIn(String[] tok) throws Exception {
		if (!Character.isLetter(tok[1].charAt(0))) throw new Exception("for type it is invalid at line " + (this.currentVal - 1)); 
		    
		double left = doubVal(tok[1]);
		    
		if (!tok[2].equals("IS")) throw new Exception("missing the is at line " + (this.currentVal - 1)); 
		    
		double right = doubVal(tok[3]);
		    
		if (!tok[4].equals("THEN")) throw new Exception("missing the then at line " + (this.currentVal - 1)); 
		    
		if (left == right) {
		    String s = tok[5];
		    for (int i = 6; i < tok.length; i++)
		        s = String.valueOf(s) + " " + tok[i]; 
		        simpleStatement(s);
		    } 
		  }
		  
	
	// checking the tokens length for the GOTO statement and execution 
	void gotoIn(String[] tok) throws Exception {
		 if (tok.length > 2) throw new Exception("goto tokens over limit at " + (this.currentVal - 1)); 
		 this.currentVal = Integer.parseInt(tok[1]);
		 }
		  
	
	// checking for valid variable type, operator, and expression for the simple statements 
	// also contains the delimiters for the program operation or execution
	void operate(String[] tok) throws Exception {
		  if (!pass(tok[0])) throw new Exception("invalid at line " + (this.currentVal - 1)); 
		  if (!tok[1].equals("=")) throw new Exception("nothing to operate at line " + (this.currentVal - 1)); 
		  
		  double result = doubVal(tok[2]);
		  
		  for (int i = 3; i < tok.length; i += 2) {
		       if (i == tok.length - 1) throw new Exception("invalid at line " + (this.currentVal - 1)); 
		       double term = doubVal(tok[i + 1]);
		      
		        if (tok[i].equals("+")) {
		            result += term;
		        } else if (tok[i].equals("-")) {
		            result -= term;
		        } else if (tok[i].equals("*")) {
		            result *= term;
		        } else if (tok[i].equals("/")) {
		            result /= term;
		        } else {
		            throw new Exception("invalid operator at line " + (this.currentVal - 1));
		        } 
		    } 
		    this.valueE.put(tok[0], Double.valueOf(result));
		  }
		  
		
	// both methods below are error checking the variables and values if they are defined or valid types
		boolean pass(String p) {
			if(!Character.isLetter(p.charAt(0))) return false;
			return true;
		}
		double doubVal(String d) throws Exception {
			if(Character.isLetter(d.charAt(0))) {
			if(!pass(d)) throw new Exception("invalid because of line " + (this.currentVal-1));
			if(this.valueE.containsKey(d)) return ((Double)this.valueE.get(d)).doubleValue();
			throw new Exception("invalid because of line " + (this.currentVal-1));
		}
			return Double.parseDouble(d);
		}
			
		
	
	
}
	
	
	