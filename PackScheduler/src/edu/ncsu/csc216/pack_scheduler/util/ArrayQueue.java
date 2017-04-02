package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Stores values as an array queue
 * @author Joey Schauer
 */
public class ArrayQueue<E> implements Queue<E>{
	ArrayList<E> arrayList;
	int capacity;
	
	/**
	 * Constructor for ArrayQueue
	 * @param capacity the capacity for the queue
	 */
	public ArrayQueue(int capacity) {
		arrayList = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds the element to the back of the queue
	 * @throws IllegalArgumentException if there is no room in the queue
	 */
	@Override
	public void enqueue(E element) {
		if (arrayList.size() == capacity) {
			throw new IllegalArgumentException();
		}
		arrayList.add(0, element);
	}

	/**
	 * Removes and returns the element at the front of the queue
	 * @return the elements at the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return arrayList.remove(arrayList.size() - 1);
	}

	/**
	 * Returns true if the queue is empty
	 * @return true if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return arrayList.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return arrayList.size();
	}

	/**
	 * Sets the queue's capacity
	 * @throws IllegalArgumentException if the capacity is invalid
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity > 0 && capacity >= arrayList.size()) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException();
		}			
	}

}
