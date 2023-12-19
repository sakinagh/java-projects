/* Sakina Ghafoor 
 * Lab 2 Assignment Fall 2023
 * Techniques in Programming
 */

package Lab2;

import java.util.StringTokenizer;
import javax.swing.*;
import Lab2.Model;
import Lab2.View;
import java.awt.event.*;
import java.util.*;


/**
 * The Control class represents the controller in the MVC (Model-View-Controller) architecture
 * for a program that encodes, decodes, and displays assembly instructions in binary and hexadecimal formats.
 * It extends the View class and implements the ActionListener interface.
 */
public class Control extends View implements ActionListener {

	/**
     * The Model instance used for encoding and decoding instructions.
     */
	protected Model model = new Model();
	
	// used to put together binary instruction and hold constant values
	protected String conditionAndFormat = "1110000";
	protected String instM = "";
	protected String instReg1 = "";
	protected String instDesReg = "";
	protected String shifts = "00000000";
	protected String instReg2 = "";

	
	/**
     * Constructs a new Control object. Registers action listeners for buttons.
     */
	public Control() {
		btnDecodeHex.addActionListener(this);	
		btnEncode.addActionListener(this);	
		btnDecode.addActionListener(this);
	}
	
	/**
     * Handles action events triggered by buttons.
     * @param evt The action event.
     */
	public void actionPerformed(ActionEvent evt) {
		errorLabel.setText("");
		if (evt.getActionCommand().equals("Encode")) {
			encode();
		} else if (evt.getActionCommand().equals("Decode Binary")) {
			decodeBin();
		} else if (evt.getActionCommand().equals("Decode Hex")) {
			decodeHex();
		}
	}
	
