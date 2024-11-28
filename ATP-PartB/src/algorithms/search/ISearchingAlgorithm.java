package algorithms.search;

/**
 * Represents a searching algorithm.
 */
public interface ISearchingAlgorithm {

    /**
     * Solves the given searchable problem.
     *
     * @param domain The problem to solve.
     * @return The solution to the problem.
     * @throws Exception if an error occurs during the process.
     */
    Solution solve(ISearchable domain) throws Exception;

    /**
     * Retrieves the name of the searching algorithm.
     *
     * @return The name of the searching algorithm.
     */
    String getName();

    /**
     * Retrieves the number of nodes evaluated during the search.
     *
     * @return The number of nodes evaluated.
     */
    int getNumberOfNodesEvaluated();
}
