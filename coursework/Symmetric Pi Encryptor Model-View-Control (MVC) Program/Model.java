/* Sakina Ghafoor 
 * Lab 4 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab4;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * The Model class represents the core logic for symmetric file encryption using the digits of Pi.
 * It provides methods to encrypt files and streams using the first digits of Pi as keys.
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
		
		protected final boolean verbose = true;
	    protected int key;
	    protected static final String piDigits = "314159265358979323846264338327950288419716939937510582097494459230781640628620";
		protected static int piIndex = 0;
		
		
		/**
	     * Encrypts the content of a file using the first digits of Pi as keys.
	     * @param inFile   The input file to be encrypted.
	     * @param outFile  The output file to store the encrypted content.
	     * @param possKey  The optional key for encryption (not used, as Pi digits are used).
	     * @throws IOException If an I/O error occurs during file processing.
	     */
		public void encryptFile(File inFile, File outFile, String possKey) throws IOException {
			   InputStream in = null;
			   OutputStream out = null;

			   try {
			      in = new FileInputStream(inFile);
			      out = new FileOutputStream(outFile);
			      encryptStream(in, out, possKey);
			   } finally {
			      if (in != null) in.close();
			      if (out != null) out.close();
			   }      
		}
		
		/**
	     * Encrypts the content of an input stream using the first digits of Pi as keys.
	     * @param in       The input stream to be encrypted.
	     * @param out      The output stream to store the encrypted content.
	     * @param possKey  The optional key for encryption (not used, as Pi digits are used).
	     * @throws IOException If an I/O error occurs during stream processing.
	     */
		public void encryptStream(InputStream in, OutputStream out, String possKey) throws IOException { 
			   boolean done = false;
			   while(!done) {
			      int next = in.read();
			      if (next == -1) done = true;
			      else {
			         byte b = (byte) next;
			         byte c = encrypt(b, possKey);
			         out.write(c);
			      }
			   }
			   if(verbose)  System.out.println("The Key is " + key );  // display in console
			}
		
		/**
	     * Encrypts a byte using XOR with the next digit of Pi as the key.
	     * @param b        The byte to be encrypted.
	     * @param possKey  The optional key for encryption (not used, as Pi digits are used).
	     * @return The encrypted byte.
	     */
		@SuppressWarnings("finally")
		public byte encrypt(byte b, String possKey) {  
			try { 
				//key = Integer.parseInt(possKey);
				piIndex = 0;
				key = getNextPiDigit();
			} catch (NumberFormatException e) {  
				
			} 
			finally { 
				//return (byte) (b + key); 
				return (byte) (b ^ key);
			}
		}

		/**
	     * Gets the next digit of Pi and advances the index.
	     * @return The next digit of Pi.
	     */
	    public int getNextPiDigit() {
	    	int digit = Character.getNumericValue(piDigits.charAt(piIndex));
	        piIndex = (piIndex + 1) % piDigits.length();
	        return digit;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
