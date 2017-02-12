package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The exception class for Conflict
 * @author Joey Schauer
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ConflictException constructor that takes a message
	 * @param message the exception message
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Default ConflictException constructor
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
