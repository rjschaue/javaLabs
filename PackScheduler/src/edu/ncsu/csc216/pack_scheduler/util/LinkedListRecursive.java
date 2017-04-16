/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * @author Joey Schauer
 * @param <E>
 */
public class LinkedListRecursive<E> {
	private ListNode front;
	private int size;
	
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(E element) {
		if (front == null) {
			return false;
		}
		return front.contains(element);
	}
	
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
	
	public E get(int idx) {
		return null;
	}
	
	public E remove(int idx) {
		return null;
	}
	
	public boolean remove(E element) {
		return false;
	}
	
	public E set(int idx, E element) {
		return null;
	}
	
	private class ListNode {
		public E data;
		public ListNode next;
		
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		private boolean contains(E element) {
			if (data == null) {
				return false;
			}
			if (data.equals(element)) {
				return true;
			}
			return next.contains(element);
		}
		
		private boolean add(int idx, E element) {
			return false;
		}
		
		private E get(int idx) {
			return null;
		}
		
		private E remove(int idx) {
			return null;
		}
		
		private boolean remove(E element) {
			return false;
		}
		
		private E set(int idx, E element) {
			return null;
		}
	}
}
