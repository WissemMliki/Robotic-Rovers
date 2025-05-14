package org.roboticRover.exception;

/**
 * Exception thrown when a rover receives an invalid movement command (not 'L', 'R', or 'M').
 */
public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(char order) {
        super("ERROR: Invalid order" + order + ".");
    }
}
