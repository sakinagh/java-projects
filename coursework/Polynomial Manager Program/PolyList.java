package assignment5;

/**
 * The PolyList class represents a list of polynomials.
 * It provides methods to insert, delete, search, and quit polynomials in the list.
 */
public class PolyList {
	private DLList<Polynomial> pn;
	
	/**
     * Constructs an empty PolyList with a doubly linked list of polynomials.
     */
	public PolyList() {
		pn = new DLList<>();
	}
	
	/**
     * Inserts a polynomial with the given name and terms into the list.
     * If a polynomial with the same name already exists, insertion fails.
     * 
     * @param name The name of the polynomial to be inserted.
     * @param terms The array of terms (coefficients and exponents) for the polynomial.
     */
	public void insert(String name, String[] terms) {
		// Start by positioning the current node pointer to the first node
	    pn.first();
	    
	    // Iterate through each polynomial in the list
	    while(true) {
	        Polynomial polynomial = pn.dataRead();
	        
	        // Check if a polynomial with the given name already exists
	        if (polynomial != null && polynomial.getName().equals(name)) {
	            System.out.println("POLYNOMIAL " + name + " ALREADY INSERTED");
	            return; // Stop the insertion process
	        }
	        
	        // Move to the next polynomial in the list
	        if (!pn.next()) break; // Exit the loop if there are no more polynomials
	    }
		
		Polynomial polynomial = new Polynomial(name);
		for(String term : terms) {
			String[] part = term.split(",");
			int coefficient = Integer.parseInt(part[0]);
			int expX = Integer.parseInt(part[1]);
			int expY = Integer.parseInt(part[2]);
			int expZ = Integer.parseInt(part[3]);
			Term t = new Term(coefficient, expX, expY, expZ);
			polynomial.addTerm(t);
		}
		pn.insertLast(polynomial);
		System.out.println(polynomial);
	}
	
	/**
     * Deletes the polynomial with the given name from the list.
     * 
     * @param name The name of the polynomial to be deleted.
     */
	public void delete(String name) {
		boolean isDeleted = false;
		pn.first(); // Iterate through each polynomial in the list
		while(pn.dataRead() != null) {
			Polynomial polynomial = pn.dataRead();
			if(polynomial.getName().equals(name)) {
				pn.deleteAtCurrent();
				isDeleted = true;
				System.out.println("POLYNOMIAL "+name+" SUCCESSFULLY DELETED");
				break;
			}
			if (!pn.next()) break;
		}
		if(!isDeleted) System.out.println("POLYNOMIAL "+name+" DOES NOT EXIST");
	}
	
	/**
     * Searches for the polynomial with the given name in the list.
     * 
     * @param name The name of the polynomial to search for.
     */
	public void search(String name) {
		boolean isFound = false;
		pn.first(); // Iterate through each polynomial in the list
		while(pn.dataRead() != null) {
			Polynomial polynomial = pn.dataRead();
			if(polynomial.getName().equals(name)) {
				System.out.println(polynomial);
				isFound = true;
			}
			if (!pn.next()) break;
		}
		if(!isFound) System.out.println("POLYNOMIAL "+name+" DOES NOT EXIST");
	}
	
	/**
     * Clears the list and exits the program.
     */
	public void quit() {
		pn.clear();
		System.exit(0);
	}

}
