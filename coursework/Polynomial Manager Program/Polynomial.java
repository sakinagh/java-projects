package assignment5;

/**
 * The Polynomial class represents a polynomial with a name and a list of terms.
 * It provides methods to get the name, retrieve terms, add terms, and generate a string representation of the polynomial.
 */
public class Polynomial {
    private String name;
    private DLList<Term> terms;

    /**
     * Constructs a polynomial with the specified name and an empty list of terms.
     * 
     * @param name The name of the polynomial.
     */
    public Polynomial(String name) {
        this.name = name;
        this.terms = new DLList<>();
    }

    /**
     * Returns the name of the polynomial.
     * 
     * @return The name of the polynomial.
     */
    public String getName() {
    	return name;
    }
    
    /**
     * Returns the term at the specified index in the list of terms.
     * 
     * @param index The index of the term to retrieve.
     * @return The term at the specified index, or null if the index is out of bounds.
     */
    public Term getTerms(int index) {
    	// Ensure index is within bounds
        if (index < 0 || index >= terms.size()) {
            return null; // Return null if index is out of bounds
        }
        
        // Move to the specified index
        terms.seek(index);
        
        // Return the term at the specified index
        return terms.dataRead();
    }
    
    /**
     * Adds a term to the polynomial.
     * 
     * @param term The term to add to the polynomial.
     */
    public void addTerm(Term term) {
    	terms.insertLast(term);
    }
    
    /**
     * Generates a string representation of the polynomial.
     * 
     * @return A string representing the polynomial.
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder(name);
        sb.append(" = ");
        boolean isFirstTerm = true;
        
        for (int i = 0; i < terms.size(); i++) {
            Term term = getTerms(i);
            String termString = term.toString();

            if (!isFirstTerm) {
                if (term.getCoefficient() < -1) {
                    sb.append(" - ");
                    termString = termString.substring(1);
                } 
                else if (term.getCoefficient() == -1) {
                   if (term.getExpX() == 0 && term.getExpY() == 0 && term.getExpZ() == 0) {
                        sb.append(" - 1");
                        termString = termString.substring(1);
                    } else {
                    	sb.append(" - ");
                    	termString = termString.substring(1);
                    }
                } 
                else if (term.getCoefficient() == 1 && term.getExpX() == 0 && term.getExpY() == 0 && term.getExpZ() == 0) {
                    sb.append(" + 1");
                } 
                else {
                    sb.append(" + ");
                }
            } else {
            	if(term.getCoefficient() == -1 && term.getExpX() == 0 && term.getExpY() == 0 && term.getExpZ() == 0) {
            		sb.append("- 1");
            		termString = termString.substring(1);
            	} else if(term.getCoefficient() == 1 && term.getExpX() == 0 && term.getExpY() == 0 && term.getExpZ() == 0) {
            		sb.append("1");
            	} 
            }
            
                    
            // Append the term string to the polynomial representation
            sb.append(termString);
            
            isFirstTerm = false;
        }
        
        return sb.toString();
    }
}
