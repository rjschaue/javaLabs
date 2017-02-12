/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This interface is used to check for conflicts between activities in a schedule
 * @author Joey Schauer
 *
 */
public interface Conflict {
	/**
	 * Checks two activities to see if their days and times conflict
	 * @param possibleConflictingActivity is the possible conflicting activity to be checked
	 * @throws ConflictException is thrown when there is an exception to conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
