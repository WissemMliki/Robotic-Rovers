package org.roboticRover.exception;

/**
 * Exception thrown when a rover receives an invalid direction.
 * Supports both generic and specific direction error messages.
 */
public class InvalidDirectionException extends RuntimeException{
    public InvalidDirectionException(char direction){
        super("ERROR: Invalid Direction " + direction + ".");
    }

    public InvalidDirectionException(){
        super("ERROR: Invalid Direction.");
    }
}
