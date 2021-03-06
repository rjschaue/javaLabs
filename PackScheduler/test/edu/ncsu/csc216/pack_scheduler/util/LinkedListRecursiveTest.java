/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Class to test LinkedListRecursive
 * @author Joey Schauer
 */
public class LinkedListRecursiveTest {

	/**
	 * Method to test add
	 */
	@Test
	public void testAdd() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();		
		assertTrue(list.size() == 0);
		
		//Add a string to an empty list
		list.add(0, "String0");
		assertTrue(list.size() == 1);
		
		//Add a string to the end of a list
		list.add(1, "String1");
		assertTrue(list.size() == 2);
		
		//Add a null string to a list
		try {
			list.add(2, null);
			fail();
		} catch (NullPointerException e) {
			assertTrue(list.size() == 2);
		}
		
		//Add a duplicate string to a list
		try {
			list.add(2, "String1");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(list.size() == 2);
		}
		
		//Add a string outside of the bounds of the list (upper)
		try {
			list.add(10, "String3");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 2);
		}
		
		//Add a string outside of the bounds of the list (lower)
		try {
			list.add(-1, "String3");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 2);
		}
		
		//Add many strings
		for (int i = 2; i < 11; i++) {
			list.add(i, "String" + i);
			assertTrue(list.size() == (i + 1));
		}
		
		//Add a string in the middle of a list
		list.add(6, "NewString");
		assertTrue(list.size() == 12);
		assertTrue(list.get(5).equals("String5"));
		assertTrue(list.get(7).equals("String6"));
		
		//Add a string already in the list
		try {
			list.add("String3");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(list.size() == 12);
		}

		//Add a string to the end using the element only add
		assertTrue(list.add("EString"));
		assertTrue(list.size() == 13);
		assertTrue(list.get(12).equals("EString"));
		
		LinkedListRecursive<String> list2 = new LinkedListRecursive<String>();		
		//Add a string to an empty list using the element only add
		assertTrue(list2.add("NewList"));
		assertTrue(list2.size() == 1);
		assertTrue(list2.get(0).equals("NewList"));
	}
	
	/**
	 * Method to test remove
	 */
	@Test
	public void testRemove() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();	
		assertTrue(list.size() == 0);
		
		for (int i = 0; i < 10; i++) {
			list.add(i, "String" + i);
			assertTrue(list.size() == (i + 1));
		}
		
		//Remove a string outside of the bounds of the list (lower)
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 10);
		}
		
		//Remove a string outside of the bounds of the list (upper)
		try {
			list.remove(11);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 10);
		}
		
		//Remove a string from the beginning of the list
		assertEquals(list.remove(0), "String0");
		assertTrue(list.size() == 9);
		
		//Remove a string from the end of the list
		assertEquals(list.remove(8), "String9");
		assertTrue(list.size() == 8);
		
		//Remove a string from the middle of the list
		assertEquals(list.remove(4), "String5");
		assertTrue(list.size() == 7);
		
		//Element only remove a null string 
		assertFalse(list.remove(null));

		
		LinkedListRecursive<String> list2 = new LinkedListRecursive<String>();	
		//Element only remove a string from an empty list
			assertFalse(list2.remove("String"));
			assertTrue(list2.size() == 0);
		
		//Remove an element not on the list
		assertFalse(list.remove("NotInList"));
		
		//Remove from front of list
		assertTrue(list.remove("String1"));
		assertTrue(list.size() == 6);
		
		//Remove from end of list
		assertTrue(list.remove("String8"));
		assertTrue(list.size() == 5);
		
		//Remove from middle of list
		assertTrue(list.remove("String4"));
		assertTrue(list.size() == 4);
	}
	
	/**
	 * Method to test set
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();		
		assertTrue(list.size() == 0);
		
		for (int i = 0; i < 10; i++) {
			list.add(i, "String" + i);
			assertTrue(list.size() == (i + 1));
		}
		
		//Set a null string to a list
		try {
			list.set(0, null);
			fail();
		} catch (NullPointerException e) {
			assertTrue(list.size() == 10);
		}
		
		//Set a duplicate string to a list
		try {
			list.set(1, "String1");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(list.size() == 10);
		}
		
		//Set a string outside of the bounds of the list (upper)
		try {
			list.set(11, "String11");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 10);
		}
		
		//Set a string outside of the bounds of the list (lower)
		try {
			list.set(-1, "String-1");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 10);
		}
		
		//Set a string at the front of the list
		assertEquals(list.set(0, "newString0"), "String0");
		assertTrue(list.size() == 10);
		
		//Set a string at the end of the list
		assertEquals(list.set(9, "newString9"), "String9");
		assertTrue(list.size() == 10);
		
		//Set a string in the middle of the list
		assertEquals(list.set(5, "newString5"), "String5");
		assertTrue(list.size() == 10);
	}
	
	/**
	 * Method to test get
	 */
	@Test
	public void testGet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();		
		assertTrue(list.size() == 0);
		
		for (int i = 0; i < 10; i++) {
			list.add(i, "String" + i);
			assertTrue(list.size() == (i + 1));
		}
		
		//Get a string outside of the bounds of the list (upper)
		try {
			list.get(11);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 10);
		}
		
		//Get a string outside of the bounds of the list (lower)
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.size() == 10);
		}
		
		//Get strings from the list
		for (int i = 0; i < 10; i++) {
			assertEquals(list.get(i), "String" + i);
		}
	}

}
