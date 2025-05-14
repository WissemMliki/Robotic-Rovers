package org.roboticRover.rover;

import org.roboticRover.exception.InvalidOrderException;

/**
 * Interface defining basic rover movements: turn left, turn right, and move forward.
 * Includes a default apply() method to execute a movement based on a single character command ('L', 'R', 'M').
 * Throws InvalidOrderException for unsupported commands.
 */

public interface Controlable {
    public void goToTheLeft();
    public void goToTheRight();
    public void moveForward();

    // Default method to apply actions
    default void apply(char action) {
        switch (action) {
            case 'L' -> goToTheLeft();
            case 'R' -> goToTheRight();
            case 'M' -> moveForward();
            default -> throw new InvalidOrderException(action);
        }
    }
}
