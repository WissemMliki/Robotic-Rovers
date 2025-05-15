package org.roboticRover.rover;

import org.roboticRover.exception.InvalidOrderException;

public enum Command {
    LEFT('L') {
        @Override
        public void execute(Rover rover) {
            rover.goToTheLeft();
        }
    },
    RIGHT('R') {
        @Override
        public void execute(Rover rover) {
            rover.goToTheRight();
        }
    },
    MOVE('M') {
        @Override
        public void execute(Rover rover) {
            rover.moveForward();
        }
    };

    private final char instruction;

    Command(char instruction) {
        this.instruction = instruction;
    }

    public abstract void execute(Rover rover);
    
    public static Command fromChar(char instruction) {
        for (Command cmd : values()) {
            if (cmd.instruction == instruction) {
                return cmd;
            }
        }
        throw new InvalidOrderException(instruction);
    }
}
