package assignment5;

import java.util.Scanner;

/**
 * The Project5 class implements a polynomial manipulation program.
 * It provides functionalities to insert, delete, and search polynomials
 * and their terms.
 */
public class Project5 {
	
	/**
     * Main method to execute the polynomial manipulation program.
     * 
     * The method continuously listens for user commands from the console
     * and processes them accordingly until the user chooses to quit.
     * 
     * @param args Command-line arguments (not used).
     */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		PolyList PL = new PolyList(); 
		
		while(true) {
			String command = scan.next();
			switch(command) {
			case "INSERT":
				String name = scan.next();
	            String allTermsStr = scan.nextLine().trim(); // Read the entire line
	            String[] tokens = allTermsStr.split("\\s+"); // Split the string by spaces
	            int numTerm = tokens.length; // Get the number of terms
	            // Create a 2D array to store coefficients and exponents for each term
	            int[][] termValues = new int[numTerm][4];
	            // Parse each term and extract coefficients and exponents
	            for (int i = 0; i < numTerm; i++) {
	                String[] termParts = tokens[i].split(","); // Split each term by commas
	                for (int j = 0; j < 4; j++) {
	                    termValues[i][j] = Integer.parseInt(termParts[j]); // Parse and store coefficient and exponents
	                }
	            }
	            // Convert termValues to terms string array
	            String[] terms = new String[numTerm];
	            for (int i = 0; i < numTerm; i++) {
	                terms[i] = termValues[i][0] + "," + termValues[i][1] + "," + termValues[i][2] + "," + termValues[i][3];
	            }
	            
	            PL.insert(name, terms);
	            break;
			case "DELETE":
				String nameToDel = scan.next();
				PL.delete(nameToDel);
				break;
			case "SEARCH":
				String searchName = scan.next();
				PL.search(searchName);
				break;
			case "QUIT":
				PL.quit();
				break;
			default:
				System.out.println("Invalid command, try again.");
			}
		}
		
	}

}
