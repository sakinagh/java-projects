/* Sakina Ghafoor 
 * Lab 3 Assignment Fall 2023
 * Techniques in Programming
 */

package Lab3;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * The `View` class represents the graphical user interface (GUI) for a Basic Interpreter.
 * It provides buttons for reading, saving, executing, and resetting a program, as well
 * as text areas for display and error messages.
 */
public class View extends JFrame {

	final boolean verbose = true;
	
	protected JButton read = new JButton("read");
	protected JButton save = new JButton("save");
	protected JButton execute = new JButton("run");
	protected JButton reset = new JButton("reset");
	
	protected JTextArea display = new JTextArea(150,50);
	protected JTextArea error = new JTextArea(50, 100);
	
	protected JScrollPane sp = new JScrollPane(display);
	protected JScrollPane sp3 = new JScrollPane(error);
	
	protected JPanel panel = new JPanel(new GridLayout(1,4));
	
	protected Color tcu = new Color(77,25,121);
	
	protected JFrame console = new JFrame("output console");
	protected JTextArea consoleLines = new JTextArea(80,60);
	protected JScrollPane sp2 = new JScrollPane(consoleLines);
	
	/**
     * Constructs an instance of the `View` class.
     * This method initializes the graphical user interface (GUI) components, including buttons,
     * text areas for display and error messages, and an output console window.
     */
	public View() {
		setTitle("Basic Interpreter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    setBounds(0,0,400,300);
	    
		panel.add(read);
		panel.add(save);
		panel.add(execute);		
		panel.add(reset);
		
		display.setEditable(true);
		//error.setEditable(false);
		
		add(panel, BorderLayout.NORTH);
	    add(sp, BorderLayout.CENTER);
	    //add(sp3, BorderLayout.SOUTH);
	    
	    console.setLayout(new BorderLayout());
		console.setBounds(200,500,640,200);
		consoleLines.setEditable(false);
		console.add(sp2, BorderLayout.CENTER);
		console.setVisible(true);
	    
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View();
	}
	
}
