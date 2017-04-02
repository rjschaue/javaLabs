/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Test class for ArrayQueue
 * @author Joey Schauer
 */
public class ArrayQueueTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<String> aq = new ArrayQueue<String>(10);
		assertEquals(aq.size(), 0);
		assertTrue(aq.isEmpty());
		
		//Test adding a null element
		try {
			aq.enqueue(null);
		} catch (NullPointerException e) {
			assertEquals(aq.size(), 0);
		}
		
		//Test adding a valid element
		aq.enqueue("New1");
		assertEquals(aq.size(), 1);
		assertFalse(aq.isEmpty());
		
		//Add multiple elements
		for (int i = 2; i < 11; i++) {
			aq.enqueue("New" + i);
			assertEquals(aq.size(), i);
		}
		
		//Test adding element over capacity
		try {
			aq.enqueue("TooMany");
		} catch (IllegalArgumentException e) {
			assertEquals(aq.size(), 10);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<String> aq = new ArrayQueue<String>(10);
		assertEquals(aq.size(), 0);
		assertTrue(aq.isEmpty());
		
		//Test dequeue on empty list
		try {
			aq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(aq.size(), 0);
			assertTrue(aq.isEmpty());
		}
		
		aq.enqueue("One");
		assertEquals(aq.size(), 1);
		assertFalse(aq.isEmpty());
		
		//Test popping with a populated list
		assertEquals(aq.dequeue(), "One");
		assertEquals(aq.size(), 0);
		assertTrue(aq.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#setCapacity()}.
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> aq = new ArrayQueue<String>(10);
		assertEquals(aq.size(), 0);
		assertTrue(aq.isEmpty());
		
		//Test setting capacity to 0
		try {
			aq.setCapacity(0);
		} catch (IllegalArgumentException e) {
			assertEquals(aq.size(), 0);
			assertTrue(aq.isEmpty());
		}
		
		aq.enqueue("One");
		assertEquals(aq.size(), 1);
		assertFalse(aq.isEmpty());
		
		aq.enqueue("Two");
		assertEquals(aq.size(), 2);
		assertFalse(aq.isEmpty());
		
		//Test setting capacity below size
		try {
			aq.setCapacity(1);
		} catch (IllegalArgumentException e) {
			assertEquals(aq.size(), 2);
			assertFalse(aq.isEmpty());
		}
	}

}
