package edu.ncsu.csc216.pack_scheduler.util;

/**
 * A stack that uses a linked list
 * @author Joey Schauer
 */
public class LinkedStack<E> implements Stack<E> {
	LinkedAbstractList<E> linkedAbstractList;
	
	public LinkedStack(int capacity) {
		linkedAbstractList = new LinkedAbstractList<E>(capacity);
	}
	
	@Override
	public void push(E element) {
		linkedAbstractList.add(linkedAbstractList.size(), element);		
	}

	@Override
	public E pop() {
		return linkedAbstractList.remove(linkedAbstractList.size() - 1);
	}

	@Override
	public boolean isEmpty() {
		return linkedAbstractList.isEmpty();
	}

	@Override
	public int size() {
		return linkedAbstractList.size();
	}

	@Override
	public void setCapacity(int capacity) {
		linkedAbstractList.setCapactiy(capacity);	
	}

}
