
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * This class handles updating the schedule by adding or removing
 * courses, resetting the schedule or getting scheduled courses
 * @author Joey Schauer
 */
public class Schedule {
	/** The schedule of courses as an array list */
	private ArrayList<Course> schedule;
	/** The title of the schedule */
	private String title;
	
	/**
	 * The null constructor for Schedule
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/**
	 * Adds a given course to the schedule
	 * @param course the course to be added to the schedule
	 * @return whether the course was added (true) or not (false)
	 * @throws IllegalArgumentException if the course is a duplicate or if there is a conflict
	 */
	public boolean addCourseToSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}			
		}
		for (int i = 0; i < schedule.size(); i++) {
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}			
		}
		schedule.add(course);
		return true;
	}
	
	/**
	 * Removes a given course from the schedule
	 * @param course the course to be removed
	 * @return whether the course has been removed (true) or not (false)
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (schedule.contains(course)) {
			schedule.remove(course);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the schedule to an empty array list
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
	}

	/**
	 * Returns the scheduled courses as a string array
	 * @return the scheduled courses as a string array
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleString;
		if (schedule.size() == 0) {
			scheduleString = new String[0][0];
			return scheduleString;
		}
		scheduleString = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
            Course c = schedule.get(i);
            String[] s = c.getShortDisplayArray();
            for (int j = 0; j < 4; j++) {
            	scheduleString[i][j] = s[j];
            }
        }
		return scheduleString;
	}
	
	/**
	 * Sets the title of the schedule to the given title
	 * @param title the new title of the schedule
	 * @throws IllegalArgumentException if the given title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Returns the title of the schedule
	 * @return the title of the schedule
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the number of credits on the schedule
	 * @return the number of credits on the schedule
	 */
	public int getScheduleCredits() {
		int scheduleCredits = 0;
		for (int i = 0; i < schedule.size(); i++) {
			scheduleCredits += schedule.get(i).getCredits();
		}
		return scheduleCredits;
	}
	
	/**
	 * Determines if a course can be added to the schedule
	 * @param course the course to check and see if it can be added
	 * @return if the course can be added (true) or not (false)
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				return false;
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}
}
