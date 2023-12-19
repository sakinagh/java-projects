/* Sakina Ghafoor 
 * Lab 2 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab2;

import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;

/**
 * The Model class represents the underlying data and business logic for the ARM Instruction Encoder program.
 * It implements Serializable to support object serialization.
 */
public class Model implements Serializable {

	// optional object reference, if needed to pass an object
	private Object o;  
	
	/**
     * Default constructor for the Model class.
     */ 
	public Model () {}
	
	/**
     * Constructor for the Model class with an optional object parameter.
     * @param fromC The address of the calling object (optional).
     */
	public Model (Object fromC) {o = fromC;}
	
	
	
	/**
     * Converts a binary value to its hexadecimal representation.
     * @param binaryValue The binary value to convert.
     * @return The hexadecimal representation of the binary value as a string.
     */
	public String BinaryToHexConverter(long binaryValue) {
		String hexString = "";

	    // ensure the hexadecimal string is 8 characters long (32 bits)
	    for (int i = 0; i < 8; i++) {
	        int hex = (int) (binaryValue & 15);
	        char hexChar = "0123456789ABCDEF".charAt(hex);
	        hexString = hexChar + hexString;
	        binaryValue = (binaryValue >> 4);
	    }

	    return hexString;
	}

		
	 /**
     * Converts an integer value to its hexadecimal representation.
     * @param x The integer value to convert.
     * @return The hexadecimal representation of the integer value as a string.
     */
	public String displayIntAsHex(int x) {
		String ans="";
		for (int i=0; i<8; i++) {
			int hex = x & 15;
			char hexChar = "0123456789ABCDEF".charAt(hex);
			ans = hexChar + ans;
			x = (x >> 4);
		}
		return ans;
	}

	
	/**
     * Converts an integer value to its binary representation with 32 digits or bits.
     * @param x The integer value to convert.
     * @return The binary representation of the integer value as a string.
     */
	public String displayIntAsBinary(int x) {
		String ans="";
		for(int i=0; i<32; i++) {
			ans = (x & 1) + ans;
			x = (x >> 1);
		}
		return ans;
	}
	
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
