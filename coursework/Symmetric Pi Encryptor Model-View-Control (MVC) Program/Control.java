/* Sakina Ghafoor 
 * Lab 4 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab4;

import javax.swing.*;
import java.awt.FileDialog;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * The Control class extends the View class and serves as the controller for the symmetric file encryption application.
 * It handles user interactions and manages the encryption process.
 */
public class Control extends View implements ActionListener,WindowListener {

	protected Model model = new Model();
	protected Model crypt;
	
	File inputF = null;
    File outputF = null;
    
	
    /**
     * Constructs a Control object, initializing the user interface and setting up event listeners.
     */
    public Control() 
	   {   super();
	       if(verbose)  System.out.println("Control Constructor");  // display in the console 
		   // Report the buttons to be listened
	       setTitle("PI Symmetric Control Encryptor");
		   openB.addActionListener(this);
		   encryptB.addActionListener(this);
		   clearB.addActionListener(this);
		   this.addWindowListener(this);
		   } 
		   
	
	
	/**
     * Handles action events triggered by buttons in the user interface.
     * @param e The ActionEvent associated with the button click.
     */   
	public void actionPerformed(ActionEvent e) {
		String whichButton = e.getActionCommand();  // determines which button generated the event evento
		if (whichButton.equals("Reset"))
			processResetB();
		if (whichButton.equals("Read"))
			getFile(true);	
		if (whichButton.equals("Encrypt & Save"))
			processFileEncrypt();	
		 validate();repaint();
	    inputFileName.requestFocus();  // pay attention to this textfield
	 }
	
	/**
     * Clears the input and output fields, resetting the user interface to its initial state.
     */
	public void processResetB()
    {  String stringValue = null;  
	     inputFileName.setText(stringValue); // display cleared
	     outputFileName.setText(stringValue); // display cleared 
	     encryptorData.setText(stringValue); // display cleared
	     output2FileLabel.setText("Idle ");
	 	    if(verbose)  System.out.println("Both textfields are cleared " );  // display in console
     } // end process Reset
	
	/**
     * Initiates the file encryption process, updating the user interface accordingly.
     */
	public void processFileEncrypt()
     {   output2FileLabel.setText("Working");
	     getFile(false);
	     try { crypt = new Model();
               crypt.encryptFile(inputF, outputF,encryptorData.getText() );
               encryptorData.setText("To decode read output");
               output2FileLabel.setText("Idle");
    
      } 
         catch (IOException e) {  System.out.println(e.getMessage() );} 
      } //
	
	/**
     * Gets the selected file from the user using a file dialog.
     * @param opt True if reading, false if saving.
     * @return The selected file.
     */
	 public File getFile(boolean opt) {
			// Put up a file dialog to allow the user to select an input file
			
			FileDialog d;
			if(opt)  d = new FileDialog(this,"Open...", FileDialog.LOAD);
			   else  d = new FileDialog(this,"Save...", FileDialog.SAVE);
			//d.setDirectory(".");
			d.setVisible(true);
			if (d.getFile() != null)
	         { if (opt) inputFileName.setText(d.getFile());
	           else outputFileName.setText(d.getFile());
	         }
			if(opt) { inputF = new File(d.getDirectory(), d.getFile());
			          return inputF; }
		            {  outputF = new File(d.getDirectory(), d.getFile());
		               return outputF; }
		           
		}
	
	/**
     * Main method for testing purposes.
     * @param args Command-line arguments (not used).
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Control();
	}



	@Override
	public void windowOpened(WindowEvent e) {	}

	@Override
	public void windowClosing(WindowEvent e) {	}

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

}
