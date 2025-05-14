package org.roboticRover.exception;

/**
 * Exception thrown when a rover moves outside the bounds of the plateau.
 * Includes the invalid (x, y) coordinates in the error message.
 */
public class ExceedsPlateauSizeException extends IndexOutOfBoundsException {
    public ExceedsPlateauSizeException(int posX, int posY) {
        super("ERROR: Exceeds plateau size at the position with coordinates (" + posX + "," + posY + ")");
    }
}
