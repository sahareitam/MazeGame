package algorithms.search;

import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Represents the Best-First Search algorithm for searching.
 * Extends BreadthFirstSearch class.
 */
public class BestFirstSearch extends BreadthFirstSearch implements Serializable {
    private Queue<AState> possibleState;

    /**
     * Constructs a BestFirstSearch object and initializes the priority queue with a comparator based on cost.
     */
    public BestFirstSearch() {
        this.possibleState = new PriorityQueue<>(new CompareCostComparator());
    }

    /**
     * Retrieves the priority queue of possible states.
     *
     * @return The priority queue of possible states.
     */
    public Queue<AState> getPossibleState() {
        return this.possibleState;
    }

    /**
     * Retrieves the name of the search algorithm.
     *
     * @return The name of the search algorithm.
     */
    public String getName() {
        return "BestFirstSearch";
    }

    /**
     * Comparator class for comparing AState objects based on their costs.
     */
    class CompareCostComparator implements Comparator<AState> {
        /**
         * Compares two AState objects based on their costs.
         *
         * @param x The first AState object.
         * @param y The second AState object.
         * @return -1 if x's cost is less than y's cost, 1 if x's cost is greater than y's cost, 0 if costs are equal.
         */
        @Override
        public int compare(AState x, AState y) {
            if (x.getCost() < y.getCost()) {
                return -1;
            }
            if (x.getCost() > y.getCost()) {
                return 1;
            }
            return 0; // if the cost is equal
        }
    }
}
