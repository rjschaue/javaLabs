package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Stores values as a linked queue
 * @param <E> is the element type for the queue
 * @author Joey Schauer
 */
public class LinkedQueue<E> implements Queue<E> {
	LinkedAbstractList<E> linkedAbstractList;

	/**
	 * Constructor for LinkedQueue
	 * @param capacity the capacity for the queue
	 */
	public LinkedQueue(int capacity) {
		linkedAbstractList = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Adds the element to the back of the queue
	 */
	@Override
	public void enqueue(E element) {
		linkedAbstractList.add(linkedAbstractList.size(), element);		
	}

	/**
	 * Removes and returns the element at the front of the queue
	 * @return the element at the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return linkedAbstractList.remove(0);
	}

	/**
	 * Returns true if the queue is empty
	 * @return true if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return linkedAbstractList.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return linkedAbstractList.size();
	}

	/**
	 * Sets the queue's capacity
	 */
	@Override
	public void setCapacity(int capacity) {
		linkedAbstractList.setCapacity(capacity);
	}

}
