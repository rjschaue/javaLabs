package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * This class handles enrollment of students into courses
 * @author Joey Schauer
 */
public class CourseRoll {
	/** A linked abstract list of students */
	private LinkedAbstractList<Student> roll;
	/** The capacity for enrollment in the course */
	private int enrollmentCap;
	/** The minimum enrollment value for a course */
	private static final int MIN_ENROLLMENT = 10;
	/** The maximum enrollment value for a course */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * The constructor for CourseRoll
	 * @param enrollmentCap the capacity for enrollment for the course
	 */
	public CourseRoll(int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
	}
	
	/**
	 * Returns the enrollment capacity of the course
	 * @return the enrollment capacity of the course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Sets the enrollment capacity for the course
	 * @param enrollmentCap the enrollment capacity to set for the course
	 * @throws IllegalArgumentException if the enrollment cap is less than the minimum or greater than the maximum
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (roll.size() > enrollmentCap) {
			throw new IllegalArgumentException();
		}
		this.enrollmentCap = enrollmentCap;
	}
	
	/**
	 * Enrolls a given student in the course
	 * @param s the student to be enrolled in the course
	 * @throws IllegalArgumentException if s is null, there are no open seats, s is already enrolled
	 * 		   or the LinkedAbstractList throws an exception of it's own
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (getOpenSeats() == 0) {
			throw new IllegalArgumentException();
		}
		if (!canEnroll(s)) {
			throw new IllegalArgumentException();
		}
		try {
			roll.add(roll.size(), s);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Drops a given student from the course
	 * @param s the student to be dropped
	 * @throws IllegalArgumentException if s is null or the LinkedAbstractList throws it's own exception
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		try {
			roll.remove(s);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns whether a student can enroll in the course or not
	 * @param s the student to determine if they can enroll
	 * @return If the student can enroll in the course (true) or not (false)
	 */
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0) {
			return false;
		}
		if (roll.contains(s)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns how many open seats are left in the course
	 * @return how many open seats are left in the course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
}
