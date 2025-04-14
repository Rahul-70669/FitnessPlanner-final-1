package io.github.vanshikaa.fitnessplanner.gymnasium.exceptions;

/**
 * Exception thrown when a User/WorkoutPlan is not found in the database.
 * Used when searching for a User/WorkoutPlan by id or name.
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Creates a new UserNotFoundException with the specified message.
     * @param message The error message explaining why the User/WorkoutPlan was not found.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
