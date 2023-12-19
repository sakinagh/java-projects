package Lab1;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.*;

/* Sakina Ghafoor 
 * Lab 1 Assignment Fall 2023
 * Techniques in Programming
 */

public class View extends JFrame {
	// GUI components of the matrix program 
	// facilitate user interactions, input, and output 
	
	// implements the frame, buttons, labels, text fields, panels, and selections using the Java Swing library 
	protected JFrame frameX = new JFrame("Matrix 1");
	protected JFrame frameY = new JFrame("Matrix 2");
	protected JFrame frameRes = new JFrame("Matrix Result");
	
	
    protected JButton create = new JButton("Create Matrices");
    protected JButton reset = new JButton("Reset");
    protected JButton quit = new JButton("Quit");
    protected JButton execute = new JButton("Execute");
    
    protected JButton readX = new JButton("Read X Matrix");
    protected JButton readY = new JButton("Read Y Matrix");
    
    protected JButton openX = new JButton("Open X Matrix");
    protected JButton openY = new JButton("Open Y Matrix");
    protected JButton openRes = new JButton("Open Result Matrix");
    
    protected JButton saveX = new JButton("Save X Matrix");
    protected JButton saveY = new JButton("Save Y Matrix");
    protected JButton saveRes = new JButton("Save Result Matrix");
    
    protected JButton clearX = new JButton("Clear X Matrix");
    protected JButton clearY = new JButton("Clear Y Matrix");
    protected JButton clearRes = new JButton("Clear Result Matrix");
   
    
    protected JLabel labelX = new JLabel("file: ",JLabel.RIGHT);
	protected JLabel labelY = new JLabel("file: ",JLabel.RIGHT);
	protected JLabel labelRes = new JLabel("file: ",JLabel.RIGHT);
    
	protected JLabel labelMat1 = new JLabel("Matrix 1", JLabel.CENTER);
	protected JLabel labelMat2 = new JLabel("Matrix 2", JLabel.CENTER);
	
	
	protected JTextArea note = new JTextArea(5, 40);
	protected JTextField matRow1 = new JTextField(5);
    protected JTextField matRow2 = new JTextField(5);
    protected JTextField matCol1 = new JTextField(5);
    protected JTextField matCol2 = new JTextField(5);
    
    protected JTextField tfY = new JTextField();
    protected JTextField tfX = new JTextField();
    protected JTextField tfRes = new JTextField();
    
    // variables for holding matrix values and executing computations in Lab1 package methods 
    protected JTextField[][] matX;
    protected JTextField[][] matY;
    protected JTextField[][] matRes;
    
    //protected JComboBox option = new JComboBox();
    protected JComboBox<String> select = new JComboBox<String>();

    protected JPanel panel25 = new JPanel(new GridLayout(1, 6));
    protected JPanel panel2 = new JPanel();
    protected JPanel panel1 = new JPanel();
    protected JPanel panel15 = new JPanel(new GridLayout(1, 6));
    
    protected JPanel panel3 = new JPanel();
    protected JPanel panel35 = new JPanel(new GridLayout(1, 6));
    
    protected JPanel panel4 = new JPanel();
    protected JPanel panel45 = new JPanel();
   

    JScrollPane scrollPane = new JScrollPane(note);
    
   

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View();

	}
	
	// implements action listeners to receive user input for program interaction 
	// sets up the basic elements for the View program 
	public View() {
		setTitle("View Program for Matrix");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    this.setBounds(0,0,400,300);
	   
	    add(panel1, BorderLayout.CENTER);
	    add(note, BorderLayout.SOUTH);
	    note.setEditable(false);
	    panel1.setLayout(new GridLayout(6,2));
		panel1.add(new JLabel(""));
		panel1.add(create);
		panel1.add(select);
		panel1.add(execute);
		panel1.add(reset);
		panel1.add(quit);
	    panel1.add(labelMat1);
		panel1.add(labelMat2);
		panel1.add(matRow1);
		panel1.add(matRow2);
		panel1.add(matCol1);
		panel1.add(matCol2);
		
		select.addItem("Select");
		select.addItem("Transpose");
		select.addItem("Add");
		select.addItem("Substract");
		select.addItem("Multiply");
	
		matRow1.requestFocus();
		setVisible(true); // allows you to see view 
		basics();
		
	}
	
	// set up plain user interface 
	public void basics() {
		matRow1.setText(""+2); 
		matCol1.setText(""+3);
		matRow2.setText(""+2); 
		matCol2.setText(""+3);
		select.setSelectedIndex(0);
	}
	
	// set up the frame and panels that displays the computation of the output matrix 
	// also allows the user to interact with the result by setting up the interface
	protected void output(int rows, int cols) {
		panel4.removeAll();
		panel4.setBackground(Color.cyan);
		panel4.setLayout(new GridLayout(rows, cols));
		panel45.setLayout(new GridLayout(rows, cols));
		
		for(int i = 0; i < rows; i++)
			 for(int j = 0; j < cols; j++)
				   panel4.add(matRes[i][j]); 
		
		frameRes.setLayout(new BorderLayout());
		frameRes.add(panel4, BorderLayout.CENTER);
		frameRes.add(panel45, BorderLayout.SOUTH);
		frameRes.setBounds(200,500,640,200);
		frameRes.setVisible(true);
		frameRes.setTitle("Result");
		
		panel45.add(clearRes);
		panel45.add(labelRes);
		panel45.add(tfRes);
		panel45.add(saveRes);
		panel45.add(openRes);
		panel45.add(new JLabel(" "));
			
		note.setText("Result Matrix Computed");
		
	}
	
	/*private void setup() {
		int rowNum = 2;
		int colNum = 3;
		int[][] a = new int[rowNum][colNum];
		
		int rowTotal = a.length;
		int colTotal = a[0].length;
	}*/
	

	

}