	/**
     * Encodes an assembly instruction provided by the user and displays it in binary and hexadecimal formats.
     * @throws IllegalArgumentException If the user input is invalid.
     */
	public void encode() {
		binaryInstruction.setText("");
		hexInstruction.setText("");
		
		String assembly = assemblerInstruction.getText().trim().toUpperCase();
		// break the assembly instruction into two pieces and check for errors
		StringTokenizer tok = new StringTokenizer(assembly," ");
		if (tok.countTokens() != 2) {
			errorLabel.setText("ERROR - Illegal assembly format");
			return;
		}
		String mnemonic = tok.nextToken();
		String regs = tok.nextToken();

		// find the correct op code and check for errors in user input 
		if(mnemonic.compareToIgnoreCase("AND")==0) instM = "0000" + "0";
		else if(mnemonic.compareToIgnoreCase("EOR")==0) instM = "0001" + "0";
		else if(mnemonic.compareToIgnoreCase("SUB")==0) instM = "0010" + "0";
		else if(mnemonic.compareToIgnoreCase("RSB")==0) instM = "0011" + "0";
		else if(mnemonic.compareToIgnoreCase("ADD")==0) instM = "0100" + "0";
		else if(mnemonic.compareToIgnoreCase("ADC")==0) instM = "0101" + "0";
		else if(mnemonic.compareToIgnoreCase("SBC")==0) instM = "0110" + "0";
		else if(mnemonic.compareToIgnoreCase("RSC")==0) instM = "0111" + "0";
		else if(mnemonic.compareToIgnoreCase("TST")==0) instM = "1000" + "0";
		else if(mnemonic.compareToIgnoreCase("TEQ")==0) instM = "1001" + "0";
		else if(mnemonic.compareToIgnoreCase("CMP")==0) instM = "1010" + "0";
		else if(mnemonic.compareToIgnoreCase("CMN")==0) instM = "1011" + "0";
		else if(mnemonic.compareToIgnoreCase("ORR")==0) instM = "1100" + "0";
		else if(mnemonic.compareToIgnoreCase("MOV")==0) instM = "1101" + "0";
		else if(mnemonic.compareToIgnoreCase("BIC")==0) instM = "1110" + "0";
		else if(mnemonic.compareToIgnoreCase("MVN")==0) instM = "1111" + "0";
		else {
			errorLabel.setText("ERROR - Invalid mnemonic");
			throw new IllegalArgumentException("ERROR - Invalid mnemonic"); 
			//return;
		}

		// separate the 3 registers using tokenizer and check user input format
		String regsToken = regs;
		StringTokenizer tok2 = new StringTokenizer(regsToken,",");
		if (tok2.countTokens() != 3) {
			errorLabel.setText("ERROR - Illegal register format");
			return;
		}
		
		String desReg = tok2.nextToken();
		instDesReg = "";
		String reg1 = tok2.nextToken();
		instReg1 = "";
		String reg2 = tok2.nextToken();
		instReg2 = "";
		
		// find the correct register and check for errors in user input
		if(reg1.compareToIgnoreCase("r0")==0) instReg1 = "0000";
		else if(reg1.compareToIgnoreCase("r1")==0) instReg1 = "0001";
		else if(reg1.compareToIgnoreCase("r2")==0) instReg1 = "0010";
		else if(reg1.compareToIgnoreCase("r3")==0) instReg1 = "0011";
		else if(reg1.compareToIgnoreCase("r4")==0) instReg1 = "0100";
		else if(reg1.compareToIgnoreCase("r5")==0) instReg1 = "0101";
		else if(reg1.compareToIgnoreCase("r6")==0) instReg1 = "0110";
		else if(reg1.compareToIgnoreCase("r7")==0) instReg1 = "0111";
		else if(reg1.compareToIgnoreCase("r8")==0) instReg1 = "1000";
		else if(reg1.compareToIgnoreCase("r9")==0) instReg1 = "1001";
		else if(reg1.compareToIgnoreCase("r10")==0) instReg1 = "1010";
		else if(reg1.compareToIgnoreCase("r11")==0) instReg1 = "1011";
		else if(reg1.compareToIgnoreCase("r12")==0) instReg1 = "1100";
		else if(reg1.compareToIgnoreCase("r13")==0) instReg1 = "1101";
		else if(reg1.compareToIgnoreCase("r14")==0) instReg1 = "1110";
		else if(reg1.compareToIgnoreCase("r15")==0) instReg1 = "1111";
		else {
			errorLabel.setText("ERROR - Invalid OP 1 register");
			throw new IllegalArgumentException("ERROR - Invalid OP 1 register");
			//return;
		}
		
		// find the correct register and check for errors in user input
		if(desReg.compareToIgnoreCase("r0")==0) instDesReg = "0000";
		else if(desReg.compareToIgnoreCase("r1")==0) instDesReg = "0001";
		else if(desReg.compareToIgnoreCase("r2")==0) instDesReg = "0010";
		else if(desReg.compareToIgnoreCase("r3")==0) instDesReg = "0011";
		else if(desReg.compareToIgnoreCase("r4")==0) instDesReg = "0100";
		else if(desReg.compareToIgnoreCase("r5")==0) instDesReg = "0101";
		else if(desReg.compareToIgnoreCase("r6")==0) instDesReg = "0110";
		else if(desReg.compareToIgnoreCase("r7")==0) instDesReg = "0111";
		else if(desReg.compareToIgnoreCase("r8")==0) instDesReg = "1000";
		else if(desReg.compareToIgnoreCase("r9")==0) instDesReg = "1001";
		else if(desReg.compareToIgnoreCase("r10")==0) instDesReg = "1010";
		else if(desReg.compareToIgnoreCase("r11")==0) instDesReg = "1011";
		else if(desReg.compareToIgnoreCase("r12")==0) instDesReg = "1100";
		else if(desReg.compareToIgnoreCase("r13")==0) instDesReg = "1101";
		else if(desReg.compareToIgnoreCase("r14")==0) instDesReg = "1110";
		else if(desReg.compareToIgnoreCase("r15")==0) instDesReg = "1111";
		else {
			errorLabel.setText("ERROR - Invalid Dest register");
			throw new IllegalArgumentException("ERROR - Invalid Dest register");
			//return;
		}
		
		// find the correct register and check for errors in user input
		if(reg2.compareToIgnoreCase("r0")==0) instReg2 = "0000";
		else if(reg2.compareToIgnoreCase("r1")==0) instReg2 = "0001";
		else if(reg2.compareToIgnoreCase("r2")==0) instReg2 = "0010";
		else if(reg2.compareToIgnoreCase("r3")==0) instReg2 = "0011";
		else if(reg2.compareToIgnoreCase("r4")==0) instReg2 = "0100";
		else if(reg2.compareToIgnoreCase("r5")==0) instReg2 = "0101";
		else if(reg2.compareToIgnoreCase("r6")==0) instReg2 = "0110";
		else if(reg2.compareToIgnoreCase("r7")==0) instReg2 = "0111";
		else if(reg2.compareToIgnoreCase("r8")==0) instReg2 = "1000";
		else if(reg2.compareToIgnoreCase("r9")==0) instReg2 = "1001";
		else if(reg2.compareToIgnoreCase("r10")==0) instReg2 = "1010";
		else if(reg2.compareToIgnoreCase("r11")==0) instReg2 = "1011";
		else if(reg2.compareToIgnoreCase("r12")==0) instReg2 = "1100";
		else if(reg2.compareToIgnoreCase("r13")==0) instReg2 = "1101";
		else if(reg2.compareToIgnoreCase("r14")==0) instReg2 = "1110";
		else if(reg2.compareToIgnoreCase("r15")==0) instReg2 = "1111";
		else {
			errorLabel.setText("ERROR - Invalid OP 2 register");
			throw new IllegalArgumentException("ERROR - Invalid OP 2 register");
			//return;
		}
		
		// put together the binary string and convert to hex 
		// set binary and hex as text output and catch number format errors 
		try {
		String instB = conditionAndFormat + instM + instReg1 + instDesReg + shifts + instReg2;
		String hexValue = model.BinaryToHexConverter(Long.parseLong(instB, 2)); 
		hexInstruction.setText(hexValue);
		binaryInstruction.setText(instB);
		} catch(NumberFormatException ex) {
			System.out.println(ex);
		}
		
	
	}
		
	

