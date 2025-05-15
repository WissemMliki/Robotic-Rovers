package org.roboticRover.rover;

import org.roboticRover.exception.InvalidOrderException;

/**
 * Interface defining basic rover movements: turn left, turn right, and move forward.
 */

public interface Controlable {
    public void goToTheLeft();
    public void goToTheRight();
    public void moveForward();
}
