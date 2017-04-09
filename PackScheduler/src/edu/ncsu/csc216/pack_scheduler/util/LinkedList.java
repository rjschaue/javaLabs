/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Specific implementation of a linked list class
 * @author Joey Schauer
 * @param <E> generic type of the LinkedList class
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** the front node of the linked list */
	private ListNode front;
	/** the back node of the linked list */
	private ListNode back;
	/** the size of the linked list */
	private int size;
	
	/**
	 * The null constructor for the linked list
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	
	/**
	 * Creates a new list iterator at the given index
	 * @return a new list iterator at the given index
	 * @throws IndexOutOfBoundsException if the index is out of the list
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		try {
			return new LinkedListIterator(index);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(e.getMessage());
		}		
	}

	/**
	 * Adds the given element at the given index to the list
	 * @throws IllegalArgumentException if the element is already in the list
	 * @see java.util.AbstractSequentialList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		if (element != null && contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	/** 
	 * Sets the given index to the given element
	 * @throws IllegalArgumentException if the element is already in the list
	 * @see java.util.AbstractSequentialList#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		if (element != null && contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
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
	 * Nested class that handles list node functionality
	 * @author Joey Schauer
	 */
	private class ListNode {
		/** The data in the list node */
		private E data;
		/** The reference to the next list node */
		private ListNode next;
		/** The reference to the previous list node */
		private ListNode prev;
		
		/**
		 * Constructor that creates a node with the given data
		 * @param data the data to set in the node
		 */
		public ListNode(E data) {
			this(data, null, null);
		}
		
		/**
		 * Constructor that creates a node with the given data
		 * and references to the given previous and next nodes
		 * @param data the data to set in the node
		 * @param prev the reference to the previous node
		 * @param next the reference to the next node
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	/**
	 * Nested class that handles iterator functions for the linked list
	 * @author Joey Schauer
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** the previous node in the iterator */
		private ListNode previous;
		/** the next node in the iterator */
		private ListNode next;
		/** the index of the previous node */
		private int previousIndex;
		/** the index of the next node */
		private int nextIndex;
		/** the last node retrieved through the iterator */
		private ListNode lastRetrieved;
		
		/**
		 * The constructor for LinkedListIterator
		 * @param index the index to set the iterator at
		 * @throws IndexOutOfBoundsException if the given index is less than 0 or greater than size
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			previous = front;
			next = front.next;
			for (int i = 0; i < index; i++) {
				previous = next;
				next = next.next;
			}
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}
		
		/**
		 * Adds the given element to the iterator
		 * @throws NullPointerException if the element is null
		 */
		@Override
		public void add(E element) {
			if (element == null) {
				throw new NullPointerException();
			}
			ListNode node = new ListNode(element, previous, next);
			next.prev = node;
			previous.next = node;
			previousIndex++;
			nextIndex++;
			size++;
			lastRetrieved = null;
		}

		/**
		 * Returns if there is a next element in the iterator
		 * @return if there is a next element in the iterator
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Returns if there is a previous element in the iterator
		 * @return if there is a previous element in the iterator
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * Returns the next element in the iterator
		 * @return the next element in the iterator
		 * @throws NoSuchElementException if next is null
		 */
		@Override
		public E next() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			previous = next;
			next = next.next;
			nextIndex++;
			previousIndex++;
			return lastRetrieved.data;
		}

		/**
		 * Returns the index of the next element in the iterator
		 * @return the index of the next element in the iterator
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns the previous element in the iterator
		 * @return the previous element in the iterator
		 * @throws NoSuchElementException if previous is null
		 */
		@Override
		public E previous() {
			if (previous == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			next = previous;
			previous = previous.prev;
			nextIndex--;
			previousIndex--;
			return lastRetrieved.data;
		}

		/**
		 * Returns the index for the previous element in the iterator
		 * @return the index for the previous element in the iterator
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the last retrieved element from the iterator
		 * @throws IllegalStateException if the last retrieved element is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.next.prev = lastRetrieved.prev;
			lastRetrieved.prev.next = lastRetrieved.next;
			size--;
			lastRetrieved = null;
		}

		/**
		 * Sets the last retrieved data to the given element
		 * @throws IllegalStateException if last retrieved is null
		 * @throws NullPointerException if the given element is null
		 */
		@Override
		public void set(E element) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (element == null) {
				throw new NullPointerException();
			}
			lastRetrieved.data = element;
		}
		
	}
}
