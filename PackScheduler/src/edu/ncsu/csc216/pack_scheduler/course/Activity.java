package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This class stores values for an Activity's title, meetingDays, startTime and endTime
 * Allows for setting and getting of these values
 * 
 * @author Joey Schauer
 *
 */

abstract public class Activity implements Conflict {

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** The upper military time limit */
	public static final int UPPER_TIME = 2400;
	/** The upper value for an hour */
	public static final int UPPER_HOUR = 60;

	/**
	 * Constructs an Activity object using the following parameters
	 * @param title the title of the Activity
	 * @param meetingDays the meetingDays for the Activity
	 * @param startTime the startTime of the Activity
	 * @param endTime the end Time of the Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);		
	}

	/**
	 * Returns the Activity's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title. If the title is null or an empty string
	 * an IllegalArgumentException is thrown.
	 * @param title the title to set
	 * @throws IllegalArgumentException if name is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Invalid title");
		}
		if (title.length() == 0) {
			throw new IllegalArgumentException("Invalid title");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Activity's meeting days
	 * @param meetingDays the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the Activity's starting time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's ending time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's start and end time. Throws an IllegalArgumentException if 
	 * startTime/endTime is less than 0, startTime/endTime is greater than 2359, startTime/endTime 
	 * modulo 100 is greater than 59, endTime is less than startTime or if startTime/endTime 
	 * doesn't equal 0 when meetingDays is "A"
	 * @param startTime the Activity's start time
	 * @param endTime the Activity's end time
	 * @throws IllegalArgumentException if startTime/endTime is less than 0, startTime/endTime
	 * is greater than 2359, startTime/endTime modulo 100 is greater than 59, endTime is less than startTime
	 * or if startTime/endTime doesn't equal 0 when meetingDays is "A"
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (meetingDays.equals("A")) {
		} else if (startTime < 0 || startTime >= UPPER_TIME || startTime % 100 >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting times");
		} else if (endTime < 0 || endTime >= UPPER_TIME || endTime % 100 >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting times");
		} else if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the meeting days and meeting time in standard time format instead of military
	 * @return Arranged for meetingDays of "A" or meeting information as a string in standard time format
	 */
	public String getMeetingString() {
		if (meetingDays.equals("A")) {
			return "Arranged";
		}
		
		String meetingString = "";
		meetingString += meetingDays + " ";
		
		if (startTime < 100) {
			meetingString += "12:";
		} else if (startTime >= UPPER_HOUR && startTime < 1300) {
			meetingString += (startTime / 100) + ":";
		} else {
			meetingString += ((startTime / 100) - 12) + ":";			
		}
		if ((startTime % 100) < 10) {
			meetingString += "0" + (startTime % 100);
		} else {
			meetingString += (startTime % 100);
		}
		if (startTime < 1200) {
			meetingString += "AM-";
		} else {
			meetingString += "PM-";
		}
		
		if (endTime < 100) {
			meetingString += "12:";
		} else if (endTime >= UPPER_HOUR && endTime < 1300) {
			meetingString += (endTime / 100) + ":";
		} else {
			meetingString += ((endTime / 100) - 12) + ":";
		}
		if ((endTime % 100) < 10) {
			meetingString += "0" + (endTime % 100);
		} else {
			meetingString += (endTime % 100);
		}
		if (endTime < 1200) {
			meetingString += "AM";
		} else {
			meetingString += "PM";
		}
		
		return meetingString;
	}
	
	/**
	 * The abstract method for getting a short version of the class's fields
	 * @return a String array of class fields
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * The abstract method for getting a long version of the class's fields
	 * @return a String array of class fields
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * The abstract method for checking if an Activity is a duplicate
	 * @param activity is the Activity to be checked
	 * @return if the Activity is a duplicate (true) or not (false)
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/** 
	 * Generates a hashCode for Activity using all fields
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks to see if a given activity conflicts with this activity by looking at the
	 * meetingDays, startTime and endTime for both activities
	 * @throws ConflictException if there is a conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		int conflictingStartTime = possibleConflictingActivity.getStartTime();
		int conflictingEndTime = possibleConflictingActivity.getEndTime();
		String conflictingMeetingDays = possibleConflictingActivity.getMeetingDays();
		for (int i = 0; i < getMeetingDays().length(); i++) {
			for (int j = 0; j < conflictingMeetingDays.length(); j++) {
				if (getMeetingDays().charAt(i) == conflictingMeetingDays.charAt(j) && getMeetingDays().charAt(i) != 'A') {
					if (getStartTime() >= conflictingStartTime && getStartTime() <= conflictingEndTime) {
						throw new ConflictException();
					}
					if (conflictingStartTime >= getStartTime() && conflictingStartTime <= getEndTime()) {
						throw new ConflictException();
					}
					if (conflictingEndTime >= getStartTime() && conflictingEndTime <= getEndTime()) {
						throw new ConflictException();
					}
					if (getEndTime() >= conflictingStartTime && getEndTime() <= conflictingEndTime) {
						throw new ConflictException();
					}
					
				}
			}
						
		}
	}	
}