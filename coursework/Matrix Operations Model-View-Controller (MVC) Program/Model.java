package Lab1;

import javax.swing.*;
import java.io.*;
import javax.swing.JTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* Sakina Ghafoor 
 * Lab 1 Assignment Fall 2023
 * Techniques in Programming
 */

public class Model implements Serializable { 
	
	public Model() {	
	}
	
	// handle input and output of files by using input streams to read files that the user chooses 
	// catches errors using a try catch block to prevent issues with exceptions 
	@SuppressWarnings("finally")
	public double[][] readF(String filename) {
		double[][] arr = null;
		try {
			FileInputStream fis = new FileInputStream(new File(filename));
			ObjectInputStream ois = new ObjectInputStream(fis);
			arr = (double[][]) ois.readObject();
			ois.close();
			fis.close();
			System.out.println("The file is opened and read as an object");
			return arr;
		}  catch(FileNotFoundException e) {
			System.out.println("File not found, try again"); 
		} catch(ClassNotFoundException e) {
			System.out.println("Class not found exception error from Model");
		} catch(IOException e) {
			System.out.println("Error initializing stream from Model"); 
		} finally { 
			return arr;
		}
	}
	
	// handle input and output of files by using output streams to save files that the user chooses as objects 
	// catches errors using a try catch block to prevent issues with exceptions 
	public void saveF(String f, double[][] a) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(f));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(a);
			fos.close();
			oos.close();
			System.out.println("The file was saved as an object");
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch(IOException e) {
			System.out.println("Error in stream input/output");
		}
	}
	
	// executes the multiplication operation for the matrix program 
	// utilizes a triple for loop to ensure the columns and rows are accurately operated 
	public double[][] multiply(double[][] arrX, double[][] arrY) {
		int xr = arrX.length; 
		int xc = arrX[0].length;
		int yc = arrY[0].length;
		
		double[][] output = new double[xr][yc];
		
		for(int x=0; x<xr; x++) {
			for(int y=0; y<yc; y++) {
				output[x][y] = 0;
				for(int z=0; z<xc; z++) { 
					output[x][y] += (arrX[x][z] * arrY[z][y]);
				}
			}
		}
		return output;
		}
	
	// executes the transpose function for a single matrix in the program
	// utilizes a double for loop to invert or transpose the rows and columns 
	public double[][] transpose(double[][] arrX) {
		int numberRow = arrX.length;
		int numberCol = arrX[0].length;
		
		double[][] output = new double[numberCol][numberRow];
		
		for(int a=0; a<numberRow; a++) {
			for(int b=0; b<numberCol; b++) {
				output[b][a] = arrX[a][b];
			}
		}
		return output;
	}
	
	// executes the addition function for the matrix program
	// utilizes a double for loop to perform operations for adding or subtracting the correlated values 
	// the method determines which operation to perform based on the 'oper' method in the if-else statement 
	public double[][] add(boolean oper, double[][] arrX, double[][] arrY) {
		int numberRow = arrX.length;
		int numberCol = arrX[0].length;
		
		double[][] output = new double[numberRow][numberCol];
		
		for(int g=0; g<numberRow; g++) {
			for(int h=0; h<numberCol; h++) {
				if(oper) output[g][h] = arrX[g][h] + arrY[g][h];
				else output[g][h] = arrX[g][h] - arrY[g][h];
			}
		}
		return output;
	}

}
