package io.github.vanshikaa.fitnessplanner.gymnasium.exceptions;

/**
 * Exception thrown when there is an issue with User/WorkoutPlan validation.
 * For example, when the required fields are missing or have invalid values.
 */
public class UserValidationException extends RuntimeException {
    /**
     * Creates a new UserValidationException with the specified message.
     * @param message The error message explaining the validation issue.
     */
    public UserValidationException(String message) {
        super(message);
    }
}
