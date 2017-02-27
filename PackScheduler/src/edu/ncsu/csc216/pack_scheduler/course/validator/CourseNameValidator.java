package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Validator for course names
 * @author Joey Schauer
 *
 */
public class CourseNameValidator {
	/** Constant value for the initial state */
	private final State stateInitial = new InitialState();
	/** Constant value for the letter state */
	private final State stateLetter = new LetterState();
	/** Constant value for the number state */
	private final State stateNumber = new NumberState();
	/** Constant value for the suffix state */
	private final State stateSuffix = new SuffixState();
	/** gets the current state of the validation */
	private State currentState;
	/** gets whether the end state is valid or not */
	private boolean validEndState;
	/** gets the count for the number of letters */
	private int letterCount;
	/** gets the count for the number of digits */
	private int digitCount;
	
	/**
	 * This method takes a given course name and determines if it is valid or not
	 * @param courseName the course name to be validated
	 * @return whether the course name is valid (true) or not (false)
	 * @throws InvalidTransitionException when there is an invalid transition through the states 
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		//Initializes values for processing the course name
		currentState = stateInitial;
		int charIndex = 0;
		char c;
		
		//Process each character of the course name
		while(charIndex < courseName.length()) {
			c = courseName.charAt(charIndex);
			
			if(!Character.isLetter(c) && !Character.isDigit(c)) {
				currentState.onOther();
			}
	
			if(Character.isLetter(c)) {
				currentState.onLetter();
			} else if (Character.isDigit(c)) {
				currentState.onDigit();
			}			
			charIndex++;
		}
		
		//Determines whether the end state is valid or not
		if(currentState == stateSuffix || currentState == stateNumber && digitCount == 3) {
			validEndState = true;
		}
		return validEndState;
	}
	
	/**
	 * The abstract class representing the state of the validation
	 * @author Joey Schauer
	 */
	public abstract class State {
		/** abstract method used when the processed character is a letter */
		public abstract void onLetter() throws InvalidTransitionException;
		/** abstract method used when the processed character is a digit */
		public abstract void onDigit() throws InvalidTransitionException;
		/**
		 * Used when the processed character is not a letter or a digit
		 * @throws InvalidTransitionException since characters must be letters or digits
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Represents the validator being in the letter state
	 * @author Joey Schauer
	 */
	public class LetterState extends State {
		/** constant value for the max number of prefix letters */
		public static final int MAX_PREFIX_LETTERS = 4;
		
		/**
		 * Default constructor for LetterState
		 */
		private LetterState() {
			//Default constructor
		}

		/**
		 * Used when the processed character is a letter
		 * @throws InvalidTransitionException when the number of letters exceeds 4
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				currentState = stateLetter;
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * Used when the processed character is a digit
		 */
		@Override
		public void onDigit() {
			currentState = stateNumber;
			digitCount++;
		}		
	}
	
	/**
	 * Represents the validator being in the number state
	 * @author Joey Schauer
	 */
	public class NumberState extends State {
		/** constant for the length of the course number */
		public static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * Default constructor for NumberState
		 */
		private NumberState() {
			//default constructor
		}
		
		/**
		 * Used when the processed character is a letter
		 * @throws InvalidTransitionException if the digit count is less than the constant value
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			} else if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = stateSuffix;
			}
		}

		/**
		 * Used when the processed character is a digit
		 * @throws InvalidTransitionException if the number of digits exceeds the constant value
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				currentState = stateNumber;
				digitCount++;
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}		
	}
	
	/**
	 * Represents the validator being in the intitial state
	 * @author Joey Schauer
	 */
	public class InitialState extends State {
		
		/**
		 * Default constructor for InitialState
		 */
		private InitialState() {
			//default constructor
		}

		/**
		 * Used when the processed character is a letter
		 */
		@Override
		public void onLetter() {
			currentState = stateLetter;
			letterCount++;
		}

		/**
		 * Used when the processed character is a digit
		 * @throws InvalidTransitionException since the first value must be a letter
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}		
	}
	
	/**
	 * Represents the validator being in the suffix state
	 * @author Joey Schauer
	 */
	public class SuffixState extends State {
		
		/**
		 * Default constructor for SuffixState
		 */
		private SuffixState() {
			//default constructor
		}

		/**
		 * Used when the processed character is a letter
		 * @throws InvalidTransitionException since there can only be one letter in the suffix
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");			
		}

		/**
		 * Used when the processed character is a digit
		 * @throws InvalidTransitionException since there are no digits after the suffix
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");			
		}	
	}
}
