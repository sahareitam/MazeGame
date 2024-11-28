package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents the Breadth-First Search algorithm for searching.
 * Extends ASearchingAlgorithm class.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm implements Serializable {
    private Queue<AState> possibleState;

    /**
     * Constructs a BreadthFirstSearch object and initializes the queue of possible states.
     */
    public BreadthFirstSearch() {
        this.possibleState = new LinkedList<>();
    }

    /**
     * Retrieves the queue of possible states.
     *
     * @return The queue of possible states.
     */
    public Queue<AState> getPossibleState() {
        return possibleState;
    }

    /**
     * Solves the given domain problem using Breadth-First Search algorithm.
     *
     * @param domain The problem that needs to be solved.
     * @return The solution to the problem.
     * @throws Exception if the domain is null.
     */
    @Override
    public Solution solve(ISearchable domain) throws Exception {
        if (domain == null)
            throw new Exception("the domain is null");
        domain.clearVisit();
        Solution solve = new Solution();

        AState start = domain.getStartState();
        getPossibleState().add(start);


        countNode++;

        AState currentState = null;

        while (!getPossibleState().isEmpty()) {
            currentState = getPossibleState().poll();

            if (currentState.state.compareTo(domain.getGoalState().state) == 0)
                break;

            ArrayList<AState> neighbors = domain.getAllSuccessors(currentState);

            while (!neighbors.isEmpty()) {
                AState n = neighbors.remove(0);
                n.setComeFrom(currentState);
                getPossibleState().add(n);
                countNode++;
            }
        }
        solve.solutionPath = restoration(currentState, domain.getStartState());

        return solve;
    }

    /**
     * Retrieves the name of the searching algorithm.
     *
     * @return The name of the searching algorithm.
     */
    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    /**
     * Retrieves the number of nodes evaluated during the search.
     *
     * @return The number of nodes evaluated.
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return countNode;
    }
}
