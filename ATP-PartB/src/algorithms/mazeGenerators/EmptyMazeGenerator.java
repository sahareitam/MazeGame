package algorithms.mazeGenerators;

/**
 * A maze generator that generates an empty maze with all cells set to 0.
 * This class is a concrete implementation of the AMazeGenerator abstract class.
 */
public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * Generates an empty maze with the specified number of rows and columns.
     * All cells in the maze are set to 0 (empty).
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return The generated empty maze.
     */
    @Override
    public Maze generate(int rows, int columns) {
        // Create a new maze instance with the specified dimensions
        Maze maze = new Maze(rows, columns);

        // Initialize the grid of the maze with all cells set to 0
        int[][] grid = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = 0;
            }
        }

        // Set the grid of the maze to the initialized grid
        maze.setGrid(grid);

        return maze;
    }
}
