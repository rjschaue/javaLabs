package edu.ncsu.csc216.pack_scheduler.user;

public abstract class User {

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

	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
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
	protected void setId(String id) {
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
	 * Generates hash code for User
	 * @return User as hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Determines if two Users are equal
	 * @return if two Users are equal (true) or not (false)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}