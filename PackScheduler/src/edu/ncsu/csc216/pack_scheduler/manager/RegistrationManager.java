package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Manages the login of users and registrar functionality for PackScheduler
 * @author Joey Schauer
 *
 */
public class RegistrationManager {
	/** the instance of the registration manager */
	private static RegistrationManager instance;
	/** gets the course catalog */
	private CourseCatalog courseCatalog;
	/** gets the student directory */
	private StudentDirectory studentDirectory;
	/** gets the faculty directory */
	private FacultyDirectory facultyDirectory;
	/** gets the registrar's information */
	private User registrar;
	/** gets the current user of the scheduler */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String PW = "Regi5tr@r";
	private static String hashPW;
	
	//Static code block for hashing the registrar user's password
	{
		try {
			  MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			  digest1.update(PW.getBytes());
			 hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Constructor for RegistrationManager
	 */
	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		registrar = new Registrar();
		currentUser = null;
	}
	
	/**
	 * Gets the instance of RegistrationManager
	 * @return the instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the course catalog
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the student directory
	 * @return the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Returns the faculty directory
	 * @return the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Processes the login of a given user
	 * @param id the id of the user to log in
	 * @param password the password of the user to log in
	 * @return if the user was logged in (true) or not (false)
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}
		
		if (registrar.getId().equals(id)) {
				MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			} else {
				throw new IllegalArgumentException("Invalid id or password");
			}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Invalid id or password");
			}
		}
		
		User user = studentDirectory.getStudentById(id);
		if (user == null) {
			user = facultyDirectory.getFacultyById(id);
			if (user == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
		}
		try {
		MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		digest.update(password.getBytes());
		String localHashPW = new String(digest.digest());
		if (user.getPassword().equals(localHashPW)) {
			currentUser = user;
				return true;
		}
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Invalid id or password");
		}				
		return false;
	}

	/**
	 * Logs out a user, setting the current user to null
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Returns the current user
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears out any data from the RegistrationManager
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}
	
	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}
	
	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Adds the given faculty member to the course
	 * @param course the given course
	 * @param faculty the given faculty member
	 * @return true if the faculty member was added to the course
	 * @throws IllegalArgumentException if attempting to add the course throws and exception
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			try {
				return faculty.getSchedule().addCourseToSchedule(course);	
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}	
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Removes the given faculty member from the course
	 * @param course the given course
	 * @param faculty the given faculty member
	 * @return true if the faculty is removed from the course
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			try {
				return faculty.getSchedule().removeCourseFromSchedule(course);	
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Resets the given faculty member's schedule
	 * @param faculty the given faculty member
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			try {
				faculty.getSchedule().resetSchedule();	
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This class is used to store values for the registrar
	 * @author Joey Schauer
	 */
	private static class Registrar extends User {
		
		/** the first name of the registrar */
		private static final String FIRST_NAME = "Wolf";
		/** the last name of the registrar */
		private static final String LAST_NAME = "Scheduler";
		/** the id of the registrar */
		private static final String ID = "registrar";
		/** the email of the registrar */
		private static final String EMAIL = "registrar@ncsu.edu";
		
		/**
		 * Create a registrar user with the user id of registrar and
		 * password of Regi5tr@r.  Note that hard coding passwords in a 
		 * project is HORRIBLY INSECURE, but it simplifies testing here.
		 * This should NEVER be done in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
}