	/**
     * Decodes a binary instruction provided by the user and displays the result in hexadecimal and assembly format. 
     * Uses another called method to convert input to assembly instruction. 
     */
	public void decodeBin() {
		assemblerInstruction.setText("");
		hexInstruction.setText("");
		long iBin = 0;
		String bin = binaryInstruction.getText().trim();
		if (bin.length() != 32) {
			errorLabel.setText("ERROR - binary must be 32 digits");
			return;
		}
		try {
			iBin = Long.parseLong(bin,2);
		} catch(NumberFormatException nfe) {
			errorLabel.setText("ERROR - illegal binary number");
			return;
		}
		String hexValue = model.displayIntAsHex((int)iBin); 
		hexInstruction.setText(hexValue);
		String assembly = bin2Assembly(bin);
		assemblerInstruction.setText(assembly);
	}

	
	/**
     * Decodes a hexadecimal instruction provided by the user and displays the result in binary and assembly format.
     * Uses another called method to convert input to assembly instruction.
     */
	public void decodeHex() {
		binaryInstruction.setText("");
		assemblerInstruction.setText("");
		long iHex = 0;
		String hex = hexInstruction.getText().trim();
		if (hex.length() != 8 && hex.length() != 4) {
			errorLabel.setText("ERROR - hex must be 8 digits");
			return;
		}
		try {
			iHex = Long.parseLong(hex,16);
		} catch(NumberFormatException nfe) {
			errorLabel.setText("ERROR - illegal hex number");
			return;
		}
		String binaryValue = model.displayIntAsBinary((int)iHex); 
		binaryInstruction.setText(binaryValue);
		String assembly = bin2Assembly(binaryValue);		
		assemblerInstruction.setText(assembly);
	}
	
	
	/**
     * Converts a binary instruction to assembly format.
     * @param bin The binary instruction as a string.
     * @return The assembly instruction.
     */
	public String bin2Assembly(String bin) {
		String assembly = "";
		String binS = bin;
		String opcode = "";
		String r1 = "";
		String dr = "";
		String r2 = "";
		
		// find the correct op code and check for errors in user input
		if(binS.substring(7,11).equals("0000")) opcode = "AND";
		else if(binS.substring(7,11).equals("0001")) opcode = "EOR";
		else if(binS.substring(7,11).equals("0010")) opcode = "SUB";
		else if(binS.substring(7,11).equals("0011")) opcode = "RSB";
		else if(binS.substring(7,11).equals("0100")) opcode = "ADD";
		else if(binS.substring(7,11).equals("0101")) opcode = "ADC";
		else if(binS.substring(7,11).equals("0110")) opcode = "SBC";
		else if(binS.substring(7,11).equals("0111")) opcode = "RSC";
		else if(binS.substring(7,11).equals("1000")) opcode = "TST";
		else if(binS.substring(7,11).equals("1001")) opcode = "TEQ";
		else if(binS.substring(7,11).equals("1010")) opcode = "CMP";
		else if(binS.substring(7,11).equals("1011")) opcode = "CMN";
		else if(binS.substring(7,11).equals("1100")) opcode = "ORR";
		else if(binS.substring(7,11).equals("1101")) opcode = "MOV";
		else if(binS.substring(7,11).equals("1110")) opcode = "BIC";
		else if(binS.substring(7,11).equals("1111")) opcode = "MVN";
		else {
			errorLabel.setText("ERROR - Invalid mnemonic");
			throw new IllegalArgumentException("ERROR - Invalid mnemonic"); 
		}
		
		// find the correct register and check for errors in user input
		if(binS.substring(12,16).equals("0000")) r1 = "r0";
		else if(binS.substring(12,16).equals("0001")) r1 = "r1";
		else if(binS.substring(12,16).equals("0010")) r1 = "r2";
		else if(binS.substring(12,16).equals("0011")) r1 = "r3";
		else if(binS.substring(12,16).equals("0100")) r1 = "r4";
		else if(binS.substring(12,16).equals("0101")) r1 = "r5";
		else if(binS.substring(12,16).equals("0110")) r1 = "r6";
		else if(binS.substring(12,16).equals("0111")) r1 = "r7";
		else if(binS.substring(12,16).equals("1000")) r1 = "r8";
		else if(binS.substring(12,16).equals("1001")) r1 = "r9";
		else if(binS.substring(12,16).equals("1010")) r1 = "r10";
		else if(binS.substring(12,16).equals("1011")) r1 = "r11";
		else if(binS.substring(12,16).equals("1100")) r1 = "r12";
		else if(binS.substring(12,16).equals("1101")) r1 = "r13";
		else if(binS.substring(12,16).equals("1110")) r1 = "r14";
		else if(binS.substring(12,16).equals("1111")) r1 = "r15";
		else {
			errorLabel.setText("ERROR - Invalid OP 1 register");
			throw new IllegalArgumentException("ERROR - Invalid OP 1 register");
		}
		
		// find the correct register and check for errors in user input
		if(binS.substring(16,20).equals("0000")) dr = "r0";
		else if(binS.substring(16,20).equals("0001")) dr = "r1";
		else if(binS.substring(16,20).equals("0010")) dr = "r2";
		else if(binS.substring(16,20).equals("0011")) dr = "r3";
		else if(binS.substring(16,20).equals("0100")) dr = "r4";
		else if(binS.substring(16,20).equals("0101")) dr = "r5";
		else if(binS.substring(16,20).equals("0110")) dr = "r6";
		else if(binS.substring(16,20).equals("0111")) dr = "r7";
		else if(binS.substring(16,20).equals("1000")) dr = "r8";
		else if(binS.substring(16,20).equals("1001")) dr = "r9";
		else if(binS.substring(16,20).equals("1010")) dr = "r10";
		else if(binS.substring(16,20).equals("1011")) dr = "r11";
		else if(binS.substring(16,20).equals("1100")) dr = "r12";
		else if(binS.substring(16,20).equals("1101")) dr = "r13";
		else if(binS.substring(16,20).equals("1110")) dr = "r14";
		else if(binS.substring(16,20).equals("1111")) dr = "r15";
		else {
			errorLabel.setText("ERROR - Invalid Dest register");
			throw new IllegalArgumentException("ERROR - Invalid Dest register");
		}
		
		// find the correct register and check for errors in user input
		if(binS.substring(28).equals("0000")) r2 = "r0";
		else if(binS.substring(28).equals("0001")) r2 = "r1";
		else if(binS.substring(28).equals("0010")) r2 = "r2";
		else if(binS.substring(28).equals("0011")) r2 = "r3";
		else if(binS.substring(28).equals("0100")) r2 = "r4";
		else if(binS.substring(28).equals("0101")) r2 = "r5";
		else if(binS.substring(28).equals("0110")) r2 = "r6";
		else if(binS.substring(28).equals("0111")) r2 = "r7";
		else if(binS.substring(28).equals("1000")) r2 = "r8";
		else if(binS.substring(28).equals("1001")) r2 = "r9";
		else if(binS.substring(28).equals("1010")) r2 = "r10";
		else if(binS.substring(28).equals("1011")) r2 = "r11";
		else if(binS.substring(28).equals("1100")) r2 = "r12";
		else if(binS.substring(28).equals("1101")) r2 = "r13";
		else if(binS.substring(28).equals("1110")) r2 = "r14";
		else if(binS.substring(28).equals("1111")) r2 = "r15";
		else {
			errorLabel.setText("ERROR - Invalid OP 2 register");
			throw new IllegalArgumentException("ERROR - Invalid OP 2 register");
		}		
		
		// piece together the assembly instruction and return the string output 
		assembly = opcode + " " + dr + "," + r1 + "," + r2;
		return assembly;
	}


	/**
     * The main method that starts the program by creating a new Control object.
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Control();
	}
	
}
