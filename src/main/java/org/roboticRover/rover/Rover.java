package org.roboticRover.rover;

import org.roboticRover.exception.ExceedsPlateauSizeException;
import org.roboticRover.exception.InvalidDirectionException;
import org.roboticRover.plateau.Plateau;

/**
 * Simulates a robotic rover navigating a plateau on Mars.
 * The rover executes a sequence of commands ('L', 'R', 'M') to turn or move forward.
 * It tracks position (x, y) and direction (N, E, S, W), while checking plateau bounds.
 * Implements the Controlable interface for basic movement operations.
 * Throws exceptions for invalid moves, directions, or commands.
 */

public class Rover implements Controlable {

    private int posX, posY;
    private Direction currentDirection;

    public Rover(int posX, int posY, char roverDirection) {
        this.posX = posX;
        this.posY = posY;
        this.currentDirection = Direction.fromRoverDirection(roverDirection);
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public Direction getcurrentDirection() {
        return this.currentDirection;
    }

    @Override
    public void goToTheLeft() {
        this.currentDirection = switch (currentDirection) {
            case EAST -> Direction.NORTH;
            case WEST -> Direction.SOUTH;
            case NORTH -> Direction.WEST;
            case SOUTH -> Direction.EAST;
            default -> throw new InvalidDirectionException();
        };
    }

    @Override
    public void goToTheRight() {
        this.currentDirection = switch (currentDirection) {
            case EAST -> Direction.SOUTH;
            case WEST -> Direction.NORTH;
            case NORTH -> Direction.EAST;
            case SOUTH -> Direction.WEST;
            default -> throw new InvalidDirectionException();
        };
    }

    @Override
    public void moveForward() {
        switch (currentDirection) {
            case EAST -> this.posX++;
            case WEST -> this.posX--;
            case NORTH -> this.posY++;
            case SOUTH -> this.posY--;
            default -> throw new InvalidDirectionException();
        }
    }

    public void controlRoboticRover(String directionInstructions, Plateau plateau, boolean enableLogs) {
        logInitialRoverPosition(plateau, enableLogs);
        for (int i = 0; i < directionInstructions.length(); i++) {
            char instruction = directionInstructions.charAt(i);
            execute(instruction);
            checkIfRoverPositionIsValid(plateau);
            logDirectionOrder(directionInstructions.charAt(i), plateau, enableLogs);
        }
        DisplayRoverLastPosition();
    }

    public void execute(char instruction) {
        Command cmd = Command.fromChar(instruction);
        cmd.execute(this);  // 'this' refers to the current Rover instance
    }

    private void logInitialRoverPosition(Plateau plateau, boolean enableLogs) {
        if (enableLogs) {
            System.out.println("\n\nInitial position of the rover: " + this.posX + " " + this.posY + " " + this.currentDirection + "\n" + plateau.logPlateauWithRover(this) + "---------");
        }
    }

    private void checkIfRoverPositionIsValid(Plateau plateau) {
        if (plateau.checkIfInvalidRoverPosition(this.posX, this.posY)) {
            throw new ExceedsPlateauSizeException(this.posX, this.posY);
        }
    }

    private void logDirectionOrder(char directionInstruction, Plateau plateau, boolean enableLogs) {
        if (enableLogs) {
            System.out.println("Process direction order: " + directionInstruction + "\n" + plateau.logPlateauWithRover(this) + "\n" + "---------");
        }
    }

    private void DisplayRoverLastPosition() {
        System.out.println(this.posX + " " + this.posY + " " + this.currentDirection.getRoverDirection());
    }

}
