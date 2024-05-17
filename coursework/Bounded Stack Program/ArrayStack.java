package assignment3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayStack represents a bounded stack implementation using an array.
 * @param <E> the type of elements stored in the stack
 */
public class ArrayStack<E> extends AbstractStack<E> {
    private E[] elements;
    private int top;

    /**
     * Constructs an ArrayStack with the specified capacity.
     * @param capacity the capacity of the stack
     * @throws IllegalArgumentException if the specified capacity is not positive
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        super(capacity); // Initialize capacity in AbstractStack
        if(capacity <= 0) throw new IllegalArgumentException("Capacity needs to be more than 0.");
        elements = (E[]) new Object[capacity];
        top = -1; // Initialize top index
    }

    /**
     * Adds an element to the top of the stack.
     * @param element the element to be pushed onto the stack
     * @throws NullPointerException if the specified element is null
     * @throws IllegalStateException if the stack is full
     */
    @Override
    public void push(E element) throws NullPointerException, IllegalStateException {
        if(element==null) throw new NullPointerException("There should not be null elements.");
        if(isFull()) throw new IllegalStateException("Stack is full of elements.");
        elements[++top] = element; // Increment top and add element to the array
    }

    /**
     * Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public E pop() throws IllegalStateException {
        if(isEmpty()) throw new IllegalStateException("Stack has no elements.");
        E element = elements[top];
        elements[top--] = null; // Clear top element and decrement top index
        return element;
    }

    /**
     * Returns the number of elements in the stack.
     * Depth is the number of actual elements stored in the stack.
     * @return the number of elements in the stack
     */
    @Override
    public int depth() {
        return top + 1;
    }

    /**
     * Removes all elements from the stack.
     */
    @Override
    public void clear() {
        while(!isEmpty()) {
            pop();
        }
    }

    /**
     * Creates and returns a new instance of ArrayStack with the same capacity as this stack.
     * @return a new instance of ArrayStack with the same capacity as this stack
     */
    @Override
    public BDDStack<E> newInstance() {
        return new ArrayStack<>(capacity());
    }



    /**
     * Returns an iterator over the elements in this stack in proper sequence.
     * @return an iterator over the elements in this stack in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private int index = 0; // Start from the bottom of the stack

        @Override
        public boolean hasNext() {
            return index < depth(); // Check if there are more elements in the stack
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[index++]; // Return current element and move to the next element upwards
        }
    }


}
