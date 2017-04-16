/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This is a linked list using recursion for it's implementation
 * @author Joey Schauer
 * @param <E> Generic type for the linked list
 */
public class LinkedListRecursive<E> {
	/** Gets the front list node for the linked list */
	private ListNode front;
	/** Gets the size of the list */
	private int size;
	
	/**
	 * Null constructor for LinkedListRecursive
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns the size of the linked list
	 * @return the size of the linked list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns true if the list is empty
	 * @return true if the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns true if the linked list contains a given element
	 * @param element the element to check the list for
	 * @return true if the linked list contains a given element
	 */
	public boolean contains(E element) {
		if (front == null) {
			return false;
		}
		return front.contains(element);
	}
	
	/**
	 * Adds a given element to the end of the linked list
	 * @param element the element to be added to the list
	 * @return true if the element is successfully added to the list
	 * @throws IllegalArgumentException if the element is already in the list
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			front = new ListNode(element, null);
			size++;
			return true;
		} else {
			for (ListNode p = front; p != null; p = p.next) {
				if (p.next == null) {
					p.next = new ListNode(element, null);
					size++;
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * Adds a given element at the given index in the linked list
	 * @param idx the index to add the given element
	 * @param element the element to be added
	 * @return true if the element was successfully added at the given index
	 * @throws NullPointerException if the given element is null
	 * @throws IllegalArgumentException if the element is already in the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 */
	public boolean add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (idx == 0) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		return front.add(idx, element);
	}
	
	/**
	 * Returns the element at the given index
	 * @param idx the index to get the element from
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(idx);
	}
	
	/**
	 * Removes the node at the given index in the linked list
	 * @param idx the index to remove from the list
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (idx == 0) {
			E removed = front.data;
			front = front.next;
			size--;
			return removed;
		}
		return front.remove(idx);
	}
	
	/**
	 * Removes the given element from the linked list
	 * @param element the element to be removed from the linked list
	 * @return true if the element was successfully removed from the list
	 * @throws NullPointerException if the given element is null
	 * @throws IllegalArgumentException if the list is empty
	 */
	public boolean remove(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (!contains(element)) {
			return false;
		}
		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}
		return front.remove(element);
	}
	
	/**
	 * Sets the element at the given index to the given element
	 * @param idx the index to set with the given element
	 * @param element the element to set
	 * @return the element that was replaced
	 * @throws NullPointerException if the given element is null
	 * @throws IllegalArgumentException if the element is already in the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 */
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return front.set(idx, element);
	}
	
	/**
	 * Nested class that handles list node functionality for the linked list
	 * @author Joey Schauer
	 */
	private class ListNode {
		/** the data for the list node */
		public E data;
		/** the next list node in the linked list */
		public ListNode next;
		
		/**
		 * The constructor for ListNode
		 * @param data the data to set the list node to
		 * @param next the next list node in the linked list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Searches for a given element using recursion
		 * @param element the element to check
		 * @return true if the list node contains the element
		 */
		private boolean contains(E element) {
			if (data.equals(element)) {
				return true;
			}
			if (next == null) {
				return false;
			}
			return next.contains(element);
		}
		
		/**
		 * Adds a given element to the given index using recursion
		 * @param idx the index to add the element to
		 * @param element the element to be added
		 * @return true if the element is successfully added
		 */
		private boolean add(int idx, E element) {
			if (idx == 1) {
				ListNode list = new ListNode(element, next);
				this.next = list;
				size++;
				return true;
			}
			idx--;
			return next.add(idx, element);
		}
		
		/**
		 * Returns the element at the given index using recursion
		 * @param idx the index to get the element from
		 * @return the element at the given index
		 */
		private E get(int idx) {
			if (idx == 0) {
				return this.data;
			}
			idx--;
			return next.get(idx);
		}
		
		/**
		 * Removes the node at the given index using recursion
		 * @param idx the index to remove the node from
		 * @return the element from the removed node
		 */
		private E remove(int idx) {
			if (idx == 1) {
				E removed = next.data;
				next = next.next;
				size--;
				return removed;
			}
			idx--;
			return next.remove(idx);
		}
		
		/**
		 * Removes the given element from the linked list using recursion
		 * @param element the element to be removed
		 * @return true if the element was successfully removed
		 */
		private boolean remove(E element) {
			if (next.data.equals(element)) {
				next = next.next;
				size--;
				return true;
			}
			return next.remove(element);
		}
		
		/**
		 * Sets the given element at the given index
		 * @param idx the index to set the element to
		 * @param element the element to be set
		 * @return the element that was replaced
		 */
		private E set(int idx, E element) {
			if (idx == 0) {
				E replaced = data;
				data = element;
				return replaced;
			}
			idx--;
			return next.set(idx, element);
		}
	}
}
