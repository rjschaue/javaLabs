package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A stack that uses and array
 * @author Joey Schauer
 */
public class ArrayStack<E> implements Stack<E> {
	/** array list to store values */
	ArrayList<E> arrayList;
	/** capacity of the array stack */
	int capacity;
	
	/**
	 * Constructor for ArrayStack
	 * @param capacity the capacity of the array stack
	 */
	public ArrayStack(int capacity) {
		arrayList = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Pushes an element to the top of the stack
	 * @throws IllegalArgumentException if the stack is full
	 */
	@Override
	public void push(E element) {
		if (arrayList.size() == capacity) {
			throw new IllegalArgumentException();
		}
		arrayList.add(arrayList.size(), element);
	}

	/**
	 * Removes an element from the top of the stack
	 * @return the element that was removed
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return arrayList.remove(arrayList.size() - 1);
	}

	/**
	 * Returns true if the stack is empty
	 * @return true if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return arrayList.size() == 0;
	}

	/**
	 * Returns the size of the stack
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		return arrayList.size();
	}

	/**
	 * Sets the capacity of the stack
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
