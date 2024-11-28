package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

/**
 * Represents the Depth-First Search algorithm for searching.
 * Extends ASearchingAlgorithm class.
 */
public class DepthFirstSearch extends ASearchingAlgorithm implements Serializable {
    Stack<AState> possibleState;

    /**
     * Constructs a DepthFirstSearch object and initializes the stack of possible states.
     */
    public DepthFirstSearch() {
        this.possibleState = new Stack<>();
    }

    /**
     * Solves the given domain problem using Depth-First Search algorithm.
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
        possibleState.push(start);

        countNode++;

        AState currentState = null;
        while (!possibleState.empty()) {
            currentState = possibleState.pop();

            if (currentState.state.compareTo(domain.getGoalState().state) == 0)
                break;

            ArrayList<AState> neighbors = domain.getAllSuccessors(currentState);
            Collections.shuffle(neighbors);
            while (!neighbors.isEmpty()) {
                AState n = neighbors.remove(0);
                n.setComeFrom(currentState);
                possibleState.push(n);
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
        return "DepthFirstSearch";
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
