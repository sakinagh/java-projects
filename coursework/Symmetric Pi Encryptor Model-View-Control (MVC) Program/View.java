/* Sakina Ghafoor 
 * Lab 4 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab4;

import javax.swing.*;
//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * The View class represents the graphical user interface for symmetric file encryption using Pi digits.
 * It provides input fields, buttons, and labels to interact with the encryption process.
 */
public class View extends JFrame {

	protected final boolean verbose = true;
	
	protected JTextField inputFileName = new JTextField(15);
	protected JLabel  inputFileLabel = new JLabel("Input File ",JLabel.CENTER);
	
	protected JTextField outputFileName = new JTextField(15);
	protected JLabel  outputFileLabel = new JLabel("Output File ",JLabel.CENTER);
	protected JLabel  output2FileLabel = new JLabel("Idle ",JLabel.CENTER);
	
	protected JTextField encryptorData = new JTextField(15);
	protected JLabel  encryptorJLabel = new JLabel("Encryptor Info ",JLabel.RIGHT);
	
	protected JButton  openB = new JButton("Read");
	protected JButton  encryptB = new JButton("Encrypt & Save");
	protected JButton  clearB = new JButton("Reset");
	
	protected Color col = new Color(70, 89, 110);
	protected JPanel  displayPanel = new JPanel(new GridLayout(5,2,5,5));
	
	/**
     * The main method to instantiate the View class for testing purposes.
     * @param args Command-line arguments (not used).
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View();
	}
	
	
	/**
     * Constructs the View, setting up the graphical user interface for symmetric file encryption.
     * It includes input fields, buttons, and labels with proper formatting and styling.
     */
	public View() {
		if(verbose)  System.out.println("View Constructor");  // display in the console 
        setTitle("PI Encryption Symmetric View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        encryptorJLabel.setForeground(Color.WHITE); 
        inputFileLabel.setForeground(Color.WHITE); 
        outputFileLabel.setForeground(Color.WHITE); 
	    displayPanel.setBackground(col);  
	    
	    displayPanel.add(encryptorJLabel);
	    displayPanel.add(encryptorData);
	    encryptorData.setEditable(false);
	    displayPanel.add(openB); 
	    displayPanel.add(encryptB);
	    displayPanel.add(inputFileLabel); 
	    displayPanel.add(outputFileLabel);
	    displayPanel.add(inputFileName); 
	    displayPanel.add(outputFileName);	 
	    displayPanel.add(clearB);
	    displayPanel.add(output2FileLabel);
	    add(displayPanel); 
	    
	    output2FileLabel.setFont(new Font("Helvetica", Font.ITALIC,20));
	    output2FileLabel.setForeground(Color.WHITE);
	    outputFileLabel.setFont(new Font("Helvetica", Font.BOLD,14));
	    inputFileLabel.setFont(new Font("Helvetica", Font.BOLD,14));
	    setBounds(100,100,400,250);
	    setVisible(true); 

	}

}

