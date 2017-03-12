package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This is a specific implementation of ArrayList
 * @author Joey Schauer
 * @param <E> the list element
 */
public class ArrayList<E> extends AbstractList<E> {
	/** The initial size of the array list */
	public static final int INIT_SIZE = 10;
	/** The array of elements */
	private E[] list;
	/** The size of the array list */
	private int size;

	/**
	 * The null constructor for ArrayList
	 */
	@SuppressWarnings("unchecked") 
	public ArrayList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}
	
	/**
	 * This method adds an element to the array at a given index
	 * @param index is the index for the element to be added to
	 * @param element is the element to be added
	 * @throws NullPointerException if the element is null
	 * @throws IllegalArgumentException if the element is a duplicate
	 * @throws IndexOutOfBoundsException if the index is outside of the list bounds
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int k = size; k > index; k--) {
			list[k] = list[k - 1];
		}
		list[index] = element;
		size++;
		if (size == list.length) {
			growArray();
		}
	}
	
	/**
	 * Private method to double the size of the array list
	 */
	@SuppressWarnings("unchecked") 
	private void growArray() {
		int newSize = size * 2;
		E[] newList = (E[]) new Object[newSize];
		for (int i = 0; i < size; i++) {
			newList[i] = list[i];
		}
		list = newList;		
	}
	
	/**
	 * This method removes an element at the given index from the list
	 * @throws IndexOutOfBoundsException if the index in outside of the list bounds
	 * @return The element that was removed
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E element = list [index];
		size--;
		for (int i = index; i < size; i++) {
			list[i] = list[i + 1];
		}
		list[size] = null;
		return element;
	}
	
	/**
	 * This method sets the given index with a new element
	 * @throws NullPointerException if the element is null
	 * @throws IllegalArgumentException if the element is a duplicate
	 * @throws IndexOutOfBoundsException if the index is outside of the list bounds
	 * @return The element that was replaced
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E oldElement = list[index];
		list[index] = element;
		return oldElement;
	}
	
	/**
	 * This method gets the element at the given array
	 * @throws IndexOutOfBoundsException if the index is outside of the list bounds
	 * @return the element at the given index in the list
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}
	
	/**
	 * Returns the size of the array list
	 * @reutrn the size of the array list
	 */
	@Override
	public int size() {
		return size;
	}
	
}
