/* Sakina Ghafoor 
 * Lab 2 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab2;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * The View class represents the graphical user interface (GUI) for the ARM Instruction Encoder program.
 * It extends the JFrame class and provides fields and buttons to input, encode, and decode ARM assembly instructions.
 */
public class View extends JFrame {

	// GUI components that are utilized in the Control methods
	static long serialVersionUID = 1l;
	protected JTextField assemblerInstruction;
	protected JTextField binaryInstruction;
	protected JTextField hexInstruction;
	protected JLabel errorLabel;
	
	protected JButton btnEncode = new JButton("Encode");
	protected JButton btnDecode = new JButton("Decode Binary");
	protected JButton btnDecodeHex = new JButton("Decode Hex");

	/**
     * The main method that starts the program by creating a new View object.
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View();
	}
	
	
	/**
     * Constructs a new View object and initializes the graphical user interface (GUI) components.
     */
	public View() {
		setTitle("ARM Instruction Encoder");
		this.setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAssemblyLanguage = new JLabel("Assembly Language");
		lblAssemblyLanguage.setBounds(30, 64, 160, 16);
		JLabel lblBinary = new JLabel("Binary Instruction");
		lblBinary.setBounds(30, 155, 190, 16);
		JLabel lblHexEquivalent = new JLabel("Hex Instruction");
		lblHexEquivalent.setBounds(30, 260, 131, 16);
		
		btnEncode.setBounds(200, 25, 117, 29);
		btnDecode.setBounds(200, 150, 150, 29);
		btnDecodeHex.setBounds(200, 220, 150, 29);
		
		assemblerInstruction = new JTextField();
		assemblerInstruction.setBounds(25, 24, 134, 28);
		getContentPane().add(assemblerInstruction);
		
		binaryInstruction = new JTextField();
		binaryInstruction.setBounds(25, 115, 330, 28);
		getContentPane().add(binaryInstruction);
		
		hexInstruction = new JTextField();
		hexInstruction.setBounds(25, 220, 134, 28);
		getContentPane().add(hexInstruction);

		getContentPane().add(lblAssemblyLanguage);
		getContentPane().add(btnEncode);
		getContentPane().add(lblBinary);		
		getContentPane().add(btnDecode);
		getContentPane().add(lblHexEquivalent);
		getContentPane().add(btnDecodeHex);
		
		errorLabel = new JLabel("");
		errorLabel.setBounds(25, 320, 280, 16);
		getContentPane().add(errorLabel);
		
		setVisible(true);
	}
	
	

	/*
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}*/

}
