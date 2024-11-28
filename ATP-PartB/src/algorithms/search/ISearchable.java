package algorithms.search;

import java.util.ArrayList;

/**
 * Represents a searchable problem.
 */
public interface ISearchable {

    /**
     * Retrieves the start state of the problem.
     *
     * @return The start state.
     */
    AState getStartState();

    /**
     * Retrieves the goal state of the problem.
     *
     * @return The goal state.
     */
    AState getGoalState();

    /**
     * Retrieves all possible successors of the given state.
     *
     * @param state The current state.
     * @return List of successor states.
     * @throws Exception if an error occurs during the process.
     */
    ArrayList<AState> getAllSuccessors(AState state) throws Exception;

    /**
     * Clears the visit status of the states.
     */
    void clearVisit();


}
