package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * This class stores values for a course including name, section, credits,
 * and instructorId. Has constants for boundary values.
 * 
 * @author Joey Schauer
 *
 */
public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Validator for Course's name */
	private CourseNameValidator validator;
	/** Length of the section String */
	public static final int SECTION_LENGTH = 3;
	/** Maximum length of the name String */
	public static final int MAX_NAME_LENGTH = 6;
	/** Minimum length of the name String */
	public static final int MIN_NAME_LENGTH = 4;
	/** Maximum number of credits for a course */
	public static final int MAX_CREDITS = 5;
	/** Minimum number of credits for a course */
	public static final int MIN_CREDITS = 1;
	/**
	 * Constructs a Course object with values for all fields
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}
	
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for
	 * courses that are arranged
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or
	 * greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
	        throw new IllegalArgumentException("Invalid name");
	    }
	    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
	        throw new IllegalArgumentException("Invalid name");
	    }
		validator = new CourseNameValidator();
		try {
			validator.isValid(name);
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e.getMessage());
		}		
		this.name = name;
	}
	
	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course's section. If the section number is not exactly three digits
	 * and IllegalArgumentException is thrown
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section number is not exactly three digits
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException("Invalid section");
		}
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section");
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section");
			}
		}
		this.section = section;
	}
	
	/**
	 * Returns the Course's credit hours
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credit hours. If the credit hours are less than 1 or greater than 5
	 * an IllegalArgumentException is thrown
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credit hours are less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}
	
	/**
	 * Returns the Course's instructor
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the Course's instructor. If the instructor's id is null or an empty string
	 * an IllegalArguemntException is thrown
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor's id is null or an empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null) {
			throw new IllegalArgumentException("Invalid instructor id");
		}
		if (instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Sets the Course's meeting days. Throws an IllegalArgumentException if meetingDays is null, 
	 * is an empty string, consists of any characters other than 'M', 'T', 'W', 'H', 'F' or 'A', 
	 * or if 'A' is in the meeting days list, it must be the only character
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if meetingDays is null, is an empty string,
	 * consists of any characters other than 'M', 'T', 'W', 'H', 'F' or 'A', or if
	 * 'A' is in the meeting days list, it must be the only character
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' 
				&& meetingDays.charAt(i) != 'W' && meetingDays.charAt(i) != 'H'
				&& meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException("Invalid meeting days");
			}
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() != 1) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}
	
	/** 
	 * Generates a hashCode for Course using all fields
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if (getMeetingDays().equals("A")) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," 
	    	   + getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Uses the abstract method from Activity to return a String array with the
	 * Course's name, section, title and meeting String
	 * @return Course's name, section, title and meeting String in an array
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] s = new String[4];
		s[0] = name;
		s[1] = section;
		s[2] = getTitle();
		s[3] = getMeetingString();
		return s;
	}

	/**
	 * Uses the abstract method from Activity to return a String array with the
	 * Course's name, section, title, credits, instructorId, meeting String and an empty String
	 * @return Course's name, section, title, credits, instructorId, meeting String and an empty String
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] s = new String[7];
		s[0] = name;
		s[1] = section;
		s[2] = getTitle();
		s[3] = "" + credits;
		s[4] = instructorId;
		s[5] = getMeetingString();
		s[6] = "";
		return s;
	}

	/**
	 * Determines if an Activity is a duplicate of the Course
	 * @param activity the Activity to be compared
	 * @return if the Activity is a duplicate (true) or not (false)
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course c = (Course) activity;
			return c.getTitle().equals(getTitle());
		} else {
			return false;
		}		
	}

	/**
	 * Compares two Course objects determining if it is higher or lower than this Course
	 * @return -1 if lower, 0 if equal or 1 if higher
	 */
	@Override
	public int compareTo(Course course) {
		if (name.compareTo(course.getName()) != 0) {
			if (name.compareTo(course.getName()) < 0) {
				return -1;
			}
			if (name.compareTo(course.getName()) > 0) {
				return 1;
			}
		}
		if (section.compareTo(course.getSection()) != 0) {
			if (section.compareTo(course.getSection()) < 0) {
				return -1;
			}
			if (section.compareTo(course.getSection()) > 0) {
				return 1;
			}
		}
		return 0;
	}
	
	
}
