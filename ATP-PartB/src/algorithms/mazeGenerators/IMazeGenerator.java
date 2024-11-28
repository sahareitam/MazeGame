package algorithms.mazeGenerators;

/**
 * An interface for maze generation algorithms.
 * Classes implementing this interface should provide methods to generate a maze and measure the time taken for the generation process.
 */
public interface IMazeGenerator {

    /**
     * Generates a maze with the specified number of rows and columns.
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return The generated maze.
     */
    Maze generate(int rows, int columns);

    /**
     * Measures the time taken by the generate method to generate a maze.
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return The time taken to generate the maze in milliseconds.
     */
    long measureAlgorithmTimeMillis(int rows, int columns);
}
