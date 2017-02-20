package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

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
		
		Student s = studentDirectory.getStudentById(id);
		if (s == null) {
			throw new IllegalArgumentException("Invalid id or password");
		}
		try {
		MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		digest.update(password.getBytes());
		String localHashPW = new String(digest.digest());
		if (s.getPassword().equals(localHashPW)) {
			currentUser = s;
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
	}
		
	/**
	 * Setter for studentDirectory to help with testing
	 * @param studentDirectory the studentDirectory to be added
	 */
	public void setStudentDirectory(StudentDirectory studentDirectory) {
		this.studentDirectory = studentDirectory;
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