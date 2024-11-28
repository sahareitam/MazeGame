package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a state in a maze.
 */
public class MazeState extends AState implements Serializable {
    private Position pos;

    /**
     * Constructs a MazeState with the given position and parent state.
     *
     * @param pos   The position of the state.
     * @param state The parent state.
     * @throws Exception if the position is null.
     */
    public MazeState(Position pos, AState state) throws Exception {
        if (pos == null)
            throw new Exception("The position is null");
        this.pos = pos;
        this.state = pos.toString();
        this.comeFrom = state;
        // Set the cost of the move
        if (state != null) {
            int x = getOldRow();
            int y = getOldCol();

            if ((x == pos.getRowIndex() - 1 && y == pos.getColumnIndex() - 1) || (x == pos.getRowIndex() + 1 && y == pos.getColumnIndex() + 1) || (x == pos.getRowIndex() + 1 && y == pos.getColumnIndex() - 1) || (x == pos.getRowIndex() - 1 && y == pos.getColumnIndex() + 1))
                this.cost = 15 + comeFrom.getCost();
            else
                this.cost = 10 + comeFrom.getCost();
        } else
            this.cost = 0;
    }

    /**
     * Checks if this state is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MazeState mazeState = (MazeState) o;
        return Objects.equals(pos, mazeState.pos);
    }

    /**
     * Retrieves the cost of moving to this state.
     *
     * @return The cost of moving to this state.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Retrieves the row index of the state's position.
     *
     * @return The row index of the state's position.
     */
    public int getX() {
        return pos.getRowIndex();
    }

    /**
     * Retrieves the column index of the state's position.
     *
     * @return The column index of the state's position.
     */
    public int getY() {
        return pos.getColumnIndex();
    }

    /**
     * Retrieves the row index of the previous position.
     *
     * @return The row index of the previous position.
     */
    public int getOldRow() {
        StringBuilder str = new StringBuilder();
        for (int i = 13; i < this.state.length(); i++) {
            if (this.state.charAt(i) == ',') {
                break;
            } else {
                str.append(this.state.charAt(i));
            }
        }
        return Integer.parseInt(str.toString());
    }

    /**
     * Retrieves the column index of the previous position.
     *
     * @return The column index of the previous position.
     */
    public int getOldCol() {
        StringBuilder str = new StringBuilder();
        boolean flag = false;
        for (int i = 13; i < this.state.length(); i++) {
            if (this.state.charAt(i) == '=') {
                i++;
                flag = true;
            }
            if (flag) {
                if (this.state.charAt(i) == '}')
                    break;
                str.append(this.state.charAt(i));
            }
        }
        return Integer.parseInt(str.toString());
    }
}
