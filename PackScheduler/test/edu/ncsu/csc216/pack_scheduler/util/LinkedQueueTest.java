package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Test class for LinkedQueue
 * @author Joey Schauer
 */
public class LinkedQueueTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> lq = new LinkedQueue<String>(10);
		assertEquals(lq.size(), 0);
		assertTrue(lq.isEmpty());
		
		//Test enqueue null object
		try {
			lq.enqueue(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(lq.size(), 0);
			assertTrue(lq.isEmpty());
		}
		
		//Test enqueue valid string
		lq.enqueue("New1");
		assertEquals(lq.size(), 1);
		assertFalse(lq.isEmpty());
		
		for (int i = 2; i < 11; i++) {
			lq.enqueue("New" + i);
			assertEquals(lq.size(), i);
		}
		
		//Test enqueue object over capacity
		try {
			lq.enqueue("TooMany");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(lq.size(), 10);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		LinkedQueue<String> lq = new LinkedQueue<String>(10);
		assertEquals(lq.size(), 0);
		assertTrue(lq.isEmpty());
		
		//Test dequeue empty list
		try {
			lq.dequeue();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(lq.size(), 0);
			assertTrue(lq.isEmpty());
		}
		
		lq.enqueue("One");
		assertEquals(lq.size(), 1);
		assertFalse(lq.isEmpty());
		
		//Test dequeue with a populated list
		assertEquals(lq.dequeue(), "One");
		assertEquals(lq.size(), 0);
		assertTrue(lq.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#setCapacity()}.
	 */
	@Test
	public void testSetCapacity() {
		LinkedQueue<String> lq = new LinkedQueue<String>(10);
		assertEquals(lq.size(), 0);
		assertTrue(lq.isEmpty());
		
		//Test setting capacity to 0
		try {
			lq.setCapacity(0);
		} catch (IllegalArgumentException e) {
			assertEquals(lq.size(), 0);
			assertTrue(lq.isEmpty());
		}
		
		lq.enqueue("One");
		assertEquals(lq.size(), 1);
		assertFalse(lq.isEmpty());
		
		lq.enqueue("Two");
		assertEquals(lq.size(), 2);
		assertFalse(lq.isEmpty());
		
		//Test setting capacity below size
		try {
			lq.setCapacity(1);
		} catch (IllegalArgumentException e) {
			assertEquals(lq.size(), 2);
			assertFalse(lq.isEmpty());
		}
		
		//Test setting a valid capacity
		lq.setCapacity(20);
		assertEquals(lq.size(), 2);
		assertFalse(lq.isEmpty());
	}

}
