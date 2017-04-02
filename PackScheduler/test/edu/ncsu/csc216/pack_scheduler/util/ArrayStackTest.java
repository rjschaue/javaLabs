package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Test class for ArrayStack
 * @author Joey Schauer
 */
public class ArrayStackTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		ArrayStack<String> as = new ArrayStack<String>(10);
		assertEquals(as.size(), 0);
		assertTrue(as.isEmpty());
		
		//Test pushing a null object
		try {
			as.push(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(as.size(), 0);
			assertTrue(as.isEmpty());
		}
		
		//Test pushing a valid string
		as.push("One");
		assertEquals(as.size(), 1);
		assertFalse(as.isEmpty());
		
		for (int i = 2; i < 11; i++) {
			as.push("New" + i);
			assertEquals(as.size(), i);
		}
		
		//Test pushing an object over capacity
		try {
			as.push("TooMany");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(as.size(), 10);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#pop()}.
	 */
	@Test
	public void testPop() {
		ArrayStack<String> as = new ArrayStack<String>(10);
		assertEquals(as.size(), 0);
		assertTrue(as.isEmpty());
		
		//Test popping an empty list
		try {
			as.pop();
			fail();
		} catch (EmptyStackException e) {
			assertEquals(as.size(), 0);
			assertTrue(as.isEmpty());
		}
		
		as.push("One");
		assertEquals(as.size(), 1);
		assertFalse(as.isEmpty());
		
		//Test popping with a populated list
		assertEquals(as.pop(), "One");
		assertEquals(as.size(), 0);
		assertTrue(as.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		ArrayStack<String> as = new ArrayStack<String>(10);
		assertEquals(as.size(), 0);
		assertTrue(as.isEmpty());
		
		//Test setting capacity to 0
		try {
			as.setCapacity(0);
		} catch (IllegalArgumentException e) {
			assertEquals(as.size(), 0);
			assertTrue(as.isEmpty());
		}
		
		as.push("One");
		assertEquals(as.size(), 1);
		assertFalse(as.isEmpty());
		
		as.push("Two");
		assertEquals(as.size(), 2);
		assertFalse(as.isEmpty());
		
		//Test setting capacity below size
		try {
			as.setCapacity(1);
		} catch (IllegalArgumentException e) {
			assertEquals(as.size(), 2);
			assertFalse(as.isEmpty());
		}
	}

}
