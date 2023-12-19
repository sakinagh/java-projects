/* Sakina Ghafoor 
 * Lab 3 Assignment Fall 2023
 * Techniques in Programming
 */

package Lab3;

import javax.swing.*;
import java.awt.FileDialog;
import java.awt.event.*;
import java.io.*;
import java.util.*;


/**
 * The `Model` class represents a model for evaluating mathematical expressions and assignments.
 * It provides methods for evaluating expressions and assignment statements.
 */
public class Model implements Serializable {
	
    protected boolean verbose = true;
    public Hashtable<String,Double> symbolTable; 
	public ExpressionTokenizerVar tokenizer;
	
	public String LHS; 
    public double RHS;
	
	private Object o;
	
    /**
     * Default constructor for the `Model` class.
     */
	public Model() { }
	
	/**
     * Constructor that accepts an object as a parameter.
     * @param fromC The object to initialize the `Model` with.
     */
	public Model(Object fromC) {o = fromC;}
	
	/**
     * Constructor that accepts a symbol table as a parameter.
     * @param var The symbol table to initialize the `Model` with.
     */
	public Model(Hashtable<String,Double> var) { 
		 symbolTable = var; 
	  }
	 
	
	/**
     * Evaluates the assignment expression.
     * @param Expression The assignment expression to evaluate.
     */
	public void getAssignmentValue(String Expression) {
	    /*Variable value = new Variable();*/
	    RHS = 0;
	    tokenizer = new ExpressionTokenizerVar(Expression);
	    String next = tokenizer.peekToken();
		LHS = tokenizer.nextToken();  // determine variable name 
	    next = tokenizer.peekToken();  
	    if ("=".equals(next) )
			   { tokenizer.nextToken();  // discard =
			     RHS = getExpressionValue();   // determine variable value
			    }
			   else System.out.println  ("Assignment error" ); 
	    if (verbose)  System.out.println  ("value delivered Assign " + RHS); 	   
		//return var.put(LHS, RHS);
	}

	/**
	 * Evaluates the expression.
	 * @return the value of the expression
	 */
	public double getExpressionValue() {  
		double value = getTermValue();  // go search for * /  terms
		boolean done = false;
		while(!done) {
			String next = tokenizer.peekToken();
			if ("+".equals(next) || "-".equals(next)) {
				tokenizer.nextToken();	//Discard the "+" or "-"
				double value2 = getTermValue();
				if ("+".equals(next)) value += value2;
				else value -= value2;
			}
			else done = true;
		}
		return value;
	}
	
	/**
	 * Evaluates the next term in the expression.
	 * @return the value of the term
	 */
	public double getTermValue() {
		double value = getFactorValue(); // go search for ( )  
		boolean done = false;
		while(!done) {
			String next = tokenizer.peekToken();
			if ("*".equals(next) || "/".equals(next)) {
				tokenizer.nextToken();	//Discard the "*" or "/"
				double value2 = getFactorValue();
				if ("*".equals(next)) value *= value2;
				else value /= value2;
			}
			else done = true;
		}
		return value;
	}
	
	/**
	 * Evaluates the next factor found in the expression.
	 * @return the value of the factor
	 */
	public double getFactorValue() {
		double value; String variable;
		String next = tokenizer.peekToken();
		if ("(".equals(next)) {
			tokenizer.nextToken();	//Discard the "("
			value = getExpressionValue();  //  recursively go back to expression value
			tokenizer.nextToken();	//Discard the ")"
		}
		  else { variable = tokenizer.nextToken();
		         try { value = Double.parseDouble(variable); }
               catch (NumberFormatException e)      
                 {  // try a set of Coded values for  value in the hashtable
                    if (symbolTable.containsKey(variable)) 
                       value = (double) (symbolTable.get(variable)) ;
                       else value = 0;
               }
             }   
      if (verbose)  System.out.println  ("Factor value delivered " + value); 
      return value;
	}	



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
			

}
