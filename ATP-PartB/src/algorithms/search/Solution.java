package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    ArrayList<AState> solutionPath=new ArrayList<>();


    /**
     * @return arrayList that contains a path of states of the solution that found from the search algorithm
     */
    public ArrayList<AState> getSolutionPath()
    {
        ArrayList<AState> temp=new ArrayList<>();
        for (int i = solutionPath.size()-1; i >= 0; i--) {
            temp.add(solutionPath.get(i));
        }
        return temp;
    }

}