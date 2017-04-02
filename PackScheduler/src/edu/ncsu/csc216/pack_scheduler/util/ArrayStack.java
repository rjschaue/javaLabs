package edu.ncsu.csc216.pack_scheduler.util;

/**
 * A stack that uses and array
 * @author Joey Schauer
 */
public class ArrayStack<E> implements Stack<E> {
	ArrayList<E> arrayList;
	int capacity;
	
	public ArrayStack(int capacity) {
		arrayList = new ArrayList<E>();
		setCapacity(capacity);
	}

	@Override
	public void push(E element) {
		arrayList.add(arrayList.size(), element);
	}

	@Override
	public E pop() {
		return arrayList.remove(arrayList.size() - 1);
	}

	@Override
	public boolean isEmpty() {
		return arrayList.size() == 0;
	}

	@Override
	public int size() {
		return arrayList.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if (capacity > 0 && capacity > arrayList.size()) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException();
		}		
	}

}
