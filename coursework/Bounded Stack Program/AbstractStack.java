package assignment3;


/**
 * An abstract class providing a skeletal implementation of the Bounded Stack interface.
 * @param <E> the type of elements in this stack
 */
public abstract class AbstractStack<E> implements BDDStack<E> {
    
    protected E[] elements; // Declaring elements variable
    protected int top; // Maintaining top index

    /**
     * Constructs a new stack with the specified capacity.
     * @param capacity the capacity of the stack
     * @throws IllegalArgumentException if the specified capacity is not positive
     */
    @SuppressWarnings("unchecked")
    public AbstractStack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be greater than 0");
        elements = (E[]) new Object[capacity]; // Creating an array with the specified capacity
        top = -1; // Initialize top index to indicate an empty stack
    }

    // ========================================
	// Secondary Methods
	// ========================================
	
	/**
	 * Returns the capacity of this stack.
	 * @return the capacity of this stack
	 */
    @Override
	public int capacity() {
        return elements.length;
    }
	
	/**
	 * Returns true if this stack is empty.
	 * @return true if this stack is empty
	 */
	@Override
    public boolean isEmpty() {
        return depth() == 0;
    }
	
	/**
	 * Returns true if this stack is full.
	 * @return true if this stack is full
	 */
    @Override
	public boolean isFull() {
        return depth() == capacity();
    }
	
	/**
	 * Reverses the elements in this stack.
	 */
    @Override
	public void flip() {
        
        if (depth() <= 1) return; // If the stack has 0 or 1 elements, no need to flip

        // Temporary stack to hold elements while flipping
        BDDStack<E> tempStack1 = newInstance();
        BDDStack<E> tempStack2 = newInstance();
    
        // Pop elements from original stack to tempStack1
        while (!isEmpty()) {
            tempStack1.push(pop());
        }
    
        // Pop elements from tempStack1 to tempStack2
        while (!tempStack1.isEmpty()) {
            tempStack2.push(tempStack1.pop());
        }
    
        // Pop elements from tempStack2 back to original stack
        while (!tempStack2.isEmpty()) {
            push(tempStack2.pop());
        }

    }
	
	/**
	 * Returns a new stack that is a shallow copy of this stack. The new stack
	 * has the same capacity as this stack.
	 * @return a new stack that is a shallow copy of this stack
	 */
    @Override
	public BDDStack<E> copy() {
        BDDStack<E> newStack = newInstance();
        for (E element : this) {
            newStack.push(element); // Push each element from this stack to the new stack
        }
        return newStack;
    }

    

}
