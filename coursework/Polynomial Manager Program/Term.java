package assignment5;

/**
 * The Term class represents a term in a polynomial expression with a coefficient and exponents for variables x, y, and z.
 * It provides methods to get the coefficient and exponents, as well as to generate a string representation of the term.
 */
public class Term {
    private int coefficient;
    private int exponentX;
    private int exponentY;
    private int exponentZ;

    /**
     * Constructs a term with the specified coefficient and exponents for variables x, y, and z.
     * 
     * @param coefficient The coefficient of the term.
     * @param exponentX The exponent of variable x.
     * @param exponentY The exponent of variable y.
     * @param exponentZ The exponent of variable z.
     */
    public Term(int coefficient, int exponentX, int exponentY, int exponentZ) {
        this.coefficient = coefficient;
        this.exponentX = exponentX;
        this.exponentY = exponentY;
        this.exponentZ = exponentZ;
    }
    
    /**
     * Returns the coefficient of the term.
     * 
     * @return The coefficient of the term.
     */
    public int getCoefficient() {
        return coefficient;
    }

    /**
     * Returns the exponent of variable x.
     * 
     * @return The exponent of variable x.
     */
    public int getExpX() {
        return exponentX;
    }

    /**
     * Returns the exponent of variable y.
     * 
     * @return The exponent of variable y.
     */
    public int getExpY() {
        return exponentY;
    }

    /**
     * Returns the exponent of variable z.
     * 
     * @return The exponent of variable z.
     */
    public int getExpZ() {
        return exponentZ;
    }
    
    /**
     * Generates a string representation of the term.
     * 
     * @return A string representing the term.
     */
    @Override
    public String toString() {
    	
    	if(coefficient==0) return "0";
    	
        StringBuilder sb = new StringBuilder();
        
        if(coefficient!=1 && coefficient!=-1) sb.append(coefficient);
        else if(coefficient==-1) sb.append("-");
        
        if(exponentX!=0) {
        	sb.append("(x");
        	if(exponentX!=1) sb.append("^").append(exponentX);
        	sb.append(")");
        }
        
        if(exponentY!=0) {
        	sb.append("(y");
        	if(exponentY!=1) sb.append("^").append(exponentY);
        	sb.append(")");
        }
        
        if(exponentZ!=0) {
        	sb.append("(z");
        	if(exponentZ!=1) sb.append("^").append(exponentZ);
        	sb.append(")");
        }
        
        return sb.toString();
        
    }
}
