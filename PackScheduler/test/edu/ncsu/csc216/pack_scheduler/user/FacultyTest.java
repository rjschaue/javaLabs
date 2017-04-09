package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Faculty
 * @author Joey Schauer
 */
public class FacultyTest {

	/** Faculty's First Name */
	private static final String FIRST_NAME = "Joey";
	/** Faculty's Last Name */
	private static final String LAST_NAME = "Schauer";
	/** Faculty's id */
	private static final String ID = "rjschaue";
	/** Faculty's email */
	private static final String EMAIL = "rjschaue@ncsu.edu";
	/** Faculty's password */
	private static final String PASSWORD = "password";
	/** Faculty's max credits */
	private static final int MAX_COURSES = 3;

	/**
	 * Tests the Faculty constructor
	 */
	@Test
	public void testFacultyStringStringStringStringStringInt() {
		Faculty f;
				
		// Testing for null first name
		f = null;
		try {
		    f = new Faculty(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(f);
		}
		
		// Testing for null last name
		f = null;
		try {
		    f = new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(f);
		}
		
		// Testing for null id
		f = null;
		try {
		    f = new Faculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(f);
		}
		
		// Testing for and empty string id
		f = null;
		try {
		    f = new Faculty(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(f);
		}
		
		// Testing for null email
		f = null;
		try {
		    f = new Faculty(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(f);
		}
		
		// Testing for null password
		f = null;
		try {
		    f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_COURSES);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(f);
		}
		
		// Testing for invalid max credits
		f = null;
		try {
		    f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 0);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(f);
		}
		
		//Test a valid construction
		f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test setFirstName
	 */
	@Test
	public void testSetFirstName() {
		Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
		
		//Test that setting the first name to null doesn't change anything
		try {
		    f.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the first name to an empty string doesn't change anything
		try {
		    f.setFirstName("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test a valid first name change
		f.setFirstName("Robert");
		assertEquals("Robert", f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
	}

	/**
	 * Test setLastName
	 */
	@Test
	public void testSetLastName() {
		Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
		
		//Test that setting the last name to null doesn't change anything
		try {
		    f.setLastName(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the last name to an empty string doesn't change anything
		try {
		    f.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test a valid last name change
		f.setLastName("Bare");
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals("Bare", f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
	}

	/**
	 * Test setEmail
	 */
	@Test
	public void testSetEmail() {
		Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
		
		//Test that setting the email to null doesn't change anything
		try {
		    f.setEmail(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the email to an empty string doesn't change anything
		try {
		    f.setEmail("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the email with no @ symbol doesn't change anything
		try {
		    f.setEmail("rjschauencsu.edu");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the email with no . doesn't change anything
		try {
		    f.setEmail("rjschaue@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the email with the last instance of . before the first @ doesn't change anything
		try {
		    f.setEmail("rj.schaue@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test a valid email change
		f.setEmail("schauerrj@ncsu.edu");
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals("schauerrj@ncsu.edu", f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
	}
	
	/**
	 * Test setPassword
	 */
	@Test
	public void testSetPassword() {
		Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
		
		//Test that setting the password to null doesn't change anything
		try {
		    f.setPassword(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the password to an empty string doesn't change anything
		try {
		    f.setPassword("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test a valid password change
		f.setPassword("drowssap");
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals("drowssap", f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
	}

	/**
	 * Test setMaxCredits
	 */
	@Test
	public void testSetMaxCredits() {
		Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
		
		//Test that setting the max credits to a number less than 3 doesn't change anything
		try {
		    f.setMaxCourses(0);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test that setting the max credits to a number greater than 18 doesn't change anything
		try {
		    f.setMaxCourses(4);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(MAX_COURSES, f.getMaxCourses());
		}
		
		//Test a valid max credits top boundary value
		f.setMaxCourses(3);
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(MAX_COURSES, f.getMaxCourses());
		
		//Test a valid max credits low boundary value
		f.setMaxCourses(1);
		assertEquals(FIRST_NAME, f.getFirstName());
		assertEquals(LAST_NAME, f.getLastName());
		assertEquals(ID, f.getId());
		assertEquals(EMAIL, f.getEmail());
		assertEquals(PASSWORD, f.getPassword());
		assertEquals(1, f.getMaxCourses());
	}

	/**
	 * Tests equals
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s3 = new Faculty("Bob", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s4 = new Faculty(FIRST_NAME, "Hildebrand", ID, EMAIL, PASSWORD, MAX_COURSES);
		User s5 = new Faculty(FIRST_NAME, LAST_NAME, "schauerj", EMAIL, PASSWORD, MAX_COURSES);
		User s6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_COURSES);
		User s7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "anotherpassword", MAX_COURSES);
		User s8 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
		String string = "Test";
		
		//Test for same object
		assertTrue(s1.equals(s1));
		
		//Test for different class
		assertFalse(s1.equals(string));
		
		//Test for equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		//Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
	}

	/**
	 * Tests hashCode
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s3 = new Faculty("Bob", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s4 = new Faculty(FIRST_NAME, "Hildebrand", ID, EMAIL, PASSWORD, MAX_COURSES);
		User s5 = new Faculty(FIRST_NAME, LAST_NAME, "schauerj", EMAIL, PASSWORD, MAX_COURSES);
		User s6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_COURSES);
		User s7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "anotherpassword", MAX_COURSES);
		User s8 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
		
		//Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());
		
		//Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Test toString
	 */
	@Test
	public void testToString() {
		//Test toString
		User faculty1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		String string1 = "Joey,Schauer,rjschaue,rjschaue@ncsu.edu,password,3";
		assertEquals(string1, faculty1.toString());
	}
}
