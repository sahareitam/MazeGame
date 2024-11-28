package algorithms.mazeGenerators;

import java.util.Random;

/**
 * Generates a simple maze with random 1/0 values.
 * The maze will have a start position in the first row and a random column.
 * The maze will have a goal position in a random row and the last column.
 * The maze will have a path from the start to the goal.
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * Generates a maze with random 1/0 values.
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return The generated maze.
     * @throws IllegalArgumentException if the provided dimensions are less than 1.
     */
    @Override
    public Maze generate(int rows, int columns) {
        Random rand = new Random();
        if (rows < 1 || columns < 1)
            throw new IllegalArgumentException("Maze dimensions are too small");
        // Create a new maze with the given dimensions
        Maze maze_simple = new Maze(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Set the value of the cell to random 1/0
                int next = rand.nextInt(2);
                maze_simple.setCell(i, j, next);
            }
        }
        int Si = maze_simple.getStartPosition().getRowIndex();
        int Sj = maze_simple.getStartPosition().getColumnIndex();
        int Gi = maze_simple.getGoalPosition().getRowIndex();
        int Gj = maze_simple.getGoalPosition().getColumnIndex();
        // Move the start position to the goal position

        // Moving horizontally until reaching the goal Row index
        // Set the value of every cell to 0
        while (Si != Gi) {
            maze_simple.setCell(Si, Sj, 0);
            Si = (Si < Gi) ? Si + 1 : Si - 1;
        }
        // and then moving vertically until reaching the goal Column index
        // Set the value of every cell to 0
        while (Sj != Gj) {
            maze_simple.setCell(Si, Sj, 0);
            Sj = (Sj < Gj) ? Sj + 1 : Sj - 1;
        }
        maze_simple.setCell(maze_simple.getGoalPosition().getRowIndex(), maze_simple.getGoalPosition().getColumnIndex(), 0);

        return maze_simple;
    }
}
