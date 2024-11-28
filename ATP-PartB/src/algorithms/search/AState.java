package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.Objects;

public abstract class AState implements Serializable {
    protected int cost;
    protected AState comeFrom;
    protected String state;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return Objects.equals(state, aState.state);
    }

    /**
     * @return the cost of the movement between two states
     */
    public abstract int getCost();


    /**
     * @param state is the previous state of a currect state
     */
    public void setComeFrom(AState state) throws Exception {
        if (state==null)
            throw new Exception("can't change the previous state because the state is null");
        this.comeFrom=state;
    }

    /**
     * @return the previous state
     */
    public AState getComeFrom()
    {
        return comeFrom;
    }



    @Override
    public String toString() {
        return this.state;
    }
}