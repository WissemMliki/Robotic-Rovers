# Context
A squad of robotic rovers are to be landed by NASA on a plateau on Mars. This plateau, which is curiously rectangular, must be navigated by the rovers so that their on-board cameras can get a complete view of the surrounding terrain to send back to Earth.
A rover's position and location is represented by a combination of x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom
left corner and facing North.
In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot.'M' means move forward one grid point, and maintain the same heading. Assume that the square directly North from (x, y) is (x, y+1).
# INPUT
The first line of input is the upper-right coordinates of the plateau, the lower-left coordinates are assumed to be 0,0. The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau. The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co-ordinates and the rover's orientation. Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving.
# OUTPUT
The output for each rover should be its final co-ordinates and heading.
# Project file tree
![image](https://github.com/user-attachments/assets/6a13940d-4998-4892-995b-7008d6b3e02e)
# Class roles
**Class Rover** : 
 * Simulates a robotic rover navigating a plateau on Mars.
 * The rover executes a sequence of commands ('L', 'R', 'M') to turn or move forward.
 * It tracks position (x, y) and direction (N, E, S, W), while checking plateau bounds.
 * Implements the Controlable interface for basic movement operations.
 * Throws exceptions for invalid moves, directions, or commands.
   
**Enum Direction** : 
 * Enum representing the four cardinal directions (N, E, S, W) used by the rover.
 * Provides mapping from character input to direction and visual symbols for display.
 * Throws InvalidDirectionException for invalid inputs.
   
**Interface Controlable** : 
 * Interface defining basic rover movements: turn left, turn right, and move forward.
 * Includes a default apply() method to execute a movement based on a single character command ('L', 'R', 'M').
 * Throws InvalidOrderException for unsupported commands.
   
**Class RoboticRoverApplication** :
 * Entry point for the Mars Robotic Rover simulation.
 * Reads input from a file (e.g. input.txt) specifying plateau size and rover instructions.
 * Creates and controls rovers based on given positions and movement commands.
 * Supports optional logging with the '-l' command-line flag.
   
**Class Plateau** :
 * Represents the plateau on which rovers operate.
 * Stores dimensions and provides utilities to:
 * - Validate rover positions within bounds.
 * - Log the plateau state with the rover's current position and direction.
 
**Class ExceedsPlateauSizeException** :
 * Exception thrown when a rover moves outside the bounds of the plateau.
 * Includes the invalid (x, y) coordinates in the error message.
 
**Class InvalidDirectionException** 
 * Exception thrown when a rover receives an invalid direction.
 * Supports both generic and specific direction error messages.

**Class InvalidOrderException** :
 * Exception thrown when a rover receives an invalid movement command (not 'L', 'R', or 'M').
 
**Class RoverTest** :
 * Unit tests for the Robotic Rover application.
 * Verifies correct rover behavior, exception handling, and edge cases using sample input files.

# Build And Test The roject
 To Build the project and generate the jar "rover.jar", you need to execute this command :
    ```bash
    mvn clean package
    ```

To launch the tests, you need to execute this command :
    ```bash
    mvn test
    ```

To run the program, you need to execute this command :
    ```bash
   java -jar rover.jar input.txt
    ```

To run the program And enabling the logs at the same time, you need to execute this command :
    ```bash
   java -jar rover.jar input.txt -l
    ```
