package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A stack that uses a linked list
 * @author Joey Schauer
 */
public class LinkedStack<E> implements Stack<E> {
	/** the linked abstract list to store values */
	LinkedAbstractList<E> linkedAbstractList;
	
	/**
	 * Constructor for LinkedStack
	 * @param capacity the capacity of the stack
	 */
	public LinkedStack(int capacity) {
		linkedAbstractList = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Pushes an element to the top of the stack
	 */
	@Override
	public void push(E element) {
		linkedAbstractList.add(linkedAbstractList.size(), element);		
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
		return linkedAbstractList.remove(linkedAbstractList.size() - 1);
	}

	/**
	 * Returns true if the stack is empty
	 * @return true if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return linkedAbstractList.isEmpty();
	}
	
	/**
	 * Returns the size of the stack
	 * @reutrn the size of the stack
	 */
	@Override
	public int size() {
		return linkedAbstractList.size();
	}

	/**
	 * Sets the capacity of the stack
	 */
	@Override
	public void setCapacity(int capacity) {
		linkedAbstractList.setCapactiy(capacity);	
	}

}
