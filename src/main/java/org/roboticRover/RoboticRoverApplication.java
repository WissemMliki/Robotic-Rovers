package org.roboticRover;

import org.roboticRover.plateau.Plateau;
import org.roboticRover.rover.Rover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Entry point for the Mars Robotic Rover simulation.
 * Reads input from a file (e.g. input.txt) specifying plateau size and rover instructions.
 * Creates and controls rovers based on given positions and movement commands.
 * Supports optional logging with the '-l' command-line flag.
 */

public class RoboticRoverApplication {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java -jar rover.jar input.txt [-l] \n -l: enable logs");
            return;
        }
        boolean enableLogs = false;
        // Check if the -l option is introduced in the command
        if (args.length > 1 && args[1].equals("-l")) {
            enableLogs = true;
        }
        String inputFile = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String plateauSize = br.readLine();
            String[] upperRightCoordinates = plateauSize.split(" ");
            int upperRightX = Integer.parseInt(upperRightCoordinates[0]);
            int upperRightY = Integer.parseInt(upperRightCoordinates[1]);
            Plateau plateau = new Plateau(upperRightX, upperRightY);
            String line;
            while ((line = br.readLine()) != null) {
                createAndControlRoboticRover(br, line, plateau, enableLogs);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the input file: " + e.getMessage());
        }
    }

    public static void createAndControlRoboticRover(BufferedReader br, String line, Plateau plateau, boolean enableLogs) throws IOException {
        String position = line.trim();
        String[] positionInstructions = position.split(" ");
        int posX = Integer.parseInt(positionInstructions[0]);
        int posY = Integer.parseInt(positionInstructions[1]);
        char roverDirection = positionInstructions[2].charAt(0);
        Rover rover = new Rover(posX, posY, roverDirection);
        String directionInstructions = br.readLine().trim();
        rover.controlRoboticRover(directionInstructions, plateau, enableLogs);
    }
}