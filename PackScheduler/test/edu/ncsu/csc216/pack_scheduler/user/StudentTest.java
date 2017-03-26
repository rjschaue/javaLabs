package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Tests the Student Class
 * 
 * @author Joey Schauer
 *
 */
public class StudentTest {
	/** Student First Name */
	private static final String FIRST_NAME = "Joey";
	/** Student Last Name */
	private static final String LAST_NAME = "Schauer";
	/** Student id */
	private static final String ID = "rjschaue";
	/** Student email */
	private static final String EMAIL = "rjschaue@ncsu.edu";
	/** Student password */
	private static final String PASSWORD = "password";
	/** Student max credits */
	private static final int MAX_CREDITS = 9;

	/**
	 * Tests the Student constructor with all parameters
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s;
				
		// Testing for null first name
		s = null;
		try {
		    s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null last name
		s = null;
		try {
		    s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null id
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for and empty string id
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null email
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null password
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDITS);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for invalid max credits
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 0);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//Test a valid construction
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests the Student constructor without maxCredits
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		Student s;
		
		// Testing for null first name
		s = null;
		try {
		    s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null last name
		s = null;
		try {
		    s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null id
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null email
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		// Testing for null password
		s = null;
		try {
		    s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//Test a valid construction
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test setFirstName
	 */
	@Test
	public void testSetFirstName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
		
		//Test that setting the first name to null doesn't change anything
		try {
		    s.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the first name to an empty string doesn't change anything
		try {
		    s.setFirstName("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test a valid first name change
		s.setFirstName("Robert");
		assertEquals("Robert", s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
	}

	/**
	 * Test setLastName
	 */
	@Test
	public void testSetLastName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
		
		//Test that setting the last name to null doesn't change anything
		try {
		    s.setLastName(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the last name to an empty string doesn't change anything
		try {
		    s.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test a valid last name change
		s.setLastName("Bare");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals("Bare", s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
	}

	/**
	 * Test setEmail
	 */
	@Test
	public void testSetEmail() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
		
		//Test that setting the email to null doesn't change anything
		try {
		    s.setEmail(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the email to an empty string doesn't change anything
		try {
		    s.setEmail("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the email with no @ symbol doesn't change anything
		try {
		    s.setEmail("rjschauencsu.edu");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the email with no . doesn't change anything
		try {
		    s.setEmail("rjschaue@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the email with the last instance of . before the first @ doesn't change anything
		try {
		    s.setEmail("rj.schaue@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test a valid email change
		s.setEmail("schauerrj@ncsu.edu");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals("schauerrj@ncsu.edu", s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
	}
	
	/**
	 * Test setPassword
	 */
	@Test
	public void testSetPassword() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
		
		//Test that setting the password to null doesn't change anything
		try {
		    s.setPassword(null);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the password to an empty string doesn't change anything
		try {
		    s.setPassword("");
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test a valid password change
		s.setPassword("drowssap");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals("drowssap", s.getPassword());
		assertEquals(18, s.getMaxCredits());
	}

	/**
	 * Test setMaxCredits
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
		
		//Test that setting the max credits to a number less than 3 doesn't change anything
		try {
		    s.setMaxCredits(2);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test that setting the max credits to a number greater than 18 doesn't change anything
		try {
		    s.setMaxCredits(19);
		    fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		
		//Test a valid max credits top boundary value
		s.setMaxCredits(18);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(18, s.getMaxCredits());
		
		//Test a valid max credits low boundary value
		s.setMaxCredits(3);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(3, s.getMaxCredits());
	}

	/**
	 * Tests equals
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s3 = new Student("Bob", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "Hildebrand", ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "schauerj", EMAIL, PASSWORD, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "anotherpassword", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
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
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s3 = new Student("Bob", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "Hildebrand", ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "schauerj", EMAIL, PASSWORD, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "anotherpassword", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
		
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
		//Test toString with full constructor
		User student1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		String string1 = "Joey,Schauer,rjschaue,rjschaue@ncsu.edu,password,9";
		assertEquals(string1, student1.toString());
		
		//Test toString with constructor lacking maxCredits
		User student2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String string2 = "Joey,Schauer,rjschaue,rjschaue@ncsu.edu,password,18";
		assertEquals(string2, student2.toString());
	}

	/**
	 * Test compareTo
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student("Bob", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, "Hildebrand", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "schauerj", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student("Lorik", "Quiin", "lorikq", EMAIL, PASSWORD, MAX_CREDITS);
		
		//Testing a null object
		Student nullStudent = null;
		try {
			s1.compareTo(nullStudent);
			fail();
		} catch (NullPointerException e) {
			assertNull(nullStudent);
		}
		
		//Testing two of the same student
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		
		//Testing students with varying last names
		assertEquals(1, s1.compareTo(s4));
		assertEquals(-1, s4.compareTo(s1));
		
		//Testing students with varying first names
		assertEquals(1, s1.compareTo(s3));
		assertEquals(-1, s3.compareTo(s1));
		
		//Testing students with varying id values
		assertEquals(-1, s1.compareTo(s5));
		assertEquals(1, s5.compareTo(s1));
		
		//Testing students with different last name, first name and id
		assertEquals(1, s1.compareTo(s6));
		assertEquals(-1, s6.compareTo(s1));
	}
	
	/**
	 * Test canAdd
	 */
	@Test
	public void testCanAdd() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Schedule schedule = s.getSchedule();
		Course course1 = new Course("CSC116", "Java1", "001", 3, "teach1", 10, "MW", 1200, 1300);
		Course course2 = new Course("CSC216", "Java2", "001", 4, "teach1", 10, "TH", 1200, 1300);
		Course course3 = new Course("CSC316", "Java3", "001", 3, "teach1", 10, "MW", 1400, 1500);
		Course course4 = new Course("CSC416", "Java4", "001", 4, "teach1", 10, "MW", 1200, 1300);
		
		schedule.addCourseToSchedule(course1);
		schedule.addCourseToSchedule(course2);
		
		//Checking to make sure courses can't be added above max credits
		assertFalse(s.canAdd(course3));
		//Checking to make sure duplicate courses can't be added
		assertFalse(s.canAdd(course1));
		//Checking to make sure conflicting courses can't be added
		assertFalse(s.canAdd(course4));
		//Checking to make sure null courses can't be added
		assertFalse(s.canAdd(null));	
	}
}
