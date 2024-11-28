package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable, Serializable {
    private final Maze maze;
    private final MazeState startState;
    private final MazeState goalState;
    private final boolean [][] visit; //visit is the matrix of visited cells.


    public SearchableMaze (Maze maze) throws Exception {
        this.maze=maze;
        startState=new MazeState(maze.getStartPosition(),null);
        goalState=new MazeState(maze.getGoalPosition(),null);
        visit=new boolean[maze.getRows()][maze.getColumns()];
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                visit[i][j]=false;
            }

        }
        visit[maze.getStartPosition().getRowIndex()][maze.getStartPosition().getColumnIndex()] = true;
    }

    /**
     * the function change the value of all the cells in array to false in order to search again the maze
     */
    public void clearVisit()
    {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                visit[i][j]=false;
            }
        }
    }

    /**
     * @return the start state in the maze
     */
    @Override
    public AState getStartState() {
        return startState;
    }

    /**
     * @return the goal state in the maze
     */
    @Override
    public AState getGoalState() {
        return goalState;
    }

    /**
     * @param stateA is a state in the maze
     * @return arrayList that contains the all possible neighbors states that can move from stateA to them
     */
    public ArrayList<AState> getAllSuccessors (AState stateA) throws Exception {
        if (stateA==null)
            throw new Exception("the state is null");
        MazeState state=(MazeState) stateA;
        int [][]m=this.maze.getGrid();
        ArrayList<AState> possibleStates=new ArrayList<>();

        if (state.getX()==0 &&state.getY()==0)
        {
            if (m[state.getX()+1][state.getY()]==0&& !visit[state.getX() + 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY()), state));
                visit[state.getX()+1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()+1]==0&& !visit[state.getX()][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() + 1), state));
                visit[state.getX()][state.getY()+1]=true;
            }

            if (m[state.getX()+1][state.getY()+1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0) && !visit[state.getX() + 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() + 1), state));
                visit[state.getX()+1][state.getY()+1]=true;
            }
        }
        else if (state.getX()==0&&state.getY()==maze.getColumns()-1)
        {
            if (m[state.getX()+1][state.getY()]==0 && !visit[state.getX() + 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY()), state));
                visit[state.getX()+1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()-1]==0&& !visit[state.getX()][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() - 1), state));
                visit[state.getX()][state.getY()-1]=true;
            }

            if (m[state.getX()+1][state.getY()-1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0)&& !visit[state.getX() + 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() - 1), state));
                visit[state.getX()+1][state.getY()-1]=true;
            }

        }
        else if (state.getX()==maze.getRows()-1 && state.getY()==0)
        {
            if (m[state.getX()-1][state.getY()]==0 && !visit[state.getX() - 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY()), state));
                visit[state.getX()-1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()+1]==0 && !visit[state.getX()][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() + 1), state));
                visit[state.getX()][state.getY()+1]=true;
            }
            if (m[state.getX()-1][state.getY()+1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0)&& !visit[state.getX() - 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() + 1), state));
                visit[state.getX()-1][state.getY()+1]=true;
            }
        }
        else if (state.getX()==maze.getRows()-1 && state.getY()==maze.getColumns()-1)
        {
            if (m[state.getX()-1][state.getY()]==0 && !visit[state.getX() - 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY()), state));
                visit[state.getX()-1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()-1]==0 && !visit[state.getX()][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() - 1), state));
                visit[state.getX()][state.getY()-1]=true;
            }
            if (m[state.getX()-1][state.getY()-1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0)&& !visit[state.getX() - 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() - 1), state));
                visit[state.getX()-1][state.getY()-1]=true;
            }
        }
        else if (state.getX()==0)
        {
            if (m[state.getX()+1][state.getY()]==0 && !visit[state.getX() + 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY()), state));
                visit[state.getX()+1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()-1]==0 && !visit[state.getX()][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() - 1), state));
                visit[state.getX()][state.getY()-1]=true;
            }
            if (m[state.getX()][state.getY()+1]==0 && !visit[state.getX()][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() + 1), state));
                visit[state.getX()][state.getY()+1]=true;
            }

            if (m[state.getX()+1][state.getY()+1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0)&& !visit[state.getX() + 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() + 1), state));
                visit[state.getX()+1][state.getY()+1]=true;
            }
            if (m[state.getX()+1][state.getY()-1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0) && !visit[state.getX() + 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() - 1), state));
                visit[state.getX()+1][state.getY()-1]=true;
            }
        }
        else if (state.getY()==maze.getColumns()-1)
        {
            if (m[state.getX()-1][state.getY()]==0 && !visit[state.getX() - 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY()), state));
                visit[state.getX()-1][state.getY()]=true;
            }
            if (m[state.getX()+1][state.getY()]==0 && !visit[state.getX() + 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY()), state));
                visit[state.getX()+1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()-1]==0 && !visit[state.getX()][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() - 1), state));
                visit[state.getX()][state.getY()-1]=true;
            }
            if (m[state.getX()+1][state.getY()-1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0) && !visit[state.getX() + 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() - 1), state));
                visit[state.getX()+1][state.getY()-1]=true;
            }
            if (m[state.getX()-1][state.getY()-1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0)&& !visit[state.getX() - 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() - 1), state));
                visit[state.getX()-1][state.getY()-1]=true;
            }
        }
        else if (state.getX()==maze.getRows()-1)
        {
            if (m[state.getX()-1][state.getY()]==0 && !visit[state.getX() - 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY()), state));
                visit[state.getX()-1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()-1]==0 && !visit[state.getX()][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() - 1), state));
                visit[state.getX()][state.getY()-1]=true;
            }
            if (m[state.getX()][state.getY()+1]==0&& !visit[state.getX()][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() + 1), state));
                visit[state.getX()][state.getY()+1]=true;
            }
            if (m[state.getX()-1][state.getY()-1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0) && !visit[state.getX() - 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() - 1), state));
                visit[state.getX()-1][state.getY()-1]=true;
            }
            if (m[state.getX()-1][state.getY()+1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0) && !visit[state.getX() - 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() + 1), state));
                visit[state.getX()-1][state.getY()+1]=true;
            }
        }
        else if (state.getY()==0)
        {
            if (m[state.getX()-1][state.getY()]==0 && !visit[state.getX() - 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY()), state));
                visit[state.getX()-1][state.getY()]=true;
            }
            if (m[state.getX()+1][state.getY()]==0 && !visit[state.getX() + 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY()), state));
                visit[state.getX()+1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()+1]==0&& !visit[state.getX()][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() + 1), state));
                visit[state.getX()][state.getY()+1]=true;
            }
            if (m[state.getX()+1][state.getY()+1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0) && !visit[state.getX() + 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() + 1), state));
                visit[state.getX()+1][state.getY()+1]=true;
            }
            if (m[state.getX()-1][state.getY()+1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0) && !visit[state.getX() - 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() + 1), state));
                visit[state.getX()-1][state.getY()+1]=true;
            }

        }
        else
        {
            if (m[state.getX()-1][state.getY()]==0 && !visit[state.getX() - 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY()), state));
                visit[state.getX()-1][state.getY()]=true;
            }
            if (m[state.getX()+1][state.getY()]==0 && !visit[state.getX() + 1][state.getY()]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY()), state));
                visit[state.getX()+1][state.getY()]=true;
            }
            if (m[state.getX()][state.getY()-1]==0 && !visit[state.getX()][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() - 1), state));
                visit[state.getX()][state.getY()-1]=true;
            }
            if (m[state.getX()][state.getY()+1]==0 && !visit[state.getX()][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX(), state.getY() + 1), state));
                visit[state.getX()][state.getY()+1]=true;
            }


            if (m[state.getX()+1][state.getY()+1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0) && !visit[state.getX() + 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() + 1), state));
                visit[state.getX()+1][state.getY()+1]=true;
            }
            if (m[state.getX()+1][state.getY()-1]==0 && (m[state.getX()+1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0) && !visit[state.getX() + 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() + 1, state.getY() - 1), state));
                visit[state.getX()+1][state.getY()-1]=true;
            }
            if (m[state.getX()-1][state.getY()-1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()-1]==0) && !visit[state.getX() - 1][state.getY() - 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() - 1), state));
                visit[state.getX()-1][state.getY()-1]=true;
            }
            if (m[state.getX()-1][state.getY()+1]==0 && (m[state.getX()-1][state.getY()]==0 || m[state.getX()][state.getY()+1]==0)&& !visit[state.getX() - 1][state.getY() + 1]) {
                possibleStates.add(new MazeState(new Position(state.getX() - 1, state.getY() + 1), state));
                visit[state.getX()-1][state.getY()+1]=true;
            }

        }
        return possibleStates;
    }
}