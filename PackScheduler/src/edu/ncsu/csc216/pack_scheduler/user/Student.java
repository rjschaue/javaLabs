package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The Student class will store student information
 * allowing users to set or get a student's firstName,
 * lastName, id, email, password and maxCredits
 * 
 * @author Joey Schauer
 *
 */

public class Student implements Comparable<Student> {
	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;
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
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
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
	 * Returns the Student's firstName
	 * @return the Student's firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the Student's firstName
	 * If the firstName is null or empty, an IllegalArgumentException is thrown
	 * @param firstName the given firstName to set
	 * @throws IllegalArgumentException if firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		if (firstName == null) {
			throw new IllegalArgumentException("Invalid first name");
		}
		if (firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns the Student's lastName
	 * @return the Student's lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the Student's lastName
	 * If the lastName is null or empty, an IllegalArgumentException is thrown
	 * @param lastName the given lastName to set
	 * @throws IllegalArgumentException if lastName is null or empty
	 */
	public void setLastName(String lastName) {
		if (lastName == null) {
			throw new IllegalArgumentException("Invalid last name");
		}
		if (lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the Student's id
	 * @return the Student's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the Student's id
	 * If id is null or empty, an IllegalArgumentException is thrown
	 * @param id is the given id to be set
	 * @throws IllegalArguementException if id is null or empty
	 */
	private void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Invalid id");
		}
		if (id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the Student's email
	 * @return the Student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the Student's email
	 * If email is null, empty, doesn't have an '@' or '.', or the last '.'
	 * comes before the first '@', an IllegalArgumentException is thrown
	 * @param email the given email to be set
	 * @throws IllegalArgumentException if email is null, empty, doesn't have an '@' or '.', or the last '.'
	 * comes before the first '@'
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.length() == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.indexOf('@') == -1) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.indexOf('.') == -1) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.indexOf('@') > email.lastIndexOf('.')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the Student's password
	 * @return the Student's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the Student's password
	 * If password is null or empty, an IllegalArgumentException is thrown
	 * @param password the given password to be set
	 * @throws IllegalArgumentException if password is null or empty
	 */
	public void setPassword(String password) {
		if (password == null) {
			throw new IllegalArgumentException("Invalid password");
		}
		if (password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
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
	 * Converts Student to hash code
	 * @return Student as hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Compares two Student objects
	 * @return if the objects are equal (true) or not (false)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		if (!lastName.equals(s.getLastName())) {
			compare = lastName.compareToIgnoreCase(s.getLastName());
			if (compare < 0) {
				return -1;
			} else if (compare > 0) {
				return 1;
			}
		}
		if (!firstName.equals(s.getFirstName())) {
			compare = firstName.compareToIgnoreCase(s.getFirstName());
			if (compare < 0) {
				return -1;
			} else if (compare > 0) {
				return 1;
			}
		}
		if (!id.equals(s.getId())) {
			compare = id.compareToIgnoreCase(s.getId());
			if (compare < 0) {
				return -1;
			} else if (compare > 0){
				return 1;
			}
		}
		return 0;
	}
}
