
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test class for Schedule
 * @author Joey Schauer
 */
public class ScheduleTest {
	
	/**
	 * Method to test Schedule constructor
	 */
	@Test
	public void testSchedule() {
		Schedule schedule = new Schedule();
		assertEquals(schedule.getTitle(), "My Schedule");
	}

	/**
	 * Method to test addCourseToSchedule
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule schedule = new Schedule();
		Course course1 = new Course("CSC116", "Java1", "001", 3, "teach1", "MW", 1200, 1300);
		Course course2 = new Course("CSC216", "Java2", "001", 4, "teach1", "TH", 1200, 1300);
		Course course3 = new Course("CSC316", "Java3", "001", 3, "teach1", "MW", 1200, 1300);
		
		assertTrue(schedule.addCourseToSchedule(course1));
		assertTrue(schedule.addCourseToSchedule(course2));
		
		try {
			schedule.addCourseToSchedule(course1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "You are already enrolled in CSC116");
		}
		
		try {
			schedule.addCourseToSchedule(course3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The course cannot be added due to a conflict.");
		}
		
		
	}
	
	/**
	 * Method to test removeCourseFromSchedule
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();
		Course course1 = new Course("CSC116", "Java1", "001", 3, "teach1", "MW", 1200, 1300);
		Course course2 = new Course("CSC216", "Java2", "001", 4, "teach1", "TH", 1200, 1300);
		Course course3 = new Course("CSC316", "Java3", "001", 3, "teach1", "MW", 1200, 1300);
		
		assertTrue(schedule.addCourseToSchedule(course1));
		assertTrue(schedule.addCourseToSchedule(course2));
		
		assertFalse(schedule.removeCourseFromSchedule(course3));
		assertTrue(schedule.removeCourseFromSchedule(course1));
		assertTrue(schedule.removeCourseFromSchedule(course2));
	}
	
	/**
	 * Method to test resetSchedule
	 */
	@Test
	public void testResetSchedule() {
		Schedule schedule = new Schedule();
		Course course1 = new Course("CSC116", "Java1", "001", 3, "teach1", "MW", 1200, 1300);
		Course course2 = new Course("CSC216", "Java2", "001", 4, "teach1", "TH", 1200, 1300);
		
		assertTrue(schedule.addCourseToSchedule(course1));
		assertTrue(schedule.addCourseToSchedule(course2));
		
		schedule.resetSchedule();
		
		assertFalse(schedule.removeCourseFromSchedule(course1));
		assertFalse(schedule.removeCourseFromSchedule(course2));
    }
	
	/**
	 * Method to test getScheduledCourses
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule schedule = new Schedule();
		Course course1 = new Course("CSC116", "Java1", "001", 3, "teach1", "MW", 1200, 1300);
		Course course2 = new Course("CSC216", "Java2", "001", 4, "teach1", "TH", 1200, 1300);
		
		schedule.getScheduledCourses();
		
		assertTrue(schedule.addCourseToSchedule(course1));
		assertTrue(schedule.addCourseToSchedule(course2));
		
		String [][] scheduleCourses = schedule.getScheduledCourses();
		
		String[][] scheduleString = new String[2][4];
        Course c = course1;
        String[] s = c.getShortDisplayArray();
        for (int j = 0; j < 4; j++) {
        	scheduleString[0][j] = s[j];
        }
        c = course2;
        s = c.getShortDisplayArray();
        for (int j = 0; j < 4; j++) {
        	scheduleString[1][j] = s[j];
        }
        
        assertEquals(scheduleCourses[0][0], scheduleString[0][0]);
        assertEquals(scheduleCourses[0][1], scheduleString[0][1]);
        assertEquals(scheduleCourses[0][2], scheduleString[0][2]);
        assertEquals(scheduleCourses[0][3], scheduleString[0][3]);
        
        assertEquals(scheduleCourses[1][0], scheduleString[1][0]);
        assertEquals(scheduleCourses[1][1], scheduleString[1][1]);
        assertEquals(scheduleCourses[1][2], scheduleString[1][2]);
        assertEquals(scheduleCourses[1][3], scheduleString[1][3]);
	}
	
	/**
	 * Method to test setTitle
	 */
	@Test
	public void testSetTitle() {
		Schedule schedule = new Schedule();
		assertEquals(schedule.getTitle(), "My Schedule");
		
		schedule.setTitle("New Title");
		assertEquals(schedule.getTitle(), "New Title");
		
		try {
			schedule.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Title cannot be null.");
		}
	}
}
