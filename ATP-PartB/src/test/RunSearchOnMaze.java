package test;

import algorithms.search.*;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        try {
            IMazeGenerator mg = new MyMazeGenerator();
            Maze maze = mg.generate(30, 30);

            SearchableMaze searchableMaze = new SearchableMaze(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher){
        //Solve a searching problem with a searcher
        Solution solution = null;
        try {
            solution = searcher.solve(domain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();


        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }

    }
}