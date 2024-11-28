package algorithms.search;

import java.io.Serializable;
import java.util.*;

public abstract class ASearchingAlgorithm implements  ISearchingAlgorithm, Serializable {
    protected int countNode;


    /**
     * @param currentState is the goal of the maze
     * @param start the start postion of the maze
     * @return arrayList that contains a path of the solution that found from the search algorithm
     */
    public ArrayList<AState> restoration (AState currentState, AState start) throws Exception {
        if (currentState==null || start==null)
            throw new Exception("The state is null");
        ArrayList <AState> path=new ArrayList<>();
        while (currentState.state.compareTo((start.state))!=0)
        {
            path.add(currentState);
            currentState=currentState.getComeFrom();
        }
        path.add(currentState);
        return path;
    }
}