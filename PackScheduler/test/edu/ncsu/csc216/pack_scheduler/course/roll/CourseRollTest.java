/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Test class for CourseRoll
 * @author Joey Schauer
 */
public class CourseRollTest {
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
	 * Tests the enroll method
	 */
	@Test
	public void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student("John", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student("Bob", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, "Hildebrand", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "schauerj", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "anotherpassword", MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
		Student s9 = new Student(FIRST_NAME, "Ngyuen", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, "Smith", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s11 = new Student(FIRST_NAME, "Nelson", ID, EMAIL, PASSWORD, MAX_CREDITS);

		CourseRoll roll = null;
		
		//Construct a CourseRoll below minimum capacity
		try {
			roll = new CourseRoll(c, 9);
		} catch (IllegalArgumentException e) {
			assertTrue(roll == null);
		}
		
		//Construct a CourseRoll above maximum capacity
		try {
			roll = new CourseRoll(c, 251);
		} catch (IllegalArgumentException e) {
			assertTrue(roll == null);
		}
		
		//Construct a new CourseRoll
		roll = c.getCourseRoll();
		assertTrue(roll.getEnrollmentCap() == 10);
		
		//Try adding a null student to the course roll
		try {
			roll.enroll(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(roll.getOpenSeats() == 10);
		}
		
		//Enroll a valid student
		roll.enroll(s1);
		assertTrue(roll.getOpenSeats() == 9);
		assertFalse(roll.canEnroll(s1));
		
		//Try enrolling a duplicate student
		try {
			roll.enroll(s1);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(roll.getOpenSeats() == 9);
		}
		
		//Enroll students up to capacity
		roll.enroll(s2);
		assertTrue(roll.getOpenSeats() == 8);
		assertFalse(roll.canEnroll(s2));
		
		roll.enroll(s3);
		assertTrue(roll.getOpenSeats() == 7);
		assertFalse(roll.canEnroll(s3));
		
		roll.enroll(s4);
		assertTrue(roll.getOpenSeats() == 6);
		assertFalse(roll.canEnroll(s4));
		
		roll.enroll(s5);
		assertTrue(roll.getOpenSeats() == 5);
		assertFalse(roll.canEnroll(s5));
		
		roll.enroll(s6);
		assertTrue(roll.getOpenSeats() == 4);
		assertFalse(roll.canEnroll(s6));
		
		roll.enroll(s7);
		assertTrue(roll.getOpenSeats() == 3);
		assertFalse(roll.canEnroll(s7));
		
		roll.enroll(s8);
		assertTrue(roll.getOpenSeats() == 2);
		assertFalse(roll.canEnroll(s8));
		
		roll.enroll(s9);
		assertTrue(roll.getOpenSeats() == 1);
		assertFalse(roll.canEnroll(s9));
		
		roll.enroll(s10);
		assertTrue(roll.getOpenSeats() == 0);
		assertFalse(roll.canEnroll(s10));
		
		//Try enrolling a student past capacity (waitlist)
		roll.enroll(s11);
		assertEquals(roll.getNumberOnWaitlist(), 1);
	}

	/**
	 * Tests the drop method
	 */
	@Test
	public void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student("John", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student("Bob", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, "Hildebrand", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "schauerj", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "anotherpassword", MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
		Student s9 = new Student(FIRST_NAME, "Ngyuen", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, "Smith", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s11 = new Student(FIRST_NAME, "Nelson", ID, EMAIL, PASSWORD, MAX_CREDITS);
		
		CourseRoll roll = c.getCourseRoll();
		
		//Enroll students just over capacity
		roll.enroll(s1);
		assertTrue(roll.getOpenSeats() == 9);
		assertFalse(roll.canEnroll(s1));
		
		roll.enroll(s2);
		assertTrue(roll.getOpenSeats() == 8);
		assertFalse(roll.canEnroll(s2));
		
		roll.enroll(s3);
		assertTrue(roll.getOpenSeats() == 7);
		assertFalse(roll.canEnroll(s3));
		
		roll.enroll(s4);
		assertTrue(roll.getOpenSeats() == 6);
		assertFalse(roll.canEnroll(s4));
		
		roll.enroll(s5);
		assertTrue(roll.getOpenSeats() == 5);
		assertFalse(roll.canEnroll(s5));
		
		roll.enroll(s6);
		assertTrue(roll.getOpenSeats() == 4);
		assertFalse(roll.canEnroll(s6));
		
		roll.enroll(s7);
		assertTrue(roll.getOpenSeats() == 3);
		assertFalse(roll.canEnroll(s7));
		
		roll.enroll(s8);
		assertTrue(roll.getOpenSeats() == 2);
		assertFalse(roll.canEnroll(s8));
		
		roll.enroll(s9);
		assertTrue(roll.getOpenSeats() == 1);
		assertFalse(roll.canEnroll(s9));
		
		roll.enroll(s10);
		assertTrue(roll.getOpenSeats() == 0);
		assertFalse(roll.canEnroll(s10));
		
		roll.enroll(s11);
		assertEquals(roll.getNumberOnWaitlist(), 1);
		
		//Try dropping a null student
		try {
			roll.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(roll.getOpenSeats() == 0);
		}
		
		//Drop a student and see if one is moved from waitlist
		roll.drop(s10);
		assertTrue(roll.getOpenSeats() == 0);
		assertTrue(roll.getNumberOnWaitlist() == 0);
		
		roll.drop(s11);
		assertTrue(roll.getOpenSeats() == 1);
		assertTrue(roll.canEnroll(s11));
	}
}
