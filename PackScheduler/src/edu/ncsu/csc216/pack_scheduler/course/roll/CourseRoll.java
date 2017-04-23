package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * This class handles enrollment of students into courses
 * @author Joey Schauer
 */
public class CourseRoll {
	/** A linked abstract list of students */
	private LinkedAbstractList<Student> roll;
	/** The capacity for enrollment in the course */
	private int enrollmentCap;
	/** A waitlist for the course */
	private LinkedQueue<Student> waitlist;
	/** The course for the roll */
	private Course course;
	/** The minimum enrollment value for a course */
	private static final int MIN_ENROLLMENT = 10;
	/** The maximum enrollment value for a course */
	private static final int MAX_ENROLLMENT = 250;
	/** The stating waitlist size */
	private static final int WAITLIST_SIZE = 10;
	
	/**
	 * The constructor for CourseRoll
	 * @param c is the course for the roll
	 * @param enrollmentCap the capacity for enrollment for the course
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if (c == null) {
			throw new IllegalArgumentException();
		}	
		course = c;
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		setEnrollmentCap(enrollmentCap);
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
	 * @throws IllegalArgumentException if the enrollment cap is less than the minimum or greater than the maximum or exceeds the roll size
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (roll != null) {
			if (roll.size() > enrollmentCap) {
				throw new IllegalArgumentException();
			} else {
				this.enrollmentCap = enrollmentCap;
				roll.setCapacity(enrollmentCap);
			}		
		}
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
		if (roll.contains(s)) {
			throw new IllegalArgumentException();
		}
		if (getOpenSeats() == 0) {
			if (waitlist.size() < WAITLIST_SIZE) {
				waitlist.enqueue(s);
			} else {
				throw new IllegalArgumentException();
			}		
		} else {
			try {
				roll.add(roll.size(), s);
				s.getSchedule().addCourseToSchedule(course);
			} catch (Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
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
	 	int count = 0;
	    if (!waitlist.isEmpty()) {
	        for (int i = 0; i < waitlist.size(); i++) {
				Student student = waitlist.dequeue();
				if (student.equals(s)) {
	                count++;
				} else {
	                waitlist.enqueue(student);
	            }           
			}
	    }
	    if (count == 0) {
	        try {
	            roll.remove(s);
				if (!waitlist.isEmpty()) {
					Student student = waitlist.dequeue();
					roll.add(student);
					student.getSchedule().addCourseToSchedule(course);
				}
	        } catch (Exception e ) {
	            throw new IllegalArgumentException(e.getMessage());
	        }
	    }
	}
	
	/**
	 * Returns whether a student can enroll in the course or not
	 * @param s the student to determine if they can enroll
	 * @return If the student can enroll in the course (true) or not (false)
	 */
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0 && getNumberOnWaitlist() == WAITLIST_SIZE) {
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
	
	/**
	 * Returns the number of students on the waitlist
	 * @return the number of students on the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
	/**
	 * Returns the course roll as a 2d array
	 * @return the course roll as a 2d array
	 */
	public String[][] getCourseRoll() {
		String[][] newRoll = new String[roll.size()][3];
		for (int i = 0; i < roll.size(); i++) {
			newRoll[i][0] = roll.get(i).getFirstName();
			newRoll[i][1] = roll.get(i).getLastName();
			newRoll[i][2] = roll.get(i).getId();
		}
		return newRoll;
	}
}
