/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Class to test Activity
 * @author Joey Schauer
 *
 */
public class ActivityTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment capacity */
	private static final int ENROLLMENT_CAP = 100;
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Tests setActivityTime method
	 */
	@Test
	public void testSetActivityTime() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "A");
	    
	    //Test setting a valid activity time
	    a1.setActivityTime(1200, 1300);
	    assertEquals(a1.getStartTime(), 1200);
	    assertEquals(a1.getEndTime(), 1300);
	    
	    //Test setting an activity time for an arranged class
	    a2.setActivityTime(1100, 1200);
	    assertEquals(a2.getStartTime(), 0);
	    assertEquals(a2.getEndTime(), 0);
	    
	    //Test setting an invalid activity time - start later than end
	    try {
	    	a1.setActivityTime(1300, 1200);
	    	fail("Should not reach this");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid meeting times", e.getMessage());
		}
	    
	    //Test setting an invalid activity time - start less than 0
	    try {
	    	a1.setActivityTime(-100, 1200);
	    	fail("Should not reach this");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid meeting times", e.getMessage());
		}
	    
	  //Test setting an invalid activity time - start greater than 2400
	    try {
	    	a1.setActivityTime(2500, 1200);
	    	fail("Should not reach this");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid meeting times", e.getMessage());
		}
	    
	  //Test setting an invalid activity time - end less than 0
	    try {
	    	a1.setActivityTime(1200, -100);
	    	fail("Should not reach this");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid meeting times", e.getMessage());
		}
	    
	  //Test setting an invalid activity time - end greater than 2400
	    try {
	    	a1.setActivityTime(1200, 2500);
	    	fail("Should not reach this");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid meeting times", e.getMessage());
		}
	}
	    
	/**
	 * Tests getMeetingString method
	 */
	@Test
	public void testGetMeetingString() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "MW", 30, 130);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "A");
	    
	    //Test arranged course string
	    assertEquals("Arranged", a2.getMeetingString());
	    
	    //Test start time less than 100
	    assertEquals("MW 12:30AM-1:30AM", a1.getMeetingString());
	    
	    //Test end time less than 100
	    a1.setActivityTime(15, 45);
	    assertEquals("MW 12:15AM-12:45AM", a1.getMeetingString());
	}
	/**
	 * Test method for checkConflict
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "TH", 1330, 1445);

	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Schedule conflict.", e.getMessage());
	    }
	    
	  //Using the same data, switch a1 and a2 in the checkConflict
	    try {
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Schedule conflict.", e.getMessage());
	    }
	    
	  //Update a1 with an end time that overlaps the start time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1200, 1330);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Schedule conflict.", e.getMessage());
	    }
	    
	  //Using the same data, switch a1 and a2 in the checkConflict
	    try {
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Schedule conflict.", e.getMessage());
	    }
	}
	

	/**
	 * Tests that the equals method works for all activity fields.
	 */
	@Test
	public void testEqualsObject() {
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
		Activity c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 830, END_TIME);
		Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, 1400);
		
		//Test for equality in both directions
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		
		//Test for each of the fields
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c1.equals(c5));
		assertFalse(c1.equals(c6));
		assertFalse(c1.equals(c7));
		assertFalse(c1.equals(c8));
		assertFalse(c1.equals(c9));
	}
	
	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
		Activity c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 830, END_TIME);
		Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, 1400);
		
		//Test for the same hash code for the same values
		assertEquals(c1.hashCode(), c2.hashCode());
		
		//Test for each of the fields
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c1.hashCode(), c4.hashCode());
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
		assertNotEquals(c1.hashCode(), c8.hashCode());
		assertNotEquals(c1.hashCode(), c9.hashCode());
	}
}
