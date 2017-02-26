package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * The exception class for invalid transitions
 * @author Joey Schauer
 *
 */
public class InvalidTransitionException extends Exception {

	/** Id used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * InvalidTransitionException constructor that takes a message
	 * @param message the exception message
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Default InvalidTransitionException constructor
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
