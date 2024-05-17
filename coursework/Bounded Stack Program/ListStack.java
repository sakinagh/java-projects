package assignment3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ListStack represents a bounded stack implementation using an ArrayList.
 * @param <E> the type of elements stored in the stack
 */
public class ListStack<E> extends AbstractStack<E> {
    private ArrayList<E> elements;

    /**
     * Constructs a ListStack with the specified capacity.
     * @param capacity the capacity of the stack
     * @throws IllegalArgumentException if the specified capacity is not positive
     */
    public ListStack(int capacity) {
        super(capacity);
        if(capacity <= 0) throw new IllegalArgumentException("Capacity needs to be greater than 0.");
        elements = new ArrayList<>(capacity);
    }

    /**
     * Adds an element to the top of the stack.
     * @param element the element to be pushed onto the stack
     * @throws NullPointerException if the specified element is null
     * @throws IllegalStateException if the stack is full
     */
    @Override
    public void push(E element) throws NullPointerException, IllegalStateException {
        if(element==null) throw new NullPointerException("There should not be any null elements.");
        if(isFull()) throw new IllegalStateException("The stack is full.");
        elements.add(element);
    }

    /**
     * Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public E pop() throws IllegalStateException {
        if(isEmpty()) throw new IllegalStateException("The stack is empty.");
        return elements.remove(elements.size()-1); // Remove and return the last element from the ArrayList
    }

    /**
     * Returns the number of elements in the stack.
     * @return the number of elements in the stack
     */
    @Override
    public int depth() {
        return elements.size();
    }

    /**
     * Removes all elements from the stack.
     */
    @Override
    public void clear() {
        elements.clear();
    }

    /**
     * Creates and returns a new instance of ListStack with the same capacity as this stack.
     * @return a new instance of ListStack with the same capacity as this stack
     */
    @Override
    public BDDStack<E> newInstance() {
        return new ListStack<>(capacity()); // Create a new ListStack with the same capacity
    }


    /**
     * Returns an iterator over the elements in this stack in proper sequence.
     * @return an iterator over the elements in this stack in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return elements.iterator(); // Return the iterator provided by ArrayList
    }


}
