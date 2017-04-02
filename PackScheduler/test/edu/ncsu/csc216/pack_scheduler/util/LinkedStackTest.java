package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for LinkedStack
 * @author Joey Schauer
 */
public class LinkedStackTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		LinkedStack<String> ls = new LinkedStack<String>(10);
		assertEquals(ls.size(), 0);
		assertTrue(ls.isEmpty());
		
		//Test pushing a null object
		try {
			ls.push(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(ls.size(), 0);
			assertTrue(ls.isEmpty());
		}
		
		//Test pushing a valid string
		ls.push("One");
		assertEquals(ls.size(), 1);
		assertFalse(ls.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testPop() {
		LinkedStack<String> ls = new LinkedStack<String>(10);
		assertEquals(ls.size(), 0);
		assertTrue(ls.isEmpty());
		
		//Test popping an empty list
		try {
			ls.pop();
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(ls.size(), 0);
			assertTrue(ls.isEmpty());
		}
		
		ls.push("One");
		assertEquals(ls.size(), 1);
		assertFalse(ls.isEmpty());
		
		//Test popping with a populated list
		assertEquals(ls.pop(), "One");
		assertEquals(ls.size(), 0);
		assertTrue(ls.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		LinkedStack<String> ls = new LinkedStack<String>(10);
		assertEquals(ls.size(), 0);
		assertTrue(ls.isEmpty());
		
		//Test setting capacity to 0
		try {
			ls.setCapacity(0);
		} catch (IllegalArgumentException e) {
			assertEquals(ls.size(), 0);
			assertTrue(ls.isEmpty());
		}
		
		ls.push("One");
		assertEquals(ls.size(), 1);
		assertFalse(ls.isEmpty());
		
		ls.push("Two");
		assertEquals(ls.size(), 2);
		assertFalse(ls.isEmpty());
		
		//Test setting capacity below size
		try {
			ls.setCapacity(1);
		} catch (IllegalArgumentException e) {
			assertEquals(ls.size(), 2);
			assertFalse(ls.isEmpty());
		}
		
		//Test setting a valid capacity
		ls.setCapacity(20);
		assertEquals(ls.size(), 2);
		assertFalse(ls.isEmpty());
	}

}
