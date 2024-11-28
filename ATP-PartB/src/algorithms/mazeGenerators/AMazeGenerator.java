package algorithms.mazeGenerators;

/**
 * An abstract class representing a maze generator.
 * This class provides a common structure for maze generation algorithms.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * Generates a maze with the specified number of rows and columns.
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return The generated maze.
     */
    public abstract Maze generate(int rows, int columns);

    /**
     * Measures the time taken by the generate method to generate a maze.
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return The time taken to generate the maze in milliseconds.
     */
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
