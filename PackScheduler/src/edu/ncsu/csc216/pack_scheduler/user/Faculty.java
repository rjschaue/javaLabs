package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Class for faculty members
 * @author Joey Schauer
 */
public class Faculty extends User {
	/** the maximum number of courses a faculty member can teach */
	private int maxCourses;
	/** gets the schedule for the faculty member */
	private FacultySchedule schedule;
	/** the absolute minimum number of courses a faculty member can teach */
	private static final int MIN_COURSES = 1;
	/** the absolute maximum number of courses a faculty member can teach */
	private static final int MAX_COURSES = 3;

	/**
	 * Constructor for Faculty
	 * @param firstName the first name of the faculty member
	 * @param lastName the last name of the faculty member
	 * @param id the id for the faculty member
	 * @param email the email of the faculty member
	 * @param password the password of the faculty member
	 * @param maxCourses the maximum number of courses a faculty member can teach
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}
	
	/**
	 * Sets the maximum number of courses for the faculty member
	 * @param maxCourses the max number of courses to set for the faculty member
	 * @throws IllegalArgumentException if the given max courses value is invalid
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Returns the max number of courses for the faculty member
	 * @return the max number of courses for the faculty member
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * Returns the faculty member's schedule
	 * @return the faculty member's schedule
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Returns true if the number of scheduled courses is greater than the faculty's max courses
	 * @return true if the number of scheduled courses is greater than the faculty's max courses
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}
	
	/**
	 * Generates hash code for faculty
	 * @return faculty as hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Compares if two faculty members are equal
	 * @return if two faculty members are equal (true) or not (false)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Converts faculty fields to a string
	 * @return faculty fields as a string with commas separating the fields
	 */
	@Override
	public String toString() {
		String faculty = getFirstName() + "," + getLastName() + ","
						 + getId() + "," + getEmail() + "," + getPassword() + "," + getMaxCourses();
		return faculty;
	}
}
