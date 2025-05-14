import org.junit.Test;
import org.roboticRover.RoboticRoverApplication;
import org.roboticRover.exception.ExceedsPlateauSizeException;
import org.roboticRover.exception.InvalidDirectionException;
import org.roboticRover.exception.InvalidOrderException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the Robotic Rover application.
 * Verifies correct rover behavior, exception handling, and edge cases using sample input files.
 */
public class RoverTest {

    @Test
    public void testSuccessfulInputFile() throws Exception {
        String testFilePath = "src/test/resources/testFiles/testSuccessfulScenario.txt";
        String expectedOutput = "1 3 N\n5 1 E\n";
        executeTest(testFilePath, expectedOutput);
    }

    @Test
    public void testWithExceedsPlateauSizeException() throws Exception {
        String testFilePath = "src/test/resources/testFiles/testExceedsPlateauSize.txt";
        assertThrows(ExceedsPlateauSizeException.class, () -> {
            executeTest(testFilePath, "");
        });
    }

    @Test
    public void testWithInvalidOrderException() throws Exception {
        String testFilePath = "src/test/resources/testFiles/testInvalidOrder.txt";
        assertThrows(InvalidOrderException.class, () -> {
            executeTest(testFilePath, "");
        });
    }

    @Test
    public void testWithInvalidDirectionException() throws Exception {
        String testFilePath = "src/test/resources/testFiles/testInvalidDirection.txt";
        assertThrows(InvalidDirectionException.class, () -> {
            executeTest(testFilePath, "");
        });
    }

    @Test
    public void testWithVoluminousInputFileWithManyMoves() throws Exception {
        String testFilePath = "src/test/resources/testFiles/testVoluminousFileWithManyMoves.txt";
        String expectedOutput = "50 50 N\r\n";
        createVoluminousFileWithManyMoves();
        executeTest(testFilePath, expectedOutput);
        Path path = Paths.get(testFilePath);
        Files.delete(path);
    }

    @Test
    public void testNotMoveBeyondPlateauBoundaries() throws Exception {
        String testFilePath = "src/test/resources/testFiles/testNotMoveBeyondPlateauBoundaries.txt";
        String expectedOutput = "5 5 N\n5 5 E\n";
        executeTest(testFilePath, expectedOutput);
    }

    @Test
    public void testHardMoves() throws Exception {
        String testFilePath = "src/test/resources/testFiles/testHardMoves.txt";
        String expectedOutput = "4 0 E\n";
        executeTest(testFilePath, expectedOutput);
    }

    private void executeTest(String testFilePath, String expectedOutput) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
        String[] args = {testFilePath};
        RoboticRoverApplication.main(args);
        System.setOut(originalSystemOut);
        String generatedOutput = outputStream.toString();
        // Normalize both strings to use Unix line endings before comparison
        String normalizedExpected = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");
        String normalizedGenerated = generatedOutput.replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(normalizedExpected, normalizedGenerated);
    }

    private void createVoluminousFileWithManyMoves() {
        String fileName = "src/test/resources/testFiles/testVoluminousFileWithManyMoves.txt";
        int posX = 50;
        int posY = 50;
        int height = 100;
        int width = 100;
        char direction = 'N';
        StringBuilder directionInstructions = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            directionInstructions.append("LMRMRMRMLL");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(width + " " + height + "\n" + posX + " " + posY + " " + direction + "\n");
            writer.write(directionInstructions.toString() + "\n");
            System.out.println("A voluminous file with many moves has been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
