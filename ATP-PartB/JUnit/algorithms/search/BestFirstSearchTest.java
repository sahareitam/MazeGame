package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void getName() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertEquals("BestFirstSearch", bfs.getName());
    }

    @Test
    void solveLargeMazeWithinOneMinute() {
        // Create a 1000x1000 maze
        Maze largeMaze = new Maze(1000, 1000);
        BestFirstSearch bfsLargeMaze = new BestFirstSearch();
        // Ensure the solve method completes within 1 minute
        assertTimeout(Duration.ofMinutes(1), () -> {
            Solution solutionLargeMaze = bfsLargeMaze.solve(new SearchableMaze(largeMaze));
            assertTrue(solutionLargeMaze.getSolutionPath().size() > 0);
        });
    }

    @Test
    void solve() throws Exception {
        // Test case 1: Minimum Size Maze (1x1)
        Maze maze1x1 = new Maze(1, 1);
        BestFirstSearch bfs1x1 = new BestFirstSearch();
        Solution solution1x1 = bfs1x1.solve(new SearchableMaze(maze1x1));
        assertEquals(1, solution1x1.getSolutionPath().size());

//      Test case 2: Very Small Maze (2x2)
        Maze maze2x2 = new Maze(2, 2);
        BestFirstSearch bfs2x2 = new BestFirstSearch();
        Solution solution2x2 = bfs2x2.solve(new SearchableMaze(maze2x2));
        assertTrue(solution2x2.getSolutionPath().size() > 0);


        // Test case 3: Large Sparse Maze (1000x1000)
        Maze maze1000x1000 = new Maze(1000, 1000);
        BestFirstSearch bfs1000x1000 = new BestFirstSearch();
        Solution solution1000x1000 = bfs1000x1000.solve(new SearchableMaze(maze1000x1000));
        assertTrue(solution1000x1000.getSolutionPath().size() > 0);


        // Test case 4: Dense Maze with Few Paths (1000x1000)
        Maze mazeDense = new Maze(1000, 1000);
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                mazeDense.setCell(i, j, rand.nextInt(10) == 0 ? 1 : 0); // Sparse walls
            }
        }
        BestFirstSearch bfsDense = new BestFirstSearch();
        Solution solutionDense = bfsDense.solve(new SearchableMaze(mazeDense));
        assertTrue(solutionDense.getSolutionPath().size() > 0);

        //Test case 5: Illegal Maze
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Maze(0, 0);
        });
    }
}

