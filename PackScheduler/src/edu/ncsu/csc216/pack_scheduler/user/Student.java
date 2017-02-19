package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The Student class will store student information
 * allowing users to set or get a student's firstName,
 * lastName, id, email, password and maxCredits
 * 
 * @author Joey Schauer
 *
 */

public class Student extends User implements Comparable<Student> {
	/** Student's max credits */
	private int maxCredits;
	/** The default max credits value */
	public static final int MAX_CREDITS = 18;

	/**
	 * This constructor for Student allows the user to set a student's
	 * firstName, lastName, id, email, password and maxCredits
	 * 
	 * @param firstName a Student's first name
	 * @param lastName a Student's last name
	 * @param id a Student's id
	 * @param email a Student's email
	 * @param password a Student's hashed password
	 * @param maxCredits a Student's max credits
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
	}
	
	/**
	 * This constructor for Student allows the user to set a student's
	 * firstName, lastName, id, email, password, setting maxCredits to 18
	 * 
	 * @param firstName a Student's first name
	 * @param lastName a Student's last name
	 * @param id a Student's id
	 * @param email a Student's email
	 * @param password a Student's hashed password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * Returns the Student's maxCredits
	 * @return the Student's maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the Student's maxCredits
	 * if maxCredits is less than 3 or greater than 18, an IllegalArgumentException is thrown
	 * @param maxCredits the given maxCredits to be set
	 * @throws IllegalArgumentException if maxCredits is less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Generates hash code for Student
	 * @return Student as hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares if two Students are equal
	 * @return if two Students are equal (true) or not (false)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Converts Student fields to a string
	 * @return Student fields as a string with commas separating the fields
	 */
	@Override
	public String toString() {
		String student = getFirstName() + "," + getLastName() + ","
						 + getId() + "," + getEmail() + "," + getPassword() + "," + getMaxCredits();
		return student;
	}

	/**
	 * Compares two student objects
	 * @throws NullPointerException if the Student is null
	 */
	@Override
	public int compareTo(Student s) {
		if (s == null) {
			throw new NullPointerException();
		}
		int compare;
		if (!getLastName().equals(s.getLastName())) {
			compare = getLastName().compareToIgnoreCase(s.getLastName());
			if (compare < 0) {
				return -1;
			} else if (compare > 0) {
				return 1;
			}
		}
		if (!getFirstName().equals(s.getFirstName())) {
			compare = getFirstName().compareToIgnoreCase(s.getFirstName());
			if (compare < 0) {
				return -1;
			} else if (compare > 0) {
				return 1;
			}
		}
		if (!getId().equals(s.getId())) {
			compare = getId().compareToIgnoreCase(s.getId());
			if (compare < 0) {
				return -1;
			} else if (compare > 0){
				return 1;
			}
		}
		return 0;
	}
}
