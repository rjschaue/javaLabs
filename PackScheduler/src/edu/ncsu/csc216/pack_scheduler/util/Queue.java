package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for a queue
 * @param <E> is the element type for the queue
 * @author Joey Schauer
 *
 */
public interface Queue<E> {

	/**
	 * Add the element to the back of the queue
	 * @param element the element to be added to the queue
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the element at the front of the queue
	 * @return the element at the front of the queue
	 */
	E dequeue();
	
	/**
	 * Returns true if the queue is empty
	 * @return true if the queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the queue
	 * @return the number of elements in the queue
	 */
	int size();
	
	/**
	 * Sets the queue's capacity
	 * @param capacity is the capacity to set for the queue
	 */
	void setCapacity(int capacity);
}
