package Lab1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* Sakina Ghafoor 
 * Lab 1 Assignment Fall 2023
 * Techniques in Programming
 */

public class Control extends View implements ActionListener, WindowListener {
	
	// variables to hold values for input and output
	protected int rowX;
	protected int colX;
	protected int rowY;
	protected int colY;
	
	protected double[][] arrX;
	protected double[][] arrY;
	protected double[][] arrRes;

    protected Model model = new Model();

    public Control() {
        // create and add action listeners for relevant operations to be implemented on user interface
    	// ensures user communication with the program
        create.addActionListener(this);
        reset.addActionListener(this);
        execute.addActionListener(this);
        quit.addActionListener(this);
        
        readX.addActionListener(this);
        readY.addActionListener(this);
        
        openX.addActionListener(this);
        openY.addActionListener(this);
        
        saveX.addActionListener(this);
        saveY.addActionListener(this);
        
        clearX.addActionListener(this);
        clearY.addActionListener(this);
        
        saveRes.addActionListener(this);
        clearRes.addActionListener(this);
        
        addWindowListener(this);

       
    }

    // connect user input selection to available matrix operations and executable methods 
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == create) {
            create();
        } else if (source == reset) {
            reset();
        } else if (source == execute) {
            oper();
        } else if (source == readX) {
            read(true);
        } else if (source == readY) {
            read(false);
        } else if (source == openX) {
            openMatrix(1);
        } else if (source == saveX) {
            saveAsFile(1);
        } else if (source == clearX) {
            clear(1);
        } else if (source == openY) {
            openMatrix(2);
        } else if (source == saveY) {
            saveAsFile(2);
        } else if (source == clearY) {
            clear(2);
        } else if (source == saveRes) {
            saveAsFile(3);
        } else if (source == clearRes) {
            clear(3);
        } else if (source == quit) {
            quitApplication();
        }
    }

    // clears operation fields on the user interface 
    private void clear(int matrixType) {
        JTextField[][] matrix = null;

        if (matrixType == 1) {
            matrix = matX;
        } else if (matrixType == 2) {
            matrix = matY;
        } else if (matrixType == 3) {
            matrix = matRes;
        }

        if (matrix != null) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    matrix[r][c].setText("");
                }
            }
        }
    }

    // based on the selection will open up the interface(s) for matrix user input
    // allows the user to input matrix values in separate GUI frames
    private void display(int s, int row, int col) {
    	if (s==1) {
    		panel2.setLayout(new GridLayout(row, col));
        	matX = new JTextField[row][col]; 
        }
    	
    	else if (s==2) {
    		panel3.setLayout(new GridLayout(row, col));
			matY = new JTextField[row][col]; 
		}
    	
    	else if (s==3) {
    		panel4.setBackground(Color.blue); 
    	}
        
    	for(int r=0; r<row; r++) { 
    		for(int c=0; c<col; c++) {   
    			if(s==1) { 
    				matX[r][c] = new JTextField(); 
    				panel2.add(matX[r][c]); 
    			}
    			else if (s==2) { 
    				matY[r][c] = new JTextField(); 
    				panel3.add(matY[r][c]); 
    			}
    			else if (s==3) {  
    				panel4.add(matRes[r][c]); 
    			}
    		}	
    	}

    	if(s==1) {    		
    		frameX.setLayout(new BorderLayout());
    		frameX.setTitle("Matrix 1");
    		frameX.setBounds(500,500,400,200);
    		frameX.add(panel2, BorderLayout.CENTER);
    		frameX.add(panel25, BorderLayout.SOUTH);   		
    		frameX.setVisible(true);
    		
    		panel25.add(clearX);
    		panel25.add(readX);
    		panel25.add(labelX);
    		panel25.add(tfX);
    		panel25.add(saveX);
    		panel25.add(openX);
    	}
    	
    	else if (s==2) {
    		panel35.add(clearY);
    		panel35.add(readY);
    		panel35.add(labelY);
    		panel35.add(tfY);
    		panel35.add(saveY);
    		panel35.add(openY);

    		frameY.setLayout(new BorderLayout());
    		frameY.setTitle("Matrix 2");
    		frameY.setBounds(500,500,400,200);
    		frameY.add(panel3, BorderLayout.CENTER);
    		frameY.add(panel35, BorderLayout.SOUTH);
    		frameY.setVisible(true);
    	}

    	note.setText("matrix is being shown");
    }

    // resets all aspects of the interface to a blank slate 
    private void reset() {
        matRow1.setText("");
        matRow2.setText("");
        matCol1.setText("");
        matCol2.setText("");
        
        if(frameX!=null) frameX.dispose();
        if(frameY!=null) frameY.dispose();
        //clear(1);
        //clear(2);
        //clear(3);
       
        note.setText("Matrices have been reset");
    }
    
    // creates the components of the input interfaces for matrix values in the form of rows and columns 
    // also checks the values of the rows and columns to ensure that there are no set backs for the selected operation 
    private void create() {
        // Validate input for matrix dimensions
        if (check(matRow1) && check(matCol1) && check(matRow2) && check(matCol2)) {
            rowX = Integer.parseInt(matRow1.getText());
            colX = Integer.parseInt(matCol1.getText());
            rowY = Integer.parseInt(matRow2.getText());
            colY = Integer.parseInt(matCol2.getText());

            if (rowX < 0 || colX < 0 || rowY < 0 || colY < 0) {
            	note.setText("Matrix values is negative");
            } else {
            	panel2.removeAll(); 
            	panel3.removeAll(); 
            	display(1,rowX,colX);
            	display(2,rowY,colY);
	        
            	if(select.getSelectedIndex()>1) frameY.setVisible(true);
            	else frameY.setVisible(false); 
	        }
        }
        note.setText("Matrices have been created");
    }
    
    // reads the input for both matrices
    // converts the read values from text to double format to prepare for result calculation 
    private void read(boolean b) {
    	int rows;
    	int column;
    	
        if (b) {
            rows=rowX;
            column=colX;
            arrX = new double[rows][column];
        }
        else {
        	rows=rowY;
        	column=colY;
        	arrY = new double[rows][column];
        }

        try {
            for (int r=0; r<rows; r++) {
                for (int c=0; c<column; c++) {
                	if(b) arrX[r][c] = Double.parseDouble(matX[r][c].getText());
                	else arrY[r][c] = Double.parseDouble(matY[r][c].getText());
                }
            }
            note.setText("Matrix has been read");
        } catch (NumberFormatException ex) {
        	note.setText("Error: Matrix contains non-numeric values");
        }
    }

    // based on the selection made by the user will implement the relevant method from the Model program 
    // notifies user of program execution and potential issues with input 
    private void oper() {
        int selection = select.getSelectedIndex();

        if(selection == 0) { 
        	note.setText("Please select an option to execute"); 
        	return; 
        }
        
        else if(selection == 1) { 
        	arrRes= model.transpose(arrX);
        	int nRows = arrRes.length;    
        	int nCols = arrRes[0].length;
        	matRes = new JTextField[nRows][nCols];
		    for(int r = 0; r < nRows; r++)
			     for(int c = 0; c < nCols; c++) {  
			    	 matRes[r][c] = new JTextField();
			         matRes[r][c].setText(""+arrRes[r][c]); 
			     }
	             output(nRows,nCols);
	    }  
        
        else if(selection == 2) { 
        	if(rowX==rowY && colX==colY ) { 
        		arrRes = model.add(true, arrX, arrY); 
        		matRes = new JTextField[rowX][colX];
        		for(int r = 0; r < rowX; r++)
        			for(int c = 0; c < colY; c++) { 
        				matRes[r][c] = new JTextField();
        				matRes[r][c].setText(""+arrRes[r][c]); 
        			}
		        panel4.removeAll(); 
		        output(rowX,colX);
		        note.setText("Result Matrix Has Been Calculated");
		    }
	
        	else { 
        		note.setText("Incompatible matrix dimensions for addition, try again");
		   }
        }
	   
        else if(selection == 3) {  
        	if(rowX==rowY && colX==colY ) { 
				 arrRes = model.add(false, arrX, arrY);
				 matX = new JTextField[rowX][colX];
				 for(int r = 0; r < rowX; r++)
					 for(int c = 0; c < colY; c++) {  
						 matRes[r][c] = new JTextField();
					     matRes[r][c].setText(""+arrRes[r][c]);  
					 }
				    output(rowX,colX);
		 }
			
			else { 
				note.setText("Incompatible matrix dimensions for subtraction, try again");
			}
      }
        
        else if (selection == 4) { 
        	if(colX == rowY) {
        		arrRes = model.multiply(arrX,arrY);
        		int nRows = arrRes.length;    
        		int nCols = arrRes[0].length;
        		matRes = new JTextField[nRows][nCols];
        		for(int r = 0; r < nRows; r++)
        			for(int c = 0; c < nCols; c++) {  
        				matRes[r][c] = new JTextField();  
        				matRes[r][c].setText(""+arrRes[r][c]); 
        			}
            output(nRows,nCols);
        	}
        	else { 
        		note.setText("Incompatible matrix dimensions for multiplication, try again");}
		}
        

       
    }

   /* private void showResult(double[][] matrix) {
        matRes = new JTextField[matrix.length][matrix[0].length];
        JPanel resultMatrixPanel = matPanel(matrix.length, matrix[0].length, matRes, "Result Matrix");
        resultMatrixPanel.setVisible(true);
    }*/
    
    // notifies the user that the matrix file was saved as an object successfully 
    private void saveAsFile(int type) {
        String fileName = null;

        if(type==1) { 
        	fileName=tfX.getText(); 
        	model.saveF(tfX.getText(),arrX); 
        }
		else if(type==2) { 
			fileName=tfY.getText();
			model.saveF(tfY.getText(),arrY); 
		}
		else if(type==3) {  
			fileName=tfRes.getText(); 
			model.saveF(tfRes.getText(),arrRes); 
		}
        note.setText("file " + fileName + " saved as object");
    }
    
    // opens saved files and their content for the relevant matrix selected 
    // notifies the user that the selected file was opened 
    private void openMatrix(int input) {
        String fileName = null;
        double[][] results = null;
        int rowNum;  
        int colNum; 
		
        if(input==1) { 
			panel2.removeAll(); 
			results = model.readF(tfX.getText());
		    fileName = tfX.getText(); 
		    rowX = results.length; 
		    colX = results[0].length;
		    read(true);
		    arrX = results;
		}
		
        else if(input==2){ 
        	panel3.removeAll(); 
        	results = model.readF(tfY.getText());
		    fileName = tfY.getText();
		    rowX = results.length;  
		    colX = results[0].length;
		    read(false);
		    arrY = results;
		}
		
        else if(input==3) { 
        	panel4.removeAll(); 
        	results = model.readF(tfRes.getText());
		    fileName = tfRes.getText();
		}
		
        rowNum = results.length;    
        colNum = results[0].length;
        display(input,rowNum,colNum);
        
        if(input==1) { 
        	rowX = rowNum; 
        	colX = colNum; 
        }
        
        if(input==2) { 
        	rowY = rowNum; 
        	colY = colNum; 
        }
        else if(input==2) { 
        	panel3.removeAll(); 
        	results = model.readF(tfY.getText()); 
        }
        
        for(int r = 0; r < rowNum; r++)
		 for(int c = 0; c < colNum; c++)
			    if(input==1) { 
			    	panel2.add(matX[r][c]);
			        matX[r][c].setText(""+results[r][c]); 
			    }
			    else if(input==2) { 
			    	panel3.add(matY[r][c]);
			        matY[r][c].setText(""+results[r][c]); 
			    }
			    else if(input==3) { 
			    	panel4.add(matRes[r][c]);
				    matRes[r][c].setText(""+results[r][c]);
			    }
		
        note.setText("file " +  fileName + " opened as object");
    }

    // error handling and providing feedback to the user 
    private boolean check(JTextField user) {
        try {
            int d = Integer.parseInt(user.getText());
            return true;
        } catch (NumberFormatException e) {
        	note.setText("Invalid integer: " + user.getText());
            return false;
        }
    }

    private void quitApplication() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new Control();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    	if(frameX != null) frameX.dispose();
		if(frameY != null) frameY.dispose();
		if(frameRes != null) frameRes.dispose();
		System.exit(1);
    }

	@Override
	public void windowClosed(WindowEvent e) {		
	}

	@Override
	public void windowIconified(WindowEvent e) {		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {		
	}

	@Override
	public void windowActivated(WindowEvent e) {		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {		
	}

}
