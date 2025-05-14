package org.roboticRover.plateau;

import org.roboticRover.rover.Rover;

/**
 * Represents the plateau on which rovers operate.
 * Stores dimensions and provides utilities to:
 * - Validate rover positions within bounds.
 * - Log the plateau state with the rover's current position and direction.
 */

public class Plateau {

    private int height, width;

    public Plateau(int upperRightX, int upperRightY) {
        this.width = upperRightX + 1;
        this.height = upperRightY + 1;
    }

    public String logPlateauWithRover(Rover rover) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (rover.getPosX() == j && this.height-rover.getPosY()-1 == i) {
                    str.append(rover.getcurrentDirection().getDirectionSymbol());
                } else {
                    str.append('.');
                }
                str.append(' ');
            }
            str.append('\n');
        }
        return str.toString();
    }

    public boolean checkIfInvalidRoverPosition(int x, int y) {
        y = this.height - y - 1;
        return x < 0 || x >= this.width || y < 0 || y >= this.height;
    }
}
