package org.roboticRover.rover;

import org.roboticRover.exception.InvalidDirectionException;

/**
 * Enum representing the four cardinal directions (N, E, S, W) used by the rover.
 * Provides mapping from character input to direction and visual symbols for display.
 * Throws InvalidDirectionException for invalid inputs.
 */

public enum Direction {
    EAST('E'),
    WEST('W'),
    NORTH('N'),
    SOUTH('S');

    private final char roverDirection;

    Direction(char roverDirection) {
        this.roverDirection = roverDirection;
    }

    public char getRoverDirection() {
        return roverDirection;
    }

    public static Direction fromRoverDirection(char roverDirection) {
        return switch (roverDirection){
            case 'E' -> EAST;
            case 'W' -> WEST;
            case 'N' -> NORTH;
            case 'S' -> SOUTH;
            default -> throw new InvalidDirectionException(roverDirection);
        };
    }

    public String getDirectionSymbol() {
        return switch (this) {
            case EAST  -> ">";
            case WEST  -> "<";
            case NORTH -> "^";
            case SOUTH -> "v";
            default -> throw new InvalidDirectionException();
        };
    }
}
