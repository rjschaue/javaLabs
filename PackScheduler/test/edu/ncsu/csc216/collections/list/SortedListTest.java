package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Used to test the SortedList class
 * @author Joey Schauer
 *
 */
public class SortedListTest {

	/**
	 * Tests the constructor for SortedList
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("apple"));
		
		//Test that the list grows by adding at least 11 elements
		assertTrue(list.add("apple"));
		assertTrue(list.contains("apple"));
		
		assertTrue(list.add("pear"));
		assertTrue(list.contains("pear"));
		
		assertTrue(list.add("strawberry"));
		assertTrue(list.contains("strawberry"));
		
		assertTrue(list.add("watermelon"));
		assertTrue(list.contains("watermelon"));
		
		assertTrue(list.add("orange"));
		assertTrue(list.contains("orange"));
		
		assertTrue(list.add("kiwi"));
		assertTrue(list.contains("kiwi"));
		
		assertTrue(list.add("blueberry"));
		assertTrue(list.contains("blueberry"));
		
		assertTrue(list.add("blackberry"));
		assertTrue(list.contains("blackberry"));
		
		assertTrue(list.add("cherry"));
		assertTrue(list.contains("cherry"));
		
		assertTrue(list.add("grape"));
		assertTrue(list.contains("grape"));
		
		assertTrue(list.add("lemon"));
		assertTrue(list.contains("lemon"));
		
		assertEquals(11, list.size());
	}

	/**
	 * Tests the add method for SortedList
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front, middle and back of the list
		assertTrue(list.add("apple"));
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		
		assertTrue(list.add("pear"));
		assertEquals(3, list.size());
		assertEquals("pear", list.get(2));
		
		assertTrue(list.add("orange"));
		assertEquals(4, list.size());
		assertEquals("orange", list.get(2));
		
		//Test adding a null element
		String test = null;
		try {
			list.add(test);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
		}
		
		//Test adding a duplicate element
		try {
			list.add("apple");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
		}
	}

	/**
	 * Tests the get method for SortedList
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
				
		//Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
			assertEquals(0, list.size());
		}
		
		//Add some elements to the list
		list.add("apple");
		list.add("banana");
		list.add("orange");
		
		//Test getting an element at an index < 0
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		
		//Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		
	}

	/**
	 * Tests the remove method for SortedList
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
			assertEquals(0, list.size());
		}
		
		//Add some elements to the list - at least 4
		list.add("apple");
		list.add("banana");
		list.add("orange");
		list.add("pear");
		
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Test removing a middle element
		String test = list.remove(2);
		assertEquals("orange", test);
		assertEquals(3, list.size());
		
		//Test removing the last element
		test = list.remove(2);
		assertEquals("pear", test);
		assertEquals(2, list.size());
		
		//Test removing the first element
		test = list.remove(0);
		assertEquals("apple", test);
		assertEquals(1, list.size());
		
		//Test removing the last element
		test = list.remove(0);
		assertEquals("banana", test);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests the indexOf method for SortedList
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("banana"));
		
		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("orange");
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("orange"));
		assertEquals(-1, list.indexOf("carrot"));
		assertEquals(-1, list.indexOf("celery"));
		
		//Test checking the index of null
		String test = null;
		try {
			list.indexOf(test);
			fail();
		} catch (NullPointerException e) {
			assertEquals(3, list.size());
		}
		
	}

	/**
	 * Tests the clear method for SortedList
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("orange");
		assertEquals(3, list.size());
		
		//Clear the list
		list.clear();
		
		//Test that the list is empty
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}

	/**
	 * Tests the isEmpty method for SortedList
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		
		//Add at least one element
		list.add("apple");
		
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
	}

	/**
	 * Tests the contains method for SortedList
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("banana"));
		
		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("orange");		
		
		//Test some true and false cases
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("banana"));
		assertTrue(list.contains("orange"));
		assertFalse(list.contains("carrot"));
		assertFalse(list.contains("celery"));
	}
	
	/**
	 * Tests the equals method for SortedList
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("orange");	
		
		list2.add("apple");
		list2.add("banana");
		list2.add("orange");
		
		list3.add("carrot");
		list3.add("celery");
		list3.add("broccoli");
		
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertTrue(list1.equals(list1));
		assertTrue(list2.equals(list2));
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list1));
		assertFalse(list3.equals(list2));
	}

	/**
	 * Tests the hashCode method for SortedList
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("orange");	
		
		list2.add("apple");
		list2.add("banana");
		list2.add("orange");
		
		list3.add("carrot");
		list3.add("celery");
		list3.add("broccoli");
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list1.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
	}

}
 