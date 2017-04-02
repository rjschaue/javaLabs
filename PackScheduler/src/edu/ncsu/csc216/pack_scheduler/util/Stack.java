package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Stack interfact for PackScheduler
 * @param <E> is the element type for the stack
 * @author Joey Schauer
 */
public interface Stack<E> {
	/**
	 * Adds element to top of stack
	 * @param element the element to be added
	 */
	void push(E element);
	
	/**
	 * Removes and returns element from top of stack
	 * @return element from top of stack
	 */
	E pop();
	
	/**
	 * Returns true if stack is empty
	 * @return true if stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the stack
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * Sets the stack's capacity
	 * @param capacity the capacity to set the stack to
	 */
	void setCapacity(int capacity);
}
