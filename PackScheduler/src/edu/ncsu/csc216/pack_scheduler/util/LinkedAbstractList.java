package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This class is a specific implementation of a linked list
 * @author Joey Schauer
 * @param <E> The element to be processed
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The front node in the list */
	private ListNode front;
	/** The size of the list */
	private int size;
	/** The capacity of the list */
	private int capacity;
	
	/**
	 * The constructor for LinkedAbstractList
	 * @param capacity the capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		size = 0;
		setCapacity(capacity);
	}
	
	/**
	 * Sets the capacity of the list
	 * @param capacity the capacity of the list
	 * @throws IllegalArgumentException if the capacity is less than 1
	 */
	public void setCapacity(int capacity) {
		if (capacity > 0 && capacity >= size) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Adds a given element to the list at the given index
	 * @param index is the index for the element to be placed at
	 * @param element is the element to be added
	 * @throws IllegalArgumentException if the size equals the capacity or the element is already in the list
	 * @throws NullPointerException if the given element is null
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size
	 */
	@Override
	public void add(int index, E element) {
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		
		if (element == null) {
			throw new NullPointerException();
		}
		
		for (ListNode p = front; p != null; p = p.next) {
			if (p.data.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == 0) {
			front = new ListNode(element, front);
		} else if (front != null) {
			ListNode current = front;
			while (current != null && index > 1) {
				current = current.next;
				index--;
			}
			if (current != null) {
				if (current.next != null) {
					current.next = new ListNode(element, current.next);
				} else {
					current.next = new ListNode(element);
				}
			} 
		}
		size++;		
	}
	
	/**
	 * Removes the element at the given index from the list
	 * @param index is the index of the element to be removed
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size
	 * @return the element that was removed or null
	 */
	@Override
	public E remove(int index) {		
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		ListNode previous = null;
		
		while (current != null && index > 0) {
			previous = current;
			current = current.next;
			index--;
		}
		
		if (current != null) {
			if (current == front) {
				front = front.next;
			} else {
				previous.next = current.next;
			}
			size--;
			return current.data;
		}
		
		return null;
	}
	
	/**
	 * Sets the index in the list to the given element
	 * @param index is the index to be set
	 * @param element is the element to be set
	 * @throws NullPointerException if the element is null
	 * @throws IllegalArgumentException if the element is already in the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size
	 * @return the element that was replaced
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		
		for (ListNode p = front; p != null; p = p.next) {
			if (p.data.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		
		while (current != null && index > 0) {
			current = current.next;
			index--;
		}
		
		if (current != null) {
			E replaced = current.data;
			current.data = element;
			return replaced;
		}
		
		return null;
	}
	
	/**
	 * Gets the element at the given index
	 * @param index the index of the element to be gotten
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 * @return the element at the given index
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		
		while (current != null && index > 0) {
			current = current.next;
			index--;
		}
		if (current == null) {
			return null;
		}
		return current.data;
	}

	/**
	 * Returns the size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * This class is used for each node in the list
	 * @author Joey Schauer
	 */
	private class ListNode {
		/** The data in the node */
		private E data;
		/** The next node in the list */
		private ListNode next;
		
		/**
		 * The constructor for ListNode with no next
		 * @param data the data for the node
		 */
		public ListNode(E data) {
			this(data, null);
		}
		
		/**
		 * The main constructor for ListNode
		 * @param data the data for the node
		 * @param next the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